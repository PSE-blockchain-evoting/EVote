package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ComponentExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;

import javax.swing.JLabel;

public class LabelExtension extends ComponentExtension<JLabel> {

    private static final String TEXT = "Hello World";

    public LabelExtension(ListExtension next) {
        super(next);
    }

    @Override
    protected JLabel createNewType() {
        return new JLabel(TEXT);
    }
}
