package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Font;
import javax.swing.JLabel;

/**
 * LabelExtension will add a JLabel on each Entry. Every JLabel will show the same text.
 */
public class LabelExtension extends ComponentExtension<JLabel> {

    private static final String TEXT = "Hello World";
    private String text;

    public LabelExtension(ListExtension next, Font font, String text) {
        super(next, font);
        this.text = text;
    }

    @Override
    protected JLabel createNewType() {
        JLabel lbl = new JLabel(TEXT);
        lbl.setFont(font);
        return lbl;
    }
}
