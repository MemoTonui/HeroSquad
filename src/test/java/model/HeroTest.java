package model;

import org.junit.Test;

import java.awt.print.Pageable;

import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void Hero_InstantiatesHeroCorectly_true() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertTrue(hero instanceof Hero);
    }

    @Test
    public void Hero_getsHeroNAme_String() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertEquals("Super Man",hero.getHeroName());
    }

    @Test
    public void Hero_getsHeroAge_Integer() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertEquals(22,hero.getAge());
    }

    @Test
    public void getSuperPower_getsHeroSuperPower_Integer() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertEquals("Fly",hero.getSuperPower());
    }

    @Test
    public void getWeakness_getsHeroWeakness_Integer() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertEquals("Kryptonite",hero.getWeakness());
    }

    @Test
    public void getHeroes_getsAllHeroesInSquad() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        assertEquals(1,Hero.getHeroes().size());
    }

    @Test
    public void GetsAllHeroesIfHeroesAreMoreThanOne() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        Hero hero2 = new Hero("Spider Man", 35,"Spider Sense","Ethyl Chloride pesticide");
        assertEquals(2,Hero.getHeroes().size());
    }

    @Test
    public void getHeroes_ContainsAllInstances_true() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        Hero hero2 = new Hero("Spider Man", 35,"Spider Sense","Ethyl Chloride pesticide");
        assertTrue(Hero.getHeroes().contains(hero));
        assertTrue(Hero.getHeroes().contains(hero));
    }

    @Test
    public void ReturnsCorrectId() {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        Hero hero2 = new Hero("Spider Man", 35,"Spider Sense","Ethyl Chloride pesticide");
        assertEquals(1,Hero.findById(hero.getId()).getId());
        assertEquals(2,Hero.findById(hero2.getId()).getId());
    }

    @Test
    public void deleteDeletesASpecificHero() throws Exception {
        Hero hero = new Hero("Super Man",22,"Fly","Kryptonite");
        Hero hero2 = new Hero("Spider Man", 35,"Spider Sense","Ethyl Chloride pesticide");
        hero.deleteHero();
        assertEquals(1, Hero.getHeroes().size()); //one is left
        assertEquals(Hero.getHeroes().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }
}