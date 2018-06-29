package edu.kit.iti.formal.pse2018.evote.view.components.listextensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 * Removable Extension adds a JButton to each entry which, when clicked, will remove that specific Entry.
 */
public class RemovableExtension extends ListExtension {

    private LinkedList<JButton> btns;

    /**
     * Creates a RemovableExtension instance
     * @param next
     */
    public RemovableExtension(ListExtension next) {
        super(next);

        btns = new LinkedList<>();
    }

    @Override
    public void addEntry(Entry e) {

        JButton btnNew = new JButton("-");

        btnNew.addActionListener(new EntryActionListener(e));

        btns.add(btnNew);
        e.add(btnNew);

        super.addEntry(e);
    }

    private class EntryActionListener implements ActionListener {

        private Entry e;

        public EntryActionListener(Entry e) {
            this.e = e;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            list.removeEntry(e);

        }
    }
}
