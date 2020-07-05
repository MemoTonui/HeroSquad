package model;

import java.util.ArrayList;

public class Hero {
    private String heroName;
    private int age;
    private String superPower;
    private String weakness;
    private static ArrayList<Hero> heroes= new ArrayList<>();
    private int id;

    public Hero(String heroName,int age,String superPower,String weakness) {

        this.heroName=heroName;
        this.age = age;
        this.superPower= superPower;
        this.weakness = weakness;
        heroes.add(this);
        id= heroes.size();


    }



    public String getHeroName() {
        return heroName;
    }

    public int getAge() {
        return age;
    }

    public String getSuperPower() {
        return superPower;
    }

    public String getWeakness() {
        return weakness;
    }

    public static ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public int getId() {
        return id;
    }
    public static Hero findById(int id){
        return heroes.get(id-1);
    }
}
