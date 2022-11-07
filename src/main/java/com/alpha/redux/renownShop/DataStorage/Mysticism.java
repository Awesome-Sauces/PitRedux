package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Mysticism extends DataStore{
    public Mysticism(String refID) {
        super(refID);
    }

    public String getLore(Integer tier){
        return colorCode("&7Next tier:\n" +
                "&7You find mystic items and pants\n" +
                "&d+"+(tier*5)+"% &7more often than\n&7normal.");
    }
}
