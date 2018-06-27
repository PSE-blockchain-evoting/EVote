package edu.kit.iti.formal.pse2018.evote.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabListener implements ActionListener {
    private VerticalTabs vt;
    private int id;

    public TabListener(int id, VerticalTabs vt){
        this.vt = vt;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        vt.setSelected(id);
    }
}
