package edu.kit.iti.formal.pse2018.evote.view.components.listExtensions;

import edu.kit.iti.formal.pse2018.evote.view.components.ListExtension;

import javax.swing.*;

public class RadioSelectionExtension extends ComponentExtension<JRadioButton>{

    private ButtonGroup group;

    public RadioSelectionExtension(ListExtension next) {
        super(next);
        group = new ButtonGroup();
    }

    @Override
    protected JRadioButton createNewType() {
        JRadioButton rb = new JRadioButton();
        group.add(rb);

        return rb;
    }

    public int getSelection(){
        ButtonModel bm = group.getSelection();
        if(bm == null){
            return -1;
        }

        for(int i = 0; i < components.size(); i++){
            if(components.get(i).getModel() == bm){
                return i;
            }
        }
        throw new IllegalStateException("selected ButtonModel is not in radiobutton list");
    }
}
