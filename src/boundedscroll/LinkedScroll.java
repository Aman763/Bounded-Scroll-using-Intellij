package boundedscroll;

public class LinkedScroll<E> extends AbstractScroll<E>{
    class Node{
        E contents;
        Node next;
        Node previous;

        public Node(E contents) {
            this.contents = contents;
            this.next = null;
            this.previous = null;
        }
    }
    Node guard;
    Node cursor;
    int rlen;
    int llen;

    public LinkedScroll(int max) {
        super(max);
        guard = new Node(null);
        cursor = new Node(null);
        guard.next = guard;
        cursor.next = guard;
        guard.previous = guard;
        cursor.previous = guard;
        rlen = 0;
        llen = 0;
    }

    @Override
    public void insert(E elem) {
        if (elem==null) throw new IllegalArgumentException();
        if(rlen + llen == capacity()) throw new IllegalStateException();
        Node temp = new Node(elem);
        temp.next = cursor.next;
        temp.previous = cursor.previous;
        cursor.previous.next = temp;
        cursor.next.previous = temp;
        cursor.next = temp;
        rlen = rlen +1;
    }

    @Override
    public E delete() {
        if(cursor.next == guard) throw new IllegalStateException();
        Node temp = cursor.next;
        cursor.next = temp.next;
        temp.next.previous = cursor.previous;
        rlen = rlen -1;
        temp.next = null;
        temp.previous=null;
        return temp.contents;
    }

    @Override
    public void advance() {
        if(cursor.next == guard) throw new IllegalStateException();
        cursor.previous = cursor.next;
        cursor.next = cursor.previous.next;
        llen = llen + 1;
        rlen = rlen - 1;

    }

    @Override
    public void retreat() {
        if(cursor.previous == guard) throw new IllegalStateException();
        cursor.next = cursor.previous;
        cursor.previous = cursor.next.previous;
        llen = llen-1;
        rlen= rlen+1;
    }

    @Override
    public void reset() {
        cursor.previous = guard;
        cursor.next = guard.next;
        rlen = rlen +llen;
        llen = 0;
    }

    @Override
    public void advanceToEnd() {
        cursor.next = guard;
        cursor.previous = guard.previous;
        llen = llen +rlen;
        rlen = 0;

    }

    @Override
    public int leftLength() {
        return llen;
    }

    @Override
    public int rightLength() {
        return rlen;
    }

    @Override
    public Scroll<E> newInstance() {
        Scroll<E> abc = new LinkedScroll<>(capacity());
        return abc;
    }

    @Override
    public void swapRights(Scroll<E> that) {
        super.swapRights(that);
    }
}
