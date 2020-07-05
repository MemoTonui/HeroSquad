package model;

import java.util.ArrayList;

public class Squad {
    private  String squadName;
    private int maxSize;
    private String description;
    private static ArrayList<Squad> instances = new ArrayList<>();
    private  ArrayList<Hero> heroes;
    private int id;
    private int heroesSize;



    public Squad(String squadName,int maxSize,String description) {
        this.squadName=squadName;
        this.maxSize =maxSize;
        this.description = description;
        instances.add(this);
        this.id = instances.size();
        heroes = new ArrayList<Hero>();
        heroesSize=0;

    }

    public static ArrayList<Squad> getInstances() {
        return instances;
    }

    public int getId() {
        return id;
    }
    public static Squad findById(int id){
        return instances.get(id-1);
    }

    public ArrayList<Hero> getHeroes(){
        return heroes;
    }
    public int getHeroesSize(){
        return heroesSize;
    }
    public void addHero(Hero hero) {
        heroes.add(hero);
        heroesSize++;
    }


    public String getSquadName() {
        return squadName;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public String getDescription(){
        return description;
    }
}
