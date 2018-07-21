package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JRadioButton;

/**
 * RadioSelectionExtension adds an RadioButton to each Entry. All RadioButtons belong to same Group.
 */
public class RadioSelectionExtension extends ComponentExtension<JRadioButton> {

    private ButtonGroup group;
    private Icon selected;
    private Icon unselected;

    public RadioSelectionExtension(ListExtension next, Font font) {
        super(next, font);
        group = new ButtonGroup();
    }

    /**
     * Creates an instance of RadioSelectionExtension, where custom icon are used.
     *
     * @param next       The next ListExtension.
     * @param font       The Font to be used.
     * @param selected   The Icon for when a radiobutton is selected.
     * @param unselected The Icon for when a radiobutton is not selected.
     */
    public RadioSelectionExtension(ListExtension next, Font font, Icon selected, Icon unselected) {
        super(next, font);
        group = new ButtonGroup();
        this.selected = selected;
        this.unselected = unselected;
    }

    @Override
    protected JRadioButton createNewType() {
        JRadioButton rb;
        if (selected != null && unselected != null) {
            rb = new JRadioButton("", unselected);
            rb.setSelectedIcon(selected);
        } else {
            rb = new JRadioButton("");
        }

        rb.setOpaque(false);
        rb.setContentAreaFilled(false);
        rb.setBorderPainted(false);

        rb.setFont(font);
        group.add(rb);


        return rb;
    }

    /**
     * Get the index of the current selected RadioButton.
     *
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

    /**
     * Set the selected RadioButton.
     *
     * @param index The index to be set.
     */
    public void setSelection(int index) {
        components.get(index).setSelected(true);
    }

    /**
     * Enable or disable the JRadiobuttons.
     *
     * @param b Enable/Disable.
     */
    public void setEnabled(boolean b) {
        for (JRadioButton rb : components) {
            rb.setEnabled(b);
        }
    }
}
