package edu.kit.iti.formal.pse2018.evote.view.components;

import java.util.List;

public interface Extendable {

    public void addEntry(Entry e);
    public void addNewEntry();
    public void removeEntry(Entry e);
    public List<Entry> getEntries();
}
