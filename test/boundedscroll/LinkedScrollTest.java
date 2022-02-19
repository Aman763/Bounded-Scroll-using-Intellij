package boundedscroll;

import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

import static org.junit.Assert.*;

public class LinkedScrollTest {

    LinkedScroll<String> ab_cd_6;
    LinkedScroll<String> lm_no_6;
    LinkedScroll<String> uv_xy_6;


    @Before
    public void setUp() throws Exception {
        ab_cd_6 = new LinkedScroll<>(6);
        ab_cd_6.insert("D");
        ab_cd_6.insert("C");
        ab_cd_6.insert("B");
        ab_cd_6.insert("A");
        ab_cd_6.reset();
        ab_cd_6.advance();
        ab_cd_6.advance();
        lm_no_6 = new LinkedScroll<>(6);
        lm_no_6.insert("O");
        lm_no_6.insert("N");
        lm_no_6.insert("M");
        lm_no_6.insert("L");
        lm_no_6.reset();
        lm_no_6.advance();
        lm_no_6.advance();
        uv_xy_6 = new LinkedScroll<>(6);
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

    }
    @Test
    public void testLength(){
        assertEquals(2,ab_cd_6.leftLength());
        assertEquals(2,ab_cd_6.rightLength());
        assertEquals(6,ab_cd_6.capacity());

    }

    @Test
    public void T(){
        assertEquals("C",ab_cd_6.getNext());
        assertEquals("B",ab_cd_6.getPrevious());

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
        assertEquals(3,lm_no_6.leftLength());
        assertEquals(1,lm_no_6.rightLength());

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
        fail();
    }

}


