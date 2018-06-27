package edu.kit.iti.formal.pse2018.evote.view.components;

public abstract class ListExtension {

    protected ListExtension next;
    protected Extendable list;

    public ListExtension(ListExtension next){
        this.list = list;
        this.next = next;
    }

    /**
     * An Entry is added to list. The ListExtension can modify it.
     * @param e the Entry that is added.
     */
    public void addEntry(Entry e) {
        if (next == null) {
            list.addEntry(e);
        } else {
            next.addEntry(e);
        }
    }

    /**
     * An Entry is removed to List. The ListExtension is notified so it can modify its state if needed.
     * @param e The Entry that is to be removed.
     */
    public void removeEntry(Entry e){
        if (next == null) {

        } else {
            next.removeEntry(e);
        }
    }

    /**
     * Setting the List that is this ListExtension belongs to.
     * @param list
     */
    public final void setList(ExtendableList list){
        if (list == null) {
            throw new IllegalArgumentException("List is Null");
        }
        this.list = list;
        if (next != null) {
            next.setList(list);
        }
    }

}
