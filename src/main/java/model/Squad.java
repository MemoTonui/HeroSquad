package model;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private  String squadName;
    private int maxSize;
    private String description;
    private static List<Squad> instances = new ArrayList<>();
    private  List<Hero> heroes;
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

    public static List<Squad> getInstances() {
        return instances;
    }

    public int getId() {
        return id;
    }
    public static Squad findById(int id){
        return instances.get(id-1);
    }

    public List<Hero> getHeroes(){
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
