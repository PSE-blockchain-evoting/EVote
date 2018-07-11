package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;

import java.awt.Font;
import javax.swing.JLabel;

/**
 * NumberedExtension will give each Entry a JLabel which displays in which row that specific Entry is.
 */
public class NumberedExtension extends ComponentExtension<JLabel> {

    /**
     * Creates a new NumberedExtension Instance.
     * @param next the next Extension in the chain. Can be null.
     */
    public NumberedExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    public void removeEntry(Entry e) {
        int i = searchIndex(e);
        components.remove(i);

        //Change numbers afterwards
        for (int j = i; j < components.size(); j++) {
            String t = genText(j);
            components.get(j).setText(t);
        }
    }

    @Override
    protected JLabel createNewType() {
        int nr = list.getEntries().size();
        JLabel lblNew = new JLabel(genText(nr));
        lblNew.setFont(font);

        return lblNew;
    }

    private String genText(int i) {
        return (i + 1) + ". ";
    }
}
