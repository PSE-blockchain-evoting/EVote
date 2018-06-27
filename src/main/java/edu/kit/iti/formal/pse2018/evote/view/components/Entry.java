package edu.kit.iti.formal.pse2018.evote.view.components;

import javax.swing.*;
import java.util.Arrays;

public class Entry extends JPanel {

    public Entry(int i){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    public String toString(){
        return Arrays.toString(super.getComponents());
    }
}
