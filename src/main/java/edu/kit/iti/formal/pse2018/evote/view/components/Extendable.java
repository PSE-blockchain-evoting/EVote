package edu.kit.iti.formal.pse2018.evote.view.components;

import java.util.List;

/**
 * For a List to support extension in form of ListExtension has to implement this interface.
 */
public interface Extendable {

    void addEntry(Entry e);

    void addNewEntry();

    void removeEntry(Entry e);

    List<Entry> getEntries();
}
