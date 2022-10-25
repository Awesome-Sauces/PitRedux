package com.alpha.redux.UpgradesNpc.perks;

public abstract class PitPerk {

    private String name;
    private String lore;
    private int cost;
    private int level;
    private String refID;

    public PitPerk(){}

    public PitPerk(String refID, String name, String lore, int cost, int level){
        this.refID = refID;
        this.name = name;
        this.lore = lore;
        this.cost = cost;
        this.level = level;
    }

    public abstract PerkExecute getPerkExecute();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRefID(String refID){
        this.refID = refID;
    }

    public String getRefID(){
        return refID;
    }
}
