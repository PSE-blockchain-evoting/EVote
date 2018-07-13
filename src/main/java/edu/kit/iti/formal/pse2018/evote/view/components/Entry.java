package edu.kit.iti.formal.pse2018.evote.view.components;

import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * This class represents a row in a BasicList.
 */
public class Entry extends JPanel {

    public Entry(int i) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    public String toString() {
        return Arrays.toString(super.getComponents());
    }
}
