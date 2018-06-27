package edu.kit.iti.formal.pse2018.evote.view.components;

import java.util.List;

public class ExtendableList extends BasicList implements Extendable {

    protected ListExtension mod;

    public ExtendableList(ListExtension mod){
        super();
        this.mod = mod;
    }

    @Override
    public void addEntry(Entry e) {
        entries.add(e);
        pnlScroll.add(e);
        refreshUI();
    }

    @Override
    public void addNewEntry() {
        Entry e = new Entry(entries.size());
        mod.addEntry(e);
    }

    @Override
    public void removeEntry(Entry e) {
        mod.removeEntry(e);
        entries.remove(e);
        pnlScroll.remove(e);
        refreshUI();
    }

    @Override
    public List<Entry> getEntries() {
        return entries;
    }

    private void refreshUI(){
        if(pnlScroll.getRootPane() != null){
            pnlScroll.getRootPane().validate();
            pnlScroll.getRootPane().repaint();
        }
    }
}
