package com.alpha.redux.well;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class bowEnchantLores {
    List<String> lore = new ArrayList<>();

    public bowEnchantLores(String enchant) {
        switch (enchant) {
            case "sprintIII":
                assert false;
                this.lore.add("&9Sprint Drain III");
                this.lore.add("&7Arrow shots gran you &eSpeed II");
                this.lore.add("&7(7s) and apply &8Slowness I");
                this.lore.add("&7(3s)");
                break;
            case "sprintII":
                assert false;
                this.lore.add("&9Sprint Drain II");
                this.lore.add("&7Arrow shots gran you &eSpeed I");
                this.lore.add("&7(5s) and apply &8Slowness I");
                this.lore.add("&7(3s)");
                break;
            case "sprintI":
                assert false;
                this.lore.add("&9Sprint Drain");
                this.lore.add("&7Arrow shots gran you &eSpeed I");
                this.lore.add("&7(3s)");
                break;

            case "fttsIII":
                assert false;
                this.lore.add("&9Faster than their shadow III");
                lore.add("&7Hitting &f0 &7shots without");
                lore.add("&7missing grants &eSpeed III &7(7s)");
                break;
            case "fttsII":
                assert false;
                this.lore.add("&9Faster than their shadow II");
                lore.add("&7Hitting &f0 &7shots without");
                lore.add("&7missing grants &eSpeed II &7(5s)");
                break;
            case "fttsI":
                assert false;
                this.lore.add("&9Faster than their shadow");
                lore.add("&7Hitting &f0 &7shots without");
                lore.add("&7missing grants &eSpeed I &7(5s)");
                break;
                
            // ^ All Normal Enchants


            case "mlbIII":
                assert false;
                this.lore.add("&dRARE! &9Mega Longbow III");
                this.lore.add("&7One shot per second, this bow is");
                this.lore.add("&7automatically fully drawn and");
                this.lore.add("&7grants &aJump Boost IV &7(2s)");
                break;
            case "mlbII":
                assert false;
                this.lore.add("&dRARE! &9Mega Longbow II");
                this.lore.add("&7One shot per second, this bow is");
                this.lore.add("&7automatically fully drawn and");
                this.lore.add("&7grants &aJump Boost III &7(2s)");
                break;
            case "mlbI":
                assert false;
                this.lore.add("&dRARE! &9Mega Longbow");
                this.lore.add("&7One shot per second, this bow is");
                this.lore.add("&7automatically fully drawn and");
                this.lore.add("&7grants &aJump Boost II &7(2s)");
                break;


            case "volleyIII":
                assert false;
                this.lore.add("&dRARE! &9Volley III");
                this.lore.add("&7Shoot &f5 arrows &7at once");
                break;
            case "volleyII":
                assert false;
                this.lore.add("&dRARE! &9Volley II");
                this.lore.add("&7Shoot &f4 arrows &7at once");
                break;
            case "volleyI":
                assert false;
                this.lore.add("&dRARE! &9Volley");
                this.lore.add("&7Shoot &f3 arrows &7at once");
                break;

            case "pullIII":
                assert false;
                this.lore.add("&dRARE! &9Pullbow III");
                this.lore.add("&7Hitting a player pulls them and");
                this.lore.add("&7nearby players toward you (8s");
                this.lore.add("&7cooldown)");
                break;
            case "pullII":
                assert false;
                this.lore.add("&dRARE! &9Pullbow II");
                this.lore.add("&7Hitting a player pulls them and");
                this.lore.add("&7nearby players toward you (8s");
                this.lore.add("&7cooldown)");
                break;
            case "pullI":
                assert false;
                this.lore.add("&dRARE! &9Pullbow");
                this.lore.add("&7Hitting a player pulls them toward");
                this.lore.add("&7you (8s cooldown)");
                break;

            case "exIII":
                assert false;
                this.lore.add("&dRARE! &9Explosive III");
                this.lore.add("&7Arrows go BOOM! (5s cooldown)");
                break;
            case "exII":
                assert false;
                this.lore.add("&dRARE! &9Explosive II");
                this.lore.add("&7Arrows go BOOM! (3s cooldown)");
                break;
            case "exI":
                assert false;
                this.lore.add("&dRARE! &9Explosive");
                this.lore.add("&7Arrows go POP! (5s cooldown)");
                break;

            case "teleIII":
                assert false;
                this.lore.add("&dRARE! &9Telebow III");
                this.lore.add("&7Shoot a teleportation");
                this.lore.add("arrow (20s cooldown)");
                break;
            case "teleII":
                assert false;
                this.lore.add("&dRARE! &9Telebow II");
                this.lore.add("&7Shoot a teleportation");
                this.lore.add("arrow (45s cooldown)");
                break;
            case "teleI":
                assert false;
                this.lore.add("&dRARE! &9Telebow");
                this.lore.add("&7Shoot a teleportation");
                this.lore.add("arrow (90s cooldown)");
                break;

        }

        for(String obj : lore) {
            if(lore.contains(obj)) lore.set(lore.indexOf(obj), ChatColor.translateAlternateColorCodes('&', obj));
        }

    }

    public List<String> getLore() {
        for(String obj : lore) if(lore.contains(obj)) lore.set(lore.indexOf(obj), ChatColor.translateAlternateColorCodes('&', obj));
        return this.lore;
    }
}
