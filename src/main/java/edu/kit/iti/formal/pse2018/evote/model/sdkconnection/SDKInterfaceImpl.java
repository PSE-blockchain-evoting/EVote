package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;

public abstract class SDKInterfaceImpl { //TODO: Realize SDKInterface

    private static final String FILE_ENDING = ".jso";

    private AppUser appUser;
    private HFClient hfClient;
    private ElectionStatusListener electionStatusListener;

    public SDKInterfaceImpl(AppUser appUser) {
        this.appUser = appUser;
        this.electionStatusListener = new ElectionStatusListener(); //TODO: SDKEventListener Parameter
        this.hfClient = HFClient.createNewInstance();
    }

    public SDKInterfaceImpl(String filePath) throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream fis = new FileInputStream(filePath + FILE_ENDING);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.appUser = (AppUser)ois.readObject();
        this.electionStatusListener = new ElectionStatusListener(); //TODO: See above
    }

    public void setAppUserEnrollment(Enrollment enrollment) {
        this.appUser.setEnrollment(enrollment);
    }
}