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

package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Date;

public class ElectionDataImpl implements ElectionDataIF {
    private String name;
    private String desc;
    private VotingSystemType type;
    private String[] candidates;
    private String[] candidatesDesc;
    private Date start;
    private Date end;
    private ElectionEndCondition eec;
    private int voterCount;

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(VotingSystemType type) {
        this.type = type;
    }

    public void setCandidates(String[] candidates) {
        this.candidates = candidates;
    }

    public void setCandidatesDesc(String[] candidatesDesc) {
        this.candidatesDesc = candidatesDesc;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setEec(ElectionEndCondition eec) {
        this.eec = eec;
    }

    public void setVoterCount(int voterCount) {
        this.voterCount = voterCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public VotingSystemType getVotingSystem() {
        return type;
    }

    @Override
    public String[] getCandidates() {
        return candidates;
    }

    @Override
    public String[] getCandidateDescriptions() {
        return candidatesDesc;
    }

    @Override
    public Date getStartDate() {
        return start;
    }

    @Override
    public Date getEndDate() {
        return end;
    }

    @Override
    public ElectionEndCondition getEndCondition() {
        return eec;
    }

    @Override
    public int getVoterCount() {
        return voterCount;
    }
}
