package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.*;

public class VoterList extends ExtendableList{
    private VoterList(ListExtension mod) {
        super(mod);
    }

    /**
     * Creates a Table for adding and removing voters.
     * @return a configured instance of ExtendableList
     */
    public static VoterList createVoterList(){

        RadioSelectionExtension rse = new RadioSelectionExtension(new TextFieldExtension(new RemovableExtension(null)));
        NumberedExtension ne = new NumberedExtension(rse);
        VoterList vl = new VoterList(ne);
        ne.setList(vl);

        return vl;
    }
}
