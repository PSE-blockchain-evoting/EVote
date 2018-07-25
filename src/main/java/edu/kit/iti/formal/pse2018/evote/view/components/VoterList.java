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

package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.RemovableExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextFieldExtension;

import java.awt.Font;
import javax.swing.UIManager;

/**
 * VoterList is UI Table for specifing Voters. It supports adding and removing Voters and giving them a name.
 */
public class VoterList extends ExtendableList {

    private TextFieldExtension tfe;

    private VoterList(ListExtension mod) {
        super(mod);
    }

    /**
     * Creates a Table for adding and removing voters.
     * @return a configured instance of ExtendableList
     */
    public static VoterList createVoterList() {
        Font f = (Font)UIManager.get("General.font");
        TextFieldExtension tfe = new TextFieldExtension(new RemovableExtension(null), f);
        NumberedExtension ne = new NumberedExtension(tfe, f);
        VoterList vl = new VoterList(ne);
        vl.setTextFieldExtension(tfe);
        ne.setList(vl);

        return vl;
    }

    private void setTextFieldExtension(TextFieldExtension tfe) {
        this.tfe = tfe;
    }

    /**
     * Access the voters in the list.
     * @return the list of Voters.
     */
    public String[] getVoters() {
        String[] list = new String[getEntryCount()];

        for (int i = 0; i < getEntryCount(); i++) {
            list[i] = tfe.getText(i);
        }
        return list;
    }

    /**
     * Sets the names of voters in the list.
     * @param voters The voters to enter in the list.
     */
    public void setVoters(String[] voters) {
        for (int i = 0; i < voters.length; i++) {
            if (i >= getEntryCount()) {
                //Add new entries
                this.addNewEntry();
            }
            tfe.setText(i, voters[i]);
        }

        if (voters.length < getEntryCount()) {
            for (int i = voters.length; i < getEntryCount(); i++) {
                removeEntry(entries.get(i));
            }
        }
    }
}
