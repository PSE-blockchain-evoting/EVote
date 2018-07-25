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
