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

    /**
     * Creates a new AppUser instance.
     * @param userName Name of the user
     * @param affiliation Hyperleger Organization the user is afilliated with.
     * @param roles legacy field, always ignored
     * @param accountName legacy field, ignored
     * @param mspID ID of the MSP the user is registered at
     * @param enrollment @see Hyperledger Fabric SDK: Enrollment
     */
    public AppUser(String userName, String affiliation, Set<String> roles, String accountName, String mspID,
                   Enrollment enrollment) {
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
