package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;


/**
 * SDKConnection interface with supervisor-specific functionality.
 */
public class SupervisorSDKInterfaceImpl extends SDKInterfaceImpl {

    private HFCAClient hfcaClient;

    private SupervisorSDKInterfaceImpl(String filePath) throws IOException, ClassNotFoundException, ClassCastException {
        super(filePath); //TODO: SDKListener
    }

    private SupervisorSDKInterfaceImpl(AppUser appUser) throws MalformedURLException {
        super(appUser);
    }

    /**
     * Creates a new SupervisorSDKInterfaceImpl instance
     * @param username Username of the admin user
     * @param password Password of the admin user
     * @param filePath Filepath to save the admin identity to
     * @return a new SupervisorSDKInterfaceImpl
     * @throws IOException if writing to filePath failed
     */
    public static SupervisorSDKInterfaceImpl createInstance(String username, String password, String filePath)
            throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        CryptoSuite cryptoSuite;
        HFCAClient hfcaClient;
        Enrollment enrollment;
        try {
            cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | CryptoException |
                InvalidArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        try {
            hfcaClient = HFCAClient.createNewInstance(bundle.getString("ca_url"), null);
        } catch (MalformedURLException e) {
            throw new RuntimeException(); //TODO: Wrap exception
        }
        hfcaClient.setCryptoSuite(cryptoSuite);
        try {
            enrollment = hfcaClient.enroll(username, password);
        } catch (EnrollmentException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        AppUser appUser = new AppUser(username, bundle.getString("affiliation"), new HashSet<>(), username,
                bundle.getString("mspID"), enrollment);
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        return new SupervisorSDKInterfaceImpl(appUser);
    }

    /**
     * Creates a new SupervisorSDKInterfaceImpl instance
     * @param filePath path to load admin identity from
     * @return new SupervisorSDKInterfaceImpl
     * @throws IOException if reading filePath failed
     * @throws ClassNotFoundException if file is not a valid identity
     */
    public static SupervisorSDKInterfaceImpl createInstance(String filePath) throws IOException,
            ClassNotFoundException {
        return new SupervisorSDKInterfaceImpl(filePath);
    }
}
