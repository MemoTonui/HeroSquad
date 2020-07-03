package model;

public class Squad {
    private  String squadName;
    private int maxSize;
    private String description;



    public Squad(String squadName,int maxSize,String description) {
        this.squadName=squadName;
        this.maxSize =maxSize;
        this.description = description;
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
