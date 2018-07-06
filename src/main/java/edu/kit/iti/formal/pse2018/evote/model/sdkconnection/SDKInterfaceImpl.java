package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionDataQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionStatusQuery;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

/**
 * Abstract SDKConnection interface with shared functionality between supervisor and voter.
 */
public abstract class SDKInterfaceImpl {

    protected AppUser appUser;
    protected HFClient hfClient;
    private ElectionStatusListener electionStatusListener;

    /**
     * Sets the AppUser attribute and creates ElectionStatusListener and HFClient.
     */
    protected SDKInterfaceImpl(AppUser appUser, SDKEventListener listener)  {
        this.appUser = appUser;
        createHFClient();
        createChannel();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.electionStatusListener = new ElectionStatusListener(listener,
                hfClient.getChannel(bundle.getString("channel_name")));
    }

    /**
     * Loads the AppUser from file and creates ElectionStatusListener and HFClient.
     * @param filePath path to the serialized AppUser
     * @throws IOException if reading file failed
     * @throws ClassNotFoundException if file is not a valid file
     * @throws ClassCastException if file is not a valid AppUser
     */
    protected SDKInterfaceImpl(String filePath, SDKEventListener listener) throws IOException, ClassNotFoundException,
            ClassCastException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.appUser = (AppUser)ois.readObject();
        createHFClient();
        createChannel();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.electionStatusListener = new ElectionStatusListener(listener,
                hfClient.getChannel(bundle.getString("channel_name")));
    }

    private void createHFClient() {
        this.hfClient = HFClient.createNewInstance();
        CryptoSuite cryptoSuite;
        try {
            cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | CryptoException
                | InvalidArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        try {
            this.hfClient.setCryptoSuite(cryptoSuite);
        } catch (CryptoException | InvalidArgumentException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        try {
            this.hfClient.setUserContext(this.appUser);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
    }

    private void createChannel() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Channel channel = hfClient.getChannel(bundle.getString("channel_name"));
        String[] names = bundle.getString("peer_names").split(",");
        String[] urls = bundle.getString("peer_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addPeer(hfClient.newPeer(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        names = bundle.getString("orderer_names").split(",");
        urls = bundle.getString("orderer_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addOrderer(hfClient.newOrderer(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        names = bundle.getString("eventhub_names").split(",");
        urls = bundle.getString("eventhub_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addEventHub(hfClient.newEventHub(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        try {
            channel.initialize();
        } catch (TransactionException e) {
            throw new RuntimeException(); //TODO: Replace with custom exception
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Requests an election status update from the network.
     */
    public void dispatchElectionOverCheck() {
        ElectionStatusQuery query = new ElectionStatusQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new RuntimeException(); //TODO: Replace with custom exception
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Requests the election data from the network.
     * @return election data stored in the network
     */
    public ElectionDataIF getElectionData() {
        ElectionDataQuery query = new ElectionDataQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new RuntimeException(); //TODO: Replace with custom exception
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return query.getResult();
    }
}