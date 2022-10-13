package com.alpha.redux.apis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

public class locations {

    /*
    private static Location cake = new Location(Bukkit.getWorld("world"), 57.5, 86, 66.5);


    public static Location getSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), 0.5, 114, 10.5);
    }

    public static Location getBotSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), 0.5, 95, 0.5);
    }

    public static Location getLeaderBoardLocation(){
        return new Location(Bukkit.getWorld("world"), 13.5, 119.5, 0.5);
    }

    public static int getSpawnProtection() {return 112;}

    */

    // Metor Map Locations
    public static Location getSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 161, 4.5);
    }

    public static Location getEventNotifyLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 164, 12.5);
    }

    public static Location getBotSpawnLocation(){
        return new Location(Bukkit.getWorld("world"), -2.5, 145, 12.5);
    }
    public static Location getLeaderBoardLocation(){
        return new Location(Bukkit.getWorld("world"), -14.5, 167, 12.5);
    }

    public static Location getEnderChestLocation(){return new Location(Bukkit.getWorld("world"), 8.5,163.5,7.5);}
    public static Location getMysticWellLocation(){return new Location(Bukkit.getWorld("world"), 9.5,164.5,12.5);}

    public static int getSpawnProtection() {return 156;}


    /*public static Location getCakeLocation(){
        return cake;
    }

     */

    /*
    public static void changeCakeLocation(){
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        int int_random = rand.nextInt(upperbound);

        switch (int_random){
            case 0:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 57.5, 86, 66.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 1:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 6.5, 84, 113.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 2:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -59.5, 97, 105.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 3:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -105.5, 96, 23.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 4:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -64.5, 85, -63.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 5:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -5.5, 88, -74.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 6:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 66.5, 78, -53.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 7:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 95.5, 86, 36.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 8:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 92.5, 96, 6.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 9:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 21.5, 112, -69.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
        }
    }

     */

    // Metor Map Cake locations


    private static Location cake = new Location(Bukkit.getWorld("world"), -9.5, 137, 38.5);

    public static Location perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -1.5, 161, 1.5);
    public static Location non_perm_upgrades_loc = new Location(Bukkit.getWorld("world"), -4.5, 161, 1.5);

    public static Location leaderboard_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 8.5);
    public static Location quest_npc_loc = new Location(Bukkit.getWorld("world"), -12.5, 161, 16.5);

    public static Location prestige_npc_loc = new Location(Bukkit.getWorld("world"), -2.5, 162, 23.5);

    public static Location getCakeLocation(){
        return cake;
    }

    public static void changeCakeLocation(){
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        int int_random = rand.nextInt(upperbound);

        switch (int_random){
            case 0:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -47.5, 118, 60.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 1:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -47.5, 136, -0.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 2:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 47.5, 138, -12.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 3:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 31.5, 120, -38.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 4:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 31.5, 121, -29.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 5:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -58.5, 121, 25.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 6:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 40.5, 129, 42.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 7:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 11.5, 124, 16.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 8:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), -15.5, 124, 6.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
            case 9:
                cake.getBlock().setType(Material.AIR);
                cake = new Location(Bukkit.getWorld("world"), 4.5, 130, -24.5);
                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "The cake has changed location!");
                getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);
                break;
        }
    }

     //
}
