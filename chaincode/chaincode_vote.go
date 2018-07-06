package main

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/lib/cid"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
	"encoding/json"
	"time"
)

type VoteChaincode struct {
}

func (t *VoteChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {
	fmt.Println("Chaincode Init")
	return shim.Success(nil)
}

func (t *VoteChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {
	fmt.Println("Vote Invoke")
	function, args := stub.GetFunctionAndParameters()
	if function == "allVotesQuery" {
		// Retrieve all submitted votes.
		return t.allVotesQuery(stub, args)
	} else if function == "electionStatusQuery" {
		// Check if election has ended.
		return t.electionStatusQuery(stub, args)
	} else if function == "ownVoteQuery" {
		// Retrieve the own vote.
		return t.ownVoteQuery(stub, args)
	} else if function == "electionDataQuery" {
		// Retrieve metadata about the election.
		return t.electionDataQuery(stub, args)
	} else if function == "destructionInvokation" {
		// Clears the current election.
		return t.destructionInvokation(stub, args)
	} else if function == "initializationInvokation" {
		// Initializes the election with metadata.
		return t.initializationInvokation(stub, args)
	} else if function == "voteInvokation" {
		// Submits vote to chaincode.
		return t.voteInvokation(stub, args)
	}

	return shim.Error("Invalid invoke function name. Expecting \"vote\" \"query\"")
}

func (t *VoteChaincode) allVotesQuery(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var resultSlice []string

	stateIterator, err := stub.GetStateByRange("vote_0", "w")
	if err != nil {
		return shim.Error("Failed to get StateIterator")
	}
	defer stateIterator.Close()

	for stateIterator.HasNext() {
		queryResponse, err := stateIterator.Next()
		if err != nil {
			return shim.Error("Failed to get next element from state")
		}
		resultSlice = append(resultSlice, string(queryResponse.Value))
	}

	returnJson, err := json.Marshal(resultSlice)
	if err != nil {
		return shim.Error("Failed to generate Json")
	}
	return shim.Success(returnJson)
}

func (t *VoteChaincode) electionStatusQuery(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	return shim.Success(nil)
}

func (t *VoteChaincode) ownVoteQuery(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	creatorBytes, err := stub.GetCreator()
	if err != nil {
		return shim.Error("Failed to get creator")
	}
	key := "vote_" + string(creatorBytes)
	stateBytes, err := stub.GetState(key)
	if err != nil {
		return shim.Error("Failed to get vote")
	}

	return shim.Success(stateBytes)
}

// Query Election Metadata on ledger.
func (t *VoteChaincode) electionDataQuery(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	stateBytes, err := stub.GetState("init")
	if err != nil {
		return shim.Error("Failed to get state")
	}
	if stateBytes == nil {
		return shim.Error("Election not initialized")
	}

	fmt.Printf("Responding with ElectionData: " + string(stateBytes))
	return shim.Success(stateBytes)
}

func (t *VoteChaincode) destructionInvokation(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	//TODO: Test this for authentication procedure
	//Leider können wir dem bootstrap admin keine Attribute mitgeben, sonst ginge das denke ich schöner:
	//https://github.com/hyperledger/fabric/tree/master/core/chaincode/lib/cid
	id, err := cid.GetID(stub)
	if err != nil {
		return shim.Error("Failed to get requesting Id")
	}
	if id != "admin" {
		return shim.Error("Requesting entity isn't admin")
	}

	return shim.Success(nil)
}

// Write ElectionData on ledger with 'init' key.
func (t *VoteChaincode) initializationInvokation(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var initMap map[string]*json.RawMessage
	var endConditionMap map[string]*json.RawMessage
	layout := "Mon Jan _2 15:04:05 MST 2006"

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting a single JSON string representing ElectionData")
	}

	var initJson = args[0]

	stateBytes, err := stub.GetState("init")
	if err != nil {
		return shim.Error("Failed to get state")
	}
	if stateBytes != nil {
		return shim.Error("Init set already")
	}

	err = json.Unmarshal([]byte(stateBytes), &initMap)
	if err != nil {
		return shim.Error("Json couldn't be parsed")
	}
	err = json.Unmarshal([]byte(*initMap["endCondition"]), &endConditionMap)
	if err != nil {
		return shim.Error("Json couldn't be parsed")
	}

	_, err = time.Parse(layout, string(*initMap["endDate"]))
	if err != nil {
		return shim.Error("The given Time couldn't be parsed")
	}

	if endConditionMap["type"] == nil {
		return shim.Error("Json couldn't be parsed")
	}
	if string(*endConditionMap["type"]) != "TimeOnlyCondition" {
		if endConditionMap["percentage"] == nil {
			return shim.Error("Json couldn't be parsed")
		}
	}

	err = stub.PutState("init", []byte(initJson))
	if err != nil {
		return shim.Error(err.Error())
	}

	fmt.Println("Init written to Ledger:")
	fmt.Println(initJson)
	return shim.Success(nil)
}

func (t *VoteChaincode) voteInvokation(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting a single JSON string representing a Vote")
	}
	var voteJson = args[0]

	creatorBytes, err := stub.GetCreator()
	if err != nil {
		return shim.Error("Failed to get creator")
	}
	key := "vote_" + string(creatorBytes)
	stateBytes, err := stub.GetState(key)
	if err != nil {
		return shim.Error("Failed to get state")
	}
	if stateBytes == nil {
		return shim.Error("User already voted once")
	}

	err = stub.PutState(key, []byte(voteJson))
	if err != nil {
		return shim.Error(err.Error())
	}

	return shim.Success(nil)
}

func main() {
	err := shim.Start(new(VoteChaincode))
	if err != nil {
		fmt.Printf("Error starting Vote chaincode: %s", err)
	}
}
