package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import java.io.Serializable;
import java.util.Set;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;


public class AppUser implements User, Serializable {

    private String userName;
    private String affiliation;
    private Set<String> roles;
    private String accountName;
    private String mspID;
    private Enrollment enrollment;

    public AppUser(String userName, String affiliation, Set<String> roles, String accountName, String mspID, Enrollment enrollment) {
        this.userName = userName;
        this.affiliation = affiliation;
        this.roles = roles;
        this.accountName = accountName;
        this.mspID = mspID;
        this.enrollment = enrollment;
    }

    public String getName() {
        return this.userName;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public String getAccount() {
        return this.accountName;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public Enrollment getEnrollment() {
        return this.enrollment;
    }

    public String getMspId() {
        return this.mspID;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
}
