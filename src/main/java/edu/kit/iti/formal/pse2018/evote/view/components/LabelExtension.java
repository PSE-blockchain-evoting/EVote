package edu.kit.iti.formal.pse2018.evote.view.components;

import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ComponentExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.ListExtension;

import javax.swing.JLabel;

/**
 * LabelExtension will add a JLabel on each Entry. Every JLabel will show the same text.
 */
public class LabelExtension extends ComponentExtension<JLabel> {

    private static final String TEXT = "Hello World";
    private String text;

    public LabelExtension(ListExtension next, String text) {
        super(next);
        this.text = text;
    }

    @Override
    protected JLabel createNewType() {
        return new JLabel(TEXT);
    }
}
