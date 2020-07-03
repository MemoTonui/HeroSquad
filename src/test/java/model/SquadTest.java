package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    @Test
    public void Squad_InstantiatesCorrectly() {
        Squad squad = new Squad("Avengers",2,"Fight Crime");
        assertEquals(true,squad instanceof Squad);
    }

    @Test
    public void Squad_getNameCorrectly_String() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        assertEquals("Linda",squad.getSquadName());
    }

    @Test
    public void Squad_getMaxSize_Integer() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        assertEquals(2,squad.getMaxSize());
    }

    @Test
    public void Squad_getDescription_String() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        assertEquals("Fight Crime",squad.getDescription());
    }

}