package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.AppUser;

import java.util.HashSet;
import java.util.Set;

import org.hyperledger.fabric.sdk.Enrollment;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AppUserTest {

    @Test
    public void all() {
        String username = "TestUserName";
        String affiliation = "TestAffiliation";
        Set<String> roles = new HashSet<>();
        roles.add("TestRole1");
        roles.add("TestRole2");
        String accountName = "TestAccountName";
        String mspID = "TestMSPID";
        Enrollment enrollment = mock(Enrollment.class);
        AppUser appUser = new AppUser(username, affiliation, roles, accountName, mspID, enrollment);
        assertEquals(username, appUser.getName());
        assertEquals(affiliation, appUser.getAffiliation());
        assertArrayEquals(roles.toArray(), appUser.getRoles().toArray());
        assertEquals(accountName, appUser.getAccount());
        assertEquals(mspID, appUser.getMspId());
        assertEquals(enrollment, appUser.getEnrollment());
    }
}
