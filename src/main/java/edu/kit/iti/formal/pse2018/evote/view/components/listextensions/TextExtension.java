package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;

import javax.swing.JLabel;

/**
 * TextExtension adds an JLabel to each Entry with a predefined String.
 */
public class TextExtension extends ComponentExtension<JLabel>{

    private String[] text;

    /**
     * Creates an instance of TextExtension
     *
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     * @param text The Text the labels show. The first element in text ist shown in the first row,
     *             the second in the second row, etc.
     */
    public TextExtension(ListExtension next, String[] text) {
        super(next);
        this.text = text;
    }

    @Override
    protected JLabel createNewType() {
        int size = components.size();
        if (size >= text.length)
            return new JLabel("");
        else
            return new JLabel(text[size]);
    }

    @Override
    public void removeEntry(Entry e) {
        super.removeEntry(e);
        int index = searchIndex(e);

        for (int i = index; i < components.size(); i++) {
            if (i < text.length)
                components.get(i).setText(text[i]);

        }
    }
}
