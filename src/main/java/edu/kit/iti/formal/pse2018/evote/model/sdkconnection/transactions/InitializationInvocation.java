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

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import org.hyperledger.fabric.sdk.HFClient;

public class InitializationInvocation extends InvocationTransaction {

    private ElectionDataIF electionData;

    public InitializationInvocation(HFClient client, ElectionDataIF electionData) {
        super(client);
        this.electionData = electionData;
    }

    @Override
    protected String[] buildArgumentStrings() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        JsonObject obj = ElectionData.toJson(this.electionData).build();
        writer.writeObject(obj);
        writer.close();
        return new String[]{stringWriter.toString()};
    }

    @Override
    protected String getFunctionName() {
        return "initializationInvokation";
    }
}
