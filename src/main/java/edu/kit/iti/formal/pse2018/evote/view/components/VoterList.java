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
