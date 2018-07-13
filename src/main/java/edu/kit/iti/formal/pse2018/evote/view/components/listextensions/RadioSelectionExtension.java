package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;

/**
 * RadioSelectionExtension adds an RadioButton to each Entry. All RadioButtons belong to same Group.
 */
public class RadioSelectionExtension extends ComponentExtension<JRadioButton> {

    private ButtonGroup group;

    public RadioSelectionExtension(ListExtension next, Font font) {
        super(next, font);
        group = new ButtonGroup();
    }

    @Override
    protected JRadioButton createNewType() {
        JRadioButton rb = new JRadioButton();
        rb.setFont(font);
        group.add(rb);

        return rb;
    }

    /**
     * Get the index of the current selected RadioButton.
     * @return The index of the selected RadioButton.
     */
    public int getSelection() {
        ButtonModel bm = group.getSelection();
        if (bm == null) {
            return -1;
        }

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getModel() == bm) {
                return i;
            }
        }
        throw new IllegalStateException("selected ButtonModel is not in radiobutton list");
    }
}
