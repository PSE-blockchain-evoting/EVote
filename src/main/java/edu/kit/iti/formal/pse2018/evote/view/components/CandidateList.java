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

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.DescriptionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.RemovableExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextFieldExtension;

import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * CandidateList provides a configured ExtendableList with the purpose of adding
 * and removing Candidates and giving each Candidate a description.
 */
public class CandidateList extends ExtendableList {

    private TextFieldExtension tfe;
    private DescriptionExtension de;

    private CandidateList(ListExtension e) {
        super(e);
    }

    /**
     * Creates a Table for adding and removing Candidates.
     *
     * @param parent parent JFrame for DescriptionExtensions JDialogs.
     * @return a configured instance of ExtendableList
     */
    public static CandidateList createCandidateList(JFrame parent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorConfig");
        Font f = (Font)UIManager.get("General.font");
        DescriptionExtension de = new DescriptionExtension(new RemovableExtension(null), f, parent);
        de.setButtonText(lang.getString("btnDescriptionText"));
        TextFieldExtension tfe = new TextFieldExtension(de, f);
        NumberedExtension ne = new NumberedExtension(tfe, f);

        CandidateList cl = new CandidateList(ne);
        cl.setTextFieldExtension(tfe);
        cl.setDescriptionExtension(de);
        ne.setList(cl);

        return cl;
    }

    /**
     * getCandidateNames will extract all names that in the list.
     *
     * @return All candidate names
     */
    public String[] getCandidateNames() {
        String[] list = new String[getEntryCount()];

        for (int i = 0; i < getEntryCount(); i++) {
            list[i] = tfe.getText(i);
        }
        return list;
    }

    /**
     * Sets the names of the candidates in the list.
     * This method will extends or shorten the list to the lengths of names.
     * If there are descriptions in the list, the entries that whose names are only replaced will stay the same.
     *
     * @param names the names of the new candidates.
     */
    public void setCandidateNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            if (i >= getEntryCount()) {
                //Add new entries
                this.addNewEntry();
            }
            tfe.setText(i, names[i]);
        }

        if (names.length < getEntryCount()) {
            for (int i = names.length; i < getEntryCount(); i++) {
                removeEntry(entries.get(i));
            }
        }
    }

    /**
     * Access to the Description of the candidates.
     * @return The descriptions of the candidates.
     */
    public String[] getCandidateDescriptions() {
        String[] list = new String[getEntryCount()];

        for (int i = 0; i < getEntryCount(); i++) {
            list[i] = de.getDescription(i);
        }
        return list;
    }

    /**
     * Set the Descriptions.
     *
     * @param descs The descriptions.
     */
    public void setCandidateDescriptions(String[] descs) {
        for (int i = 0; i < descs.length; i++) {
            if (i >= getEntryCount()) {
                //Add new entries
                this.addNewEntry();
            }
            de.setDescription(i, descs[i]);
        }

        if (descs.length < getEntryCount()) {
            for (int i = descs.length; i < getEntryCount(); i++) {
                removeEntry(entries.get(i));
            }
        }
    }

    private void setTextFieldExtension(TextFieldExtension te) {
        this.tfe = te;
    }

    private void setDescriptionExtension(DescriptionExtension de) {
        this.de = de;
    }
}
