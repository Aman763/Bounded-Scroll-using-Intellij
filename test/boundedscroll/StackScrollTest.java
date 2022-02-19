
package boundedscroll;

        import org.junit.Before;
        import org.junit.Test;

        import java.util.Iterator;
        import java.util.ListIterator;
        import java.util.NoSuchElementException;

        import static org.junit.Assert.*;

public class StackScrollTest {

    Scroll<String> ab_cd_6;
    Scroll<String> lm_no_6;
    Scroll<String> uv_xy_6;
    Scroll<String> p_6;
    Scroll<String> pq_rs_6;


    @Before
    public void setUp() throws Exception {
        ab_cd_6 = new StackScroll<>(6);
        ab_cd_6.insert("D");
        ab_cd_6.insert("C");
        ab_cd_6.insert("B");
        ab_cd_6.insert("A");
        ab_cd_6.reset();
        ab_cd_6.advance();
        ab_cd_6.advance();
        lm_no_6 = new StackScroll<>(6);
        lm_no_6.insert("O");
        lm_no_6.insert("N");
        lm_no_6.insert("M");
        lm_no_6.insert("L");
        lm_no_6.reset();
        lm_no_6.advance();
        lm_no_6.advance();
        uv_xy_6 = new StackScroll<>(6);
        uv_xy_6.insert("Y");
        uv_xy_6.insert("X");
        uv_xy_6.insert("V");
        uv_xy_6.insert("U");
        uv_xy_6.reset();
        uv_xy_6.advance();
        uv_xy_6.advance();
        uv_xy_6.swapRights(lm_no_6);
        uv_xy_6.reset();
        uv_xy_6.advance();
        uv_xy_6.advance();
        lm_no_6.advanceToEnd();
        lm_no_6.retreat();
        String a = "X";
        ab_cd_6.replace(a);
        lm_no_6.reset();
        p_6 = new StackScroll<>(6);
        p_6.insert("P");
        p_6.reset();
        ab_cd_6.splice(p_6);
        ab_cd_6.reset();
        ab_cd_6.reverse();
        lm_no_6.advance();
        lm_no_6.advance();
        pq_rs_6 = new StackScroll<>(6);
        pq_rs_6.insert("S");
        pq_rs_6.insert("R");
        pq_rs_6.insert("Q");
        pq_rs_6.insert("P");
        pq_rs_6.reset();


    }
    @Test
    public void initSetup1(){
        assertEquals(5,ab_cd_6.leftLength());
        assertEquals(0,ab_cd_6.rightLength());
        assertEquals(6,ab_cd_6.capacity());

    }

    @Test
    public void initSetup2(){

        assertEquals("A",ab_cd_6.getPrevious());

    }

    @Test
    public void initSetup3(){
        assertEquals("N",uv_xy_6.getNext());
        assertEquals("V",uv_xy_6.getPrevious());

    }

    @Test
    public void initSetup4(){
        assertEquals(2,uv_xy_6.leftLength());
        assertEquals(2,uv_xy_6.rightLength());
        assertEquals(2,lm_no_6.leftLength());
        assertEquals(2,lm_no_6.rightLength());

    }
    @Test
    public void initSetup5(){
        assertEquals("A",ab_cd_6.getPrevious());

    }
    @Test
    public void initSetup6(){
        assertEquals(5,ab_cd_6.leftLength());
        assertEquals(0,ab_cd_6.rightLength());


    }

    @Test
    public void initSetup7(){
        assertEquals(5,ab_cd_6.leftLength());
        assertEquals(0,ab_cd_6.rightLength());

    }

    @Test
    public void initSetup8(){
        assertEquals(22622457,lm_no_6.hashCode());
    }

    @Test
    public void initSetup9(){
        assertEquals("[L, M][X, Y]:6",lm_no_6.toString());
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void insertinvalidargument() {
        ab_cd_6.insert(null);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void insertinvalidCapacity() {
        ab_cd_6.insert("E");
        ab_cd_6.insert("F");
        ab_cd_6.insert("G");
        fail();
    }
    @Test(expected = IllegalStateException.class)
    public void insertinvalidCapacityDelete() {
        ab_cd_6.delete();
        ab_cd_6.delete();
        ab_cd_6.delete();
        ab_cd_6.delete();
        ab_cd_6.delete();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void insertinvalidCapacityAdvance() {
        ab_cd_6.advance();
        ab_cd_6.advance();
        ab_cd_6.advance();
        ab_cd_6.advance();
        fail();
    }
    @Test(expected = IllegalStateException.class)
    public void insertinvalidCapacityRetreat() {
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        ab_cd_6.retreat();
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void replaceEmptyElement() {
        String a = null;
        ab_cd_6.replace(a);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void replaceLengthInvalid() {
        String a = "X";
        ab_cd_6.advanceToEnd();
        ab_cd_6.replace(a);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void spliceTotalLength() {
        ab_cd_6.splice(pq_rs_6);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void spliceLeftLength() {
        ab_cd_6.splice(lm_no_6);
        fail();
    }
    @Test(expected = IllegalStateException.class)
    public void reverseLength() {
        ab_cd_6.reverse();
        fail();
    }

    @Test
    public void listIter() {
        ListIterator iter = ab_cd_6.listIterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
    @Test
    public void listIterprev() {
        ListIterator iter = ab_cd_6.listIterator();
        while(iter.hasPrevious()){
            System.out.println(iter.previous());
        }

    }
    @Test(expected = UnsupportedOperationException.class)
    public void listIterRemove() {
        ListIterator iter = ab_cd_6.listIterator();
        iter.remove();
        fail();
    }
    @Test(expected = UnsupportedOperationException.class)
    public void listIterset() {
        ListIterator iter = ab_cd_6.listIterator();
        iter.set("A");
        fail();
    }
    @Test(expected = UnsupportedOperationException.class)
    public void listIteradd() {
        ListIterator iter = ab_cd_6.listIterator();
        iter.add("A");
        fail();
    }
    @Test(expected = NoSuchElementException.class)
    public void listIternext() {
        ListIterator iter = ab_cd_6.listIterator();
        iter.next();
        fail();
    }
    @Test(expected = NoSuchElementException.class)
    public void listIterPrevious() {
        ListIterator iter = ab_cd_6.listIterator();
        iter.previous();
        iter.previous();
        iter.previous();
        iter.previous();
        iter.previous();
        iter.previous();
        fail();
    }

}
