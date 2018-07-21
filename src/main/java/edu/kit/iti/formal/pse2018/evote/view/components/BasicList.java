package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 * BasicList is a very basic implementation of
 * NOTE: This Class can't be used alone. Use ExtendableList instead.
 */
public class BasicList extends JPanel {

    protected JPanel pnlScroll;
    protected JScrollPane spc;
    protected LinkedList<Entry> entries;

    /**
     * Creates a very basic List.
     */
    public BasicList() {
        this.setLayout(new BorderLayout());
        pnlScroll = new JPanel();

        GridLayout gl = new GridLayout(0, 1);
        pnlScroll.setLayout(gl);

        JPanel p = new JPanel(new BorderLayout());
        p.add(pnlScroll, BorderLayout.NORTH);

        spc = new JScrollPane(p);
        this.add(spc);

        entries = new LinkedList<>();
    }

    /**
     * Gives the number entries in the list.
     *
     * @return The number of Entries in this list.
     */
    public int getEntryCount() {
        return entries.size();
    }

    @Override
    public void setBorder(Border border) {
        if (spc != null) {
            spc.setBorder(border);
        }
    }
}
