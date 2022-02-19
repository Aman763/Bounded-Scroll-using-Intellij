package boundedscroll;

import java.util.Iterator;
import java.util.ListIterator;

public abstract class AbstractScroll<E> implements Scroll<E> {

    private final int capacity;

    public AbstractScroll(int max) {
        capacity = max;
    }

    @Override
    public void swapRights(Scroll<E> that) {
        if(that == null) throw new IllegalArgumentException();
        if(this.leftLength() + that.rightLength() > this.capacity() || that.leftLength() + this.rightLength() > that.capacity()) throw new IllegalStateException();
        Scroll<E> temp = this.newInstance();
        temppushpop(this,temp);
        swapping(this,that);
        temppushpop(temp,that);
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ScrollIterator<E>(this);
    }

    @Override
    public E getNext() {
        if(rightLength() == 0) throw new IllegalStateException();
        E result = delete();
        insert(result);
        return result;
    }

    @Override
    public E getPrevious() {
        if(leftLength() == 0) throw new IllegalStateException();
        this.retreat();
        E result = delete();
        insert(result);
        this.advance();
        return result;
    }

    @Override
    public E replace(E element) {
        if(element == null) throw new IllegalArgumentException();
        if(rightLength() == 0) throw new IllegalStateException();
        E abc = this.delete();
        this.insert(element);
        return abc;
    }

    @Override
    public void splice(Scroll<E> that) {
        if(that.leftLength() != 0) throw new IllegalArgumentException();
        if(that.rightLength() + this.leftLength() + this.rightLength() > this.capacity()) throw new IllegalStateException();
        if(that.rightLength() == 0){
            return;
        }
        E element = that.delete();
        this.insert(element);
        this.advance();
        this.splice(that);
    }

    @Override
    public void reverse() {
        if(this.leftLength() != 0) throw new IllegalStateException();
        Scroll<E> temp = newInstance();
        int a = this.rightLength();
        for(int i = 0;i < a; i++){
            temp.insert(this.delete());
        }
        this.swapRights(temp);
        this.advanceToEnd();
    }

    @Override
    public Iterator<E> iterator() {
        return new ScrollIterator<E>(this);
    }


    @Override
    public int hashCode() {
        int res = 19;
        int ll = leftLength();
        int rl = rightLength();

        this.reset();
        int idx = -1;
        for (E element : this) {
            idx++;
            res = 33 * res + element.hashCode() * idx;
        }
        res += capacity();
        res += ll * 5;
        res += rl * 7;
        for (int j = 0; j < rl; j++) {
            retreat();
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Scroll)) return false;
        Scroll<?> that = (Scroll) obj;
        if (this.capacity() != that.capacity()) return false;
        if (this.leftLength() != that.leftLength()) return false;
        if (this.rightLength() != that.rightLength()) return false;
        int thisRSize = this.rightLength();
        int thatRSize = that.rightLength();
        this.reset();
        that.reset();
        Iterator<E> thisIterator = this.iterator();
        Iterator<?> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            E elem = thisIterator.next();
            Object o = thatIterator.next();
            if (!elem.equals(o)) return false;
        }
        for (int j = 0; j < thisRSize; j++) {
            this.retreat();
        }
        for (int j = 0; j < thatRSize; j++) {
            that.retreat();
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int lL = leftLength();
        int rL = rightLength();
        this.reset();
        sb.append("[");
        Iterator<E> iterator = this.iterator();
        if (!iterator.hasNext()) {
            sb.append("][]:");
            sb.append(capacity());
            return sb.toString();
        }
        int i = 0;
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (i == lL - 1) {
                sb.append("][");
            } else if (iterator.hasNext()) {
                sb.append(", ");
            }
            i += 1;
        }
        sb.append("]:");
        sb.append(capacity());
        for (int j = 0; j < rL; j++) {
            retreat();
        }
        return sb.toString();
    }

    public void temppushpop(Scroll<E> other, Scroll<E> temp){
        while(other.rightLength() != 0){
            temp.insert(other.delete());
        }
    }
    public void swapping(Scroll<E> other, Scroll<E> that){
        int right_length = that.rightLength();
        while(that.rightLength() != 0){
            other.insert(that.delete());
            other.advance();
        }
        setPos(other,right_length);
    }
    public void setPos(Scroll<?> scroll, int right_length){
        if(scroll.rightLength() == right_length){
            return;
        }
        else{
            scroll.advanceToEnd();
            for(int i = 0; i<right_length;i++){
                scroll.retreat();
            }
        }
    }


}