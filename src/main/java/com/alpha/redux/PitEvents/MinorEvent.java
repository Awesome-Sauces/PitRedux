package com.alpha.redux.PitEvents;

import com.alpha.redux.UpgradesNpc.perks.PerkExecute;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import org.bukkit.Material;

public abstract class MinorEvent {

    private String name;
    private String refID;
    private Hologram hologram1;
    private Hologram hologram2;
    private Hologram hologram3;
    private int time;

    public MinorEvent(){}

    public MinorEvent(String refID, String name, int time){
        this.refID = refID;
        this.name = name;
        this.time = time;
    }

    public abstract EventExecute getEventExecute();

    public abstract void loadHolograms();

    public abstract void deleteHolograms();

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Hologram getHologram1() {
        return hologram1;
    }

    public void setHologram1(Hologram hologram1) {
        this.hologram1 = hologram1;
    }

    public Hologram getHologram2() {
        return hologram2;
    }

    public void setHologram2(Hologram hologram2) {
        this.hologram2 = hologram2;
    }

    public Hologram getHologram3() {
        return hologram3;
    }

    public void setHologram3(Hologram hologram3) {
        this.hologram3 = hologram3;
    }
}
