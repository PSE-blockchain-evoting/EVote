package edu.kit.iti.formal.pse2018.evote.view.components;

public abstract class ListExtension {

    protected ListExtension next;
    protected Extendable list;

    public ListExtension(ListExtension next){
        this.list = list;
        this.next = next;
    }

    public void addEntry(Entry e){
        if(next == null){
            list.addEntry(e);
        }else {
            next.addEntry(e);
        }
    }

    public void removeEntry(Entry e){
        if(next == null){

        }else{
            next.removeEntry(e);
        }
    }

    public final void setList(Extendable list){
        if(list == null){
            throw new IllegalArgumentException("List is Null");
        }
        this.list = list;
        if(next != null){
            next.setList(list);
        }
    }

}
