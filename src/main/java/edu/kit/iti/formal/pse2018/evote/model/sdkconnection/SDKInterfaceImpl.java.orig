package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionDataQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionStatusQuery;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

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
        this.hfClient = HFClient.createNewInstance();
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
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.electionStatusListener = new ElectionStatusListener(listener,
                hfClient.getChannel(bundle.getString("channel_name")));
    }

    private void createChannel() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Channel channel = hfClient.getChannel(bundle.getString("channel_name"));
        String[] names = bundle.getStringArray("peer_names");
        String[] urls = bundle.getStringArray("peer_urls");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addPeer(hfClient.newPeer(names[i], urls[i]));
            }
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        names = bundle.getStringArray("orderer_names");
        urls = bundle.getStringArray("orderer_urls");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addOrderer(hfClient.newOrderer(names[i], urls[i]));
            }
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        names = bundle.getStringArray("eventhub_names");
        urls = bundle.getStringArray("eventhub_urls");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addEventHub(hfClient.newEventHub(names[i], urls[i]));
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
        query.query();
    }

    /**
     * Requests the election data from the network.
     * @return election data stored in the network
     */
    public ElectionDataIF getElectionData() {
        ElectionDataQuery query = new ElectionDataQuery(this.hfClient);
        query.query();
        return query.getResult();
    }
}