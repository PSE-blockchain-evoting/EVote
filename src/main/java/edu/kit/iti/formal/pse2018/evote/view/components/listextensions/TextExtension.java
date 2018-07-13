package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 * TextExtension adds an JLabel to each Entry with a predefined String.
 */
public class TextExtension extends ComponentExtension<JLabel> {

    private List<String> text;

    /**
     * Creates an instance of TextExtension.
     *
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     * @param text The Text the labels show. The first element in text ist shown in the first row,
     *             the second in the second row, etc.
     */
    public TextExtension(ListExtension next, Font font, String[] text) {
        super(next, font);
        if (text == null) {
            this.text = new ArrayList<String>();
        } else {
            this.text = new ArrayList<String>();
            for (int i = 0; i < text.length; i++) {
                this.text.add(text[i]);
            }
        }
    }

    @Override
    protected JLabel createNewType() {
        int size = components.size();
        JLabel lbl;
        if (size >= text.size()) {
            lbl = new JLabel("");
        } else {
            lbl = new JLabel(text.get(size));
        }
        lbl.setFont(font);
        return lbl;
    }

    @Override
    public void addEntry(Entry e) {
        super.addEntry(e);
        text.add("");
    }

    @Override
    public void removeEntry(Entry e) {
        super.removeEntry(e);
        int index = searchIndex(e);

        for (int i = index; i < components.size(); i++) {
            if (i < text.size()) {
                components.get(i).setText(text.get(i));
            }
        }
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
        while (i >= this.text.size()) {
            this.text.add("");
        }

        this.text.set(i, text);
        components.get(i).setText(text);
    }
}
