package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;

/**
 * Abstract SDKConnection interface with shared functionality between supervisor and voter.
 */
public abstract class SDKInterfaceImpl { //TODO: Realize SDKInterface

    private AppUser appUser;
    private HFClient hfClient;
    private ElectionStatusListener electionStatusListener;

    /**
     * Sets the AppUser attribute and creates ElectionStatusListener and HFClient.
     */
    protected SDKInterfaceImpl(AppUser appUser) {
        this.appUser = appUser;
        this.electionStatusListener = new ElectionStatusListener(); //TODO: SDKEventListener Parameter
        this.hfClient = HFClient.createNewInstance();
    }

    /**
     * Loads the AppUser from file and creates ElectionStatusListener and HFClient.
     * @param filePath path to the serialized AppUser
     * @throws IOException if reading file failed
     * @throws ClassNotFoundException if file is not a valid file
     * @throws ClassCastException if file is not a valid AppUser
     */
    protected SDKInterfaceImpl(String filePath) throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.appUser = (AppUser)ois.readObject();
        this.electionStatusListener = new ElectionStatusListener(); //TODO: See above
    }

    public void setAppUserEnrollment(Enrollment enrollment) {
        this.appUser.setEnrollment(enrollment);
    }
}