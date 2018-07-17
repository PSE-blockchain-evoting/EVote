package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GapExtension extends ComponentExtension<JPanel> {

    /**
     * ComponentExtension is a specialized ListExtension which automatically adds a Type Component
     * to Entries of the list and manages them.
     *
     * @param next The next ListExtension in the chain of ListExtensions. Can be null.
     * @param font The font used.
     */
    public GapExtension(ListExtension next, Font font) {
        super(next, font);
    }

    @Override
    protected JPanel createNewType() {
        JPanel pnl = new TransparentPanel();
        pnl.setSize(new Dimension(10000, 10));
        return pnl;
    }

    private class TransparentPanel extends JPanel {
        @Override
        public void paintComponent(Graphics graphics) {

        }
    }
}
