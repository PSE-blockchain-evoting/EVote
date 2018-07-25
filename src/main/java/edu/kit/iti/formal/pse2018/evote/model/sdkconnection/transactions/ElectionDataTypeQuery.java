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
import edu.kit.iti.formal.pse2018.evote.utils.CandidatePercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class ElectionDataTypeQuery extends QueryTransaction {

    private ElectionDataIF result;

    public ElectionDataTypeQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        this.result = ElectionData.fromJSon(obj);
    }

    public ElectionDataIF getResult() {
        return this.result;
    }
}
