package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Font;
import javax.swing.JTextField;

/**
 * TextFieldExtension will add a JTextField to an Entry.
 */
public class TextFieldExtension extends ComponentExtension<JTextField> {

    public TextFieldExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    protected JTextField createNewType() {
        JTextField txf = new JTextField();
        txf.setFont(font);
        return txf;
    }

    /**
     * get the text of an Entry.
     *
     * @param i the index of the Entry.
     * @return The Text in the specified Entry.
     */
    public String getText(int i) {
        assert (0 <= i && i < components.size());
        return components.get(i).getText();
    }

    /**
     * Set the text of an Entry.
     *
     * @param i    the index of the Entry.
     * @param text The text to set.
     */
    public void setText(int i, String text) {
        while (i >= components.size()) {
            list.addNewEntry();
        }
        components.get(i).setText(text);
    }

    /**
     * Allow editing of the Textfields.
     *
     * @param b enable/disable.
     */
    public void setEditable(boolean b) {
        for (JTextField txf : components) {
            txf.setEditable(b);
        }
    }
}
