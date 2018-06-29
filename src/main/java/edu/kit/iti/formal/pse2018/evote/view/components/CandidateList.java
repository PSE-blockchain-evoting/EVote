package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.DescriptionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.RemovableExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextFieldExtension;

import javax.swing.JFrame;

/**
 * CandidateList provides a configured ExtendableList with the purpose of adding
 * and removing Candidates and giving each Candidate a description.
 */
public class CandidateList extends ExtendableList {

    private CandidateList(ListExtension e) {
        super(e);
    }

    /**
     * Creates a Table for adding and removing Candidates.
     * @param parent parent JFrame for DescriptionExtensions JDialogs.
     * @return a configured instance of ExtendableList
     */
    public static CandidateList createCandidateList(JFrame parent) {
        NumberedExtension ne = new NumberedExtension(
                                new TextFieldExtension(
                                new DescriptionExtension(
                                new RemovableExtension(null), parent)));
        CandidateList cl = new CandidateList(ne);
        ne.setList(cl);
        return cl;
    }
}
