package boundedscroll;

import java.util.Iterator;
import java.util.Stack;

public class StackScroll <E> extends AbstractScroll<E> {

    Stack<E> left,right;

    public StackScroll(int max) {
        super(max);
        left= new Stack<>();
        right=new Stack<>();
    }



    @Override
    public void insert(E elem) {
        if (elem==null) throw new IllegalArgumentException();
        if(left.size() + right.size() == capacity()) throw new IllegalStateException();

        right.push(elem);
    }

    @Override
    public E delete() {
        if(right.isEmpty()) throw new IllegalStateException();

        return right.pop();
    }

    @Override
    public void advance() {
        if(right.isEmpty()) throw new IllegalStateException();

        left.push(right.pop());
    }

    @Override
    public void retreat() {
        if(left.isEmpty()) throw new IllegalStateException();{

        right.push(left.pop());
        }
    }

    @Override
    public void reset() {
        if(left.isEmpty()){
            return;
        }
        while (leftLength() != 0) {
            retreat();
        }
    }
    @Override
    public void advanceToEnd() {
        if(right.isEmpty()){
            return;
        }
        while (rightLength() != 0) {
            advance();
        }
    }


    @Override
    public int leftLength() {
        return left.size();
    }

    @Override
    public int rightLength() {
        return right.size();
    }

    @Override
    public Scroll<E> newInstance() {
        Scroll<E> abc = new StackScroll<>(capacity());
        return abc;
    }


    @Override
    public void swapRights(Scroll<E> that) {
        if(that == null) throw new IllegalArgumentException();
        if(this.leftLength() + that.rightLength() > this.capacity() || that.leftLength() + this.rightLength() > that.capacity()) throw new IllegalStateException();
        if (that instanceof StackScroll) {
            StackScroll<E> stackScrollThat = (StackScroll<E>) that;
            Stack<E> temp = this.right;
            this.right = stackScrollThat.right;
            stackScrollThat.right = temp;
        } else {
            super.swapRights(that);
        }
    }
}


