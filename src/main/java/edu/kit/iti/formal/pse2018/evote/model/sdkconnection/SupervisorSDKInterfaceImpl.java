package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import java.io.IOException;
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

public class SupervisorSDKInterfaceImpl extends SDKInterfaceImpl {

    private HFCAClient hfcaClient;

    public SupervisorSDKInterfaceImpl(String filePath) throws IOException, ClassNotFoundException, ClassCastException {
        super(filePath); //TODO: SDKListener
    }

    public SupervisorSDKInterfaceImpl(String username, String password, String filePath) throws MalformedURLException {
        super(createAdminUser(username, filePath));
        CryptoSuite cryptoSuite;
        try {
            cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | CryptoException |
                InvalidArgumentException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.hfcaClient = HFCAClient.createNewInstance(bundle.getString("ca_url"), null); //TODO: Wrap exception
        hfcaClient.setCryptoSuite(cryptoSuite);
        Enrollment enrollment = null;
        try {
            enrollment = hfcaClient.enroll(username, password);
        } catch (EnrollmentException | org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException e) {
            throw new RuntimeException(); //TODO: Use Exception from package
        }
        this.setAppUserEnrollment(enrollment);
    }

    private static AppUser createAdminUser(String username, String filePath) {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        AppUser appUser = new AppUser(username, bundle.getString("affiliation"), new HashSet<>(), username,
                bundle.getString("mspID"), null);
        return appUser;
    }
}
