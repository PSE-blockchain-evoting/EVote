package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.AllVotesQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.DestructionInvocation;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.InitializationInvocation;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.Attribute;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAEnrollment;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.IdentityException;
import org.hyperledger.fabric_ca.sdk.exception.RegistrationException;


/**
 * SDKConnection interface with supervisor-specific functionality.
 */
public class SupervisorSDKInterfaceImpl extends SDKInterfaceImpl implements SupervisorSDKInterface {

    private HFCAClient hfcaClient;

    private SupervisorSDKInterfaceImpl(String filePath, SDKEventListener listener, HFCAClient hfcaClient)
            throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        super(filePath, listener);
        this.hfcaClient = hfcaClient;
    }

    private SupervisorSDKInterfaceImpl(AppUser appUser, SDKEventListener listener, HFCAClient hfcaClient)
            throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        super(appUser, listener);
        this.hfcaClient = hfcaClient;
    }

    /**
     * Creates a new SupervisorSDKInterfaceImpl instance.
     * @param username Username of the admin user
     * @param password Password of the admin user
     * @param filePath Filepath to save the admin identity to
     * @param listener listener to be notified of status changes
     * @return a new SupervisorSDKInterfaceImpl
     */
    public static SupervisorSDKInterfaceImpl createInstance(String username, String password, String filePath,
                SDKEventListener listener) throws IOException, NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        HFCAClient hfcaClient = createHFCAClient();
        Enrollment enrollment;
        HFCAIdentity identity;
        try {
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("test_attr", "test_val"));
            identity = hfcaClient.newHFCAIdentity(username);
            identity.setAttributes(attributes);
            enrollment = hfcaClient.enroll(username, password);
        } catch (EnrollmentException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        AppUser appUser = new AppUser(username, bundle.getString("affiliation"), new HashSet<>(), username,
                bundle.getString("mspID"), enrollment);
        try {
            identity.update(appUser);
            for (HFCAIdentity hfcaIdentity : hfcaClient.getHFCAIdentities(appUser)) {
                System.out.println(hfcaIdentity);
                for (Attribute attribute : hfcaIdentity.getAttributes()) {
                    System.out.println("\t" + attribute.getName() + ": " + attribute.getValue());
                }
            }
        } catch (IdentityException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new NetworkException(e.getMessage());
        }

        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        return new SupervisorSDKInterfaceImpl(appUser, listener, hfcaClient);
    }

    /**
     * Creates a new SupervisorSDKInterfaceImpl instance.
     * @param filePath path to load admin identity from
     * @param listener listener to be notified of status changes
     * @return new SupervisorSDKInterfaceImpl
     */
    public static SupervisorSDKInterfaceImpl createInstance(String filePath, SDKEventListener listener)
            throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        return new SupervisorSDKInterfaceImpl(filePath, listener, createHFCAClient());
    }

    private static HFCAClient createHFCAClient() throws InternalSDKException, NetworkException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        CryptoSuite cryptoSuite;
        HFCAClient hfcaClient;
        try {
            cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | CryptoException
                | InvalidArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new InternalSDKException(e.getMessage());
        }
        try {
            hfcaClient = HFCAClient.createNewInstance(bundle.getString("ca_url"), null);
        } catch (MalformedURLException e) {
            throw new NetworkException(e.getMessage());
        }
        hfcaClient.setCryptoSuite(cryptoSuite);
        return hfcaClient;
    }

    /**
     * Completely stop election and restart the network.
     */
    public void destroyElection() throws NetworkException, NetworkConfigException {
        DestructionInvocation inv = new DestructionInvocation(this.hfClient);
        try {
            inv.invoke();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
    }

    /**
     * Creates a new election.
     * @param electionData to create the election with
     */
    public void createElection(ElectionDataIF electionData) throws NetworkException, NetworkConfigException {
        InitializationInvocation inv = new InitializationInvocation(this.hfClient, electionData);
        try {
            inv.invoke();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
    }

    @Override
    public void createUser(String name, String filePath) throws IOException,
            edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        RegistrationRequest request;
        try {
            request = new RegistrationRequest(name, bundle.getString("affiliation"));
        } catch (Exception e) {
            throw new edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException(e.getMessage());
        }
        String enrollmentSecret;
        try {
            enrollmentSecret = hfcaClient.register(request, this.appUser);
        } catch (RegistrationException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException(e.getMessage());
        }
        HFCAEnrollment enrollment;
        try {
            enrollment = (HFCAEnrollment) hfcaClient.enroll(name, enrollmentSecret);
        } catch (EnrollmentException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException(e.getMessage());
        }
        AppUser user = new AppUser(name, bundle.getString("affiliation"), new HashSet<>(), name,
                bundle.getString("mspID"), enrollment);
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
    }
}
