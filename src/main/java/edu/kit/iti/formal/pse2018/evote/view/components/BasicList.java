package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BasicList extends JPanel {

    protected JPanel pnlScroll;
    protected LinkedList<Entry> entries;

    /**
     *  Creates a very basic List
     *  NOTE: This Class shouldn't be used. Use ExtendableList instead.
     */
    public BasicList(){
        this.setLayout(new BorderLayout());
        pnlScroll = new JPanel();

        GridLayout gl = new GridLayout(0, 1);
        pnlScroll.setLayout(gl);

        JPanel p = new JPanel(new BorderLayout());
        p.add(pnlScroll, BorderLayout.NORTH);

        JScrollPane scp = new JScrollPane(p);
        this.add(scp);

        entries = new LinkedList<>();
    }
}
