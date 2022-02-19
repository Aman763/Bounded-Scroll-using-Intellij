package boundedscroll;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ScrollIterator<E> implements ListIterator<E> {

    Scroll<E> scroll;

    public ScrollIterator(Scroll<E> scroll) {
        this.scroll = scroll;
    }
    @Override
    public boolean hasNext() {
        return scroll.rightLength() !=0;
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        E xyz = scroll.getNext();
        scroll.advance();
        return xyz;
    }

    @Override
    public boolean hasPrevious() {
        return scroll.leftLength() !=0;
    }

    @Override
    public E previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        E xyz = scroll.getPrevious();
        scroll.retreat();
        return xyz;
    }

    @Override
    public int nextIndex() {
        return scroll.leftLength();
    }

    @Override
    public int previousIndex() {
        return scroll.leftLength() - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();

    }

    @Override
    public void set(E e) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException();

    }
}
