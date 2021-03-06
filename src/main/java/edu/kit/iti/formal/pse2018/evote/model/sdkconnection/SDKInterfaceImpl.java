/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.AllVotesQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionDataQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.ElectionStatusQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.InitStatusQuery;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigResourceBundle;
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

/**
 * Abstract SDKConnection interface with shared functionality between supervisor and voter.
 */
public abstract class SDKInterfaceImpl implements SDKInterface {

    protected AppUser appUser;
    protected HFClient hfClient;

    /**
     * Sets the AppUser attribute and creates ElectionStatusListener and HFClient.
     */
    protected SDKInterfaceImpl(AppUser appUser) throws NetworkConfigException,
            AuthenticationException, InternalSDKException, NetworkException {
        this.appUser = appUser;
        createHFClient();
        createChannel();
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
    }

    /**
     * Loads the AppUser from file and creates ElectionStatusListener and HFClient.
     *
     * @param filePath path to the serialized AppUser
     * @throws IOException            if reading file failed
     * @throws ClassNotFoundException if file is not a valid file
     * @throws ClassCastException     if file is not a valid AppUser
     */
    protected SDKInterfaceImpl(String filePath) throws AuthenticationException,
            NetworkConfigException, NetworkException, InternalSDKException {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.appUser = (AppUser) ois.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new AuthenticationException(e.getMessage());
        }
        createHFClient();
        createChannel();
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
    }

    private void createHFClient() throws InternalSDKException, AuthenticationException {
        this.hfClient = HFClient.createNewInstance();
        CryptoSuite cryptoSuite;
        try {
            cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | CryptoException
                | InvalidArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new InternalSDKException(e.getMessage());
        }
        try {
            this.hfClient.setCryptoSuite(cryptoSuite);
        } catch (CryptoException | InvalidArgumentException e) {
            throw new InternalSDKException(e.getMessage());
        }
        try {
            this.hfClient.setUserContext(this.appUser);
        } catch (InvalidArgumentException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

    private void createChannel() throws NetworkConfigException, NetworkException {
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
        Channel channel;
        try {
            channel = hfClient.newChannel(bundle.getString("channel_name"));
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        String[] names = bundle.getString("peer_names").split(",");
        String[] urls = bundle.getString("peer_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addPeer(hfClient.newPeer(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        names = bundle.getString("orderer_names").split(",");
        urls = bundle.getString("orderer_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addOrderer(hfClient.newOrderer(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        names = bundle.getString("eventhub_names").split(",");
        urls = bundle.getString("eventhub_urls").split(",");
        assert names.length == urls.length;
        try {
            for (int i = 0; i < names.length; i++) {
                channel.addEventHub(hfClient.newEventHub(names[i].trim(), urls[i].trim()));
            }
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }

        try {
            channel.initialize();
        } catch (TransactionException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
    }

    /**
     * Requests an election status update from the network.
     */
    public boolean isElectionOver() throws NetworkException, NetworkConfigException {
        ElectionStatusQuery query = new ElectionStatusQuery(this.hfClient);
        try {
            query.query();

        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        System.out.println("isElectionOver: " + query.getResult());
        return query.getResult().equals("ended");
    }

    /**
     * Requests the election data from the network.
     *
     * @return election data stored in the network
     */
    public ElectionDataIF getElectionData() throws NetworkException, NetworkConfigException {
        ElectionDataQuery query = new ElectionDataQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        return query.getResult();
    }

    /**
     * Gets all votes from the network.
     *
     * @return all votes
     */
    public String[] getAllVotes() throws NetworkException, NetworkConfigException {
        AllVotesQuery query = new AllVotesQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        return query.getResult();
    }

    /**
     * Checks if an election is initialized or not.
     *
     * @return true, if election is initialized.
     */
    public boolean isElectionInitialized() throws NetworkException, NetworkConfigException {
        InitStatusQuery query = new InitStatusQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        return "true".equals(query.getResult());
    }

}