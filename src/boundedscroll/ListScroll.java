package boundedscroll;

import java.util.ArrayList;
import java.util.List;

public class ListScroll<E> extends AbstractScroll<E>{
    List<E> list;
    int pos;

    public ListScroll(int max) {
        super(max);
        list = new ArrayList<>();
        pos = 0;

    }

    @Override
    public void insert(E elem) {
        if (elem==null) throw new IllegalArgumentException();
        if(list.size() == capacity()) throw new IllegalStateException();
        list.add(pos,elem);

    }

    @Override
    public E delete() {
        if(list.size() == pos) throw new IllegalStateException();
        return list.remove(pos);
    }

    @Override
    public void advance() {
        if(list.size() == pos) throw new IllegalStateException();
        pos = pos + 1;
    }

    @Override
    public void retreat() {
        if(leftLength() == 0) throw new IllegalStateException();
        pos= pos -1;

    }

    @Override
    public void reset() {
        if(pos == 0){
            return;
        }
        else{
            pos = 0;
        }

    }

    @Override
    public void advanceToEnd() {
        if(pos == list.size()){
            return;
        }
        else{
            pos = list.size();
        }

    }

    @Override
    public int leftLength() {
        return pos;
    }

    @Override
    public int rightLength() {
        return list.size() - pos;
    }

    @Override
    public Scroll<E> newInstance() {
        Scroll<E> abc = new ListScroll<>(capacity());
        return abc;
    }

    @Override
    public void swapRights(Scroll<E> that) {
        super.swapRights(that);
    }
}
