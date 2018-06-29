package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TabListener is used in VerticalTabs to listen, which tab has been pressed by the user.
 */
public class TabListener implements ActionListener {
    private VerticalTabs vt;
    private int id;

    public TabListener(int id, VerticalTabs vt){
        this.vt = vt;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        vt.setSelected(id);
    }
}
