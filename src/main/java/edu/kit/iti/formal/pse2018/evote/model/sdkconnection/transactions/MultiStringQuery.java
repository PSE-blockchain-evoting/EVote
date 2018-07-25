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

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonString;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class MultiStringQuery extends QueryTransaction {

    private String[] result;

    public MultiStringQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
        JsonArray arr = reader.readArray();
        this.result = arr.getValuesAs(JsonString.class).stream().map(x -> x.getString()).toArray(String[]::new);
    }

    public String[] getResult() {
        System.out.println("\u001B[32m" + "Multistringquery result = " + Arrays.toString(this.result) + "\u001B[0m");
        return this.result;
    }
}
