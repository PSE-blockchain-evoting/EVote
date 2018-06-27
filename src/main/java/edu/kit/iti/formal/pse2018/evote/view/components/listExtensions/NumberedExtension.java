package edu.kit.iti.formal.pse2018.evote.view.components.listExtensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ListExtension;

import javax.swing.*;

public class NumberedExtension extends ComponentExtension<JLabel>{

    public NumberedExtension(ListExtension next){
        super(next);
    }

    @Override
    public void removeEntry(Entry e) {
        int i = searchIndex(e);
        components.remove(i);

        //Change numbers afterwards
        for(int j = i; j < components.size(); j++){
            String t = genText(j);
            components.get(j).setText(t);
        }
    }

    @Override
    protected JLabel createNewType() {
        int nr = list.getEntries().size();
        JLabel lblNew = new JLabel(genText(nr));

        return lblNew;
    }

    private String genText(int i){
        return (i+1) + ". ";
    }
}
