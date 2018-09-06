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

package edu.kit.iti.formal.pse2018.evote.utils;

import javax.json.Json;
import javax.json.JsonObject;

public class VoterPercentileCondition extends ElectionEndCondition {

    private int percentage;

    public VoterPercentileCondition(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage(){
        return percentage;
    }

    @Override
    public JsonObject asJsonObject() {
        return Json.createObjectBuilder()
                .add("type", "VoterPercentileCondition")
                .add("percentage", this.percentage)
                .build();
    }
}
