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

public class ConfigIssuesImpl implements ConfigIssues {

    private String nameIssue;
    private String candidateIssue;
    private String voterIssue;
    private String timespanIssue;
    private String endConditionIssue;

    public void setNameIssue(String nameIssue) {
        this.nameIssue = nameIssue;
    }

    public void setCandidateIssue(String candidateIssue) {
        this.candidateIssue = candidateIssue;
    }

    public void setVoterIssue(String voterIssue) {
        this.voterIssue = voterIssue;
    }

    public void setTimespanIssue(String timespanIssue) {
        this.timespanIssue = timespanIssue;
    }

    public void setEndConditionIssue(String endConditionIssue) {
        this.endConditionIssue = endConditionIssue;
    }

    @Override
    public String getNameIssue() {
        return nameIssue;
    }

    @Override
    public String getCandidateIssue() {
        return candidateIssue;
    }

    @Override
    public String getVoterIssue() {
        return voterIssue;
    }

    @Override
    public String getTimespanIssue() {
        return timespanIssue;
    }

    @Override
    public String getEndConditionIssue() {
        return endConditionIssue;
    }
}
