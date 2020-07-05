package model;

import org.junit.Test;

import javax.swing.*;

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

    @Test
    public void getsAllInstancesOfSquadsCreated_true() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        Squad squad2 = new Squad("Cooks",5,"Clean Utensils");
        assertEquals(2,Squad.getInstances().size());
    }

    @Test
    public void ContainsAllInstances_true() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        Squad squad2 = new Squad("Cooks",5,"Clean Utensils");
        assertTrue(Squad.getInstances().contains(squad));
        assertTrue(Squad.getInstances().contains(squad2));
    }

    @Test
    public void ReturnCorrectId_Integer() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        Squad squad2 = new Squad("Cooks",5,"Clean Utensils");
        assertEquals(2,Squad.findById(squad2.getId()).getId());

    }

    @Test
    public void addHero_addsHeroToList_true() {
        Squad squad = new Squad("Linda",2,"Fight Crime");
        Hero hero = new Hero("Smartness", 20, "intelligent", "Power failure");
        squad.addHero(hero);
        assertTrue(squad.getHeroes().contains(hero));
    }

}