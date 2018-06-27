package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listExtensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listExtensions.RadioSelectionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listExtensions.RemovableExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listExtensions.TextFieldExtension;

public class VoterList extends ExtendableList{
    private VoterList(ListExtension mod) {
        super(mod);
    }

    public static VoterList createVoterList(){

        RadioSelectionExtension rse = new RadioSelectionExtension(new TextFieldExtension(new RemovableExtension(null)));
        NumberedExtension ne = new NumberedExtension(rse);
        VoterList vl = new VoterList(ne);
        ne.setList(vl);

        return vl;
    }
}
