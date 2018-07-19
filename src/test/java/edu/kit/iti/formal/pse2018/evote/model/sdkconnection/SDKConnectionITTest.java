package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.SingleStringQuery;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

//THIS TEST ONLY WORKS WITH A RUNNING NETWORK
@Ignore
public class SDKConnectionITTest {

    private SupervisorSDKInterfaceImpl sdkInterface;
    private SDKEventListener eventListener;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public void setup() throws IOException, NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        eventListener = mock(SDKEventListener.class);
        sdkInterface = SupervisorSDKInterfaceImpl.createInstance("admin", "adminpw", tempFolder.newFile("adminIdent").getAbsolutePath(), eventListener);
    }


    public void createUserTest() throws IOException, EnrollmentException {
        sdkInterface.createUser("testuser1", tempFolder.newFile("user1Ident").getAbsolutePath());
    }


    public void createElectionTest() throws FileNotFoundException, NetworkException, NetworkConfigException {
        JsonReader reader = Json.createReader(new FileInputStream("src/test/resources/electionDataExample.json"));
        ElectionDataIF electionData = ElectionData.fromJSon(reader.readObject());
        sdkInterface.createElection(electionData);
    }

    public void queryTestQuery() throws NetworkException, ProposalException, InvalidArgumentException {
        SingleStringQuery query = new SingleStringQuery(sdkInterface.hfClient) {
            @Override
            protected String[] buildArgumentStrings() {
                return new String[0];
            }

            @Override
            protected String getFunctionName() {
                return "cidtest";
            }
        };
        query.query();
        System.out.println(query.getResult());
    }

    @Test
    public void all() throws NetworkConfigException, NetworkException, AuthenticationException, InternalSDKException, IOException, EnrollmentException, InvalidArgumentException, ProposalException {
        setup();
        //createUserTest();
        //createElectionTest();
        queryTestQuery();
    }

}
