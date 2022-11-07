package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Tenacity extends DataStore{
    public Tenacity(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Each tier:\n" +
                "&7Heal &c0.1❤ &7on kill.");
    }
}
