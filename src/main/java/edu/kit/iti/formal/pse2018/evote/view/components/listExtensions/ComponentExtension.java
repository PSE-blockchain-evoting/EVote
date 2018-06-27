package edu.kit.iti.formal.pse2018.evote.view.components.listExtensions;

import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ListExtension;

import javax.swing.*;
import java.util.LinkedList;

public abstract class ComponentExtension<Type extends JComponent> extends ListExtension {

    protected LinkedList<Type> components;

    public ComponentExtension(ListExtension next) {
        super(next);

        components = new LinkedList<>();
    }

    @Override
    public void addEntry(Entry e) {
        Type tNew = createNewType();

        components.add(tNew);
        e.add(tNew);
        super.addEntry(e);
    }

    @Override
    public void removeEntry(Entry e) {
        super.removeEntry(e);
        int i = searchIndex(e);
        removeData(i);
    }

    protected void removeData(int i){
        components.remove(i);
    }

    protected abstract Type createNewType();

    protected int searchIndex(Entry e){
        int n = 0;
        for(Entry e1 : list.getEntries()){
            if(e1 == e){
                return n;
            }
            n++;
        }

        assert(0 <= n && n < components.size());
        assert(components.get(n) == e);

        throw new IllegalStateException("This entry doesn't exist.");
    }
}
