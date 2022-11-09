package com.alpha.redux.renownShop.DataStorage;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Promotion extends DataStore{
    public Promotion(String refID) {
        super(refID);
    }

    public String getLore(){
        return colorCode("&7Upgrade the \"Assistant (to the)\n" +
                "&7Streak\" perk:\n\n" +
                "&aAssistant Streak\n" +
                "&7Earn &6+2g &7and &b+15% XP\n" +
                "&7from kills and assists.\n\n" +
                "&7Gain &e+1 &7streak every &c4th\n" +
                "&ckill&7.");
    }
}
