package com.alpha.redux.boosters;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.apis.locations;
import com.alpha.redux.redux;
import me.alpha.hunter.api.HunterAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.funEvents.event.twoTimesEvent;

public class Booster {

    public static boolean xpActive = false;
    public static boolean goldActive = false;
    public static boolean botActive = false;

    public static ItemStack XpBoosterItem(String uuid){
        return ItemMaker(Material.EXP_BOTTLE, ChatColor.RED + "XP Booster",
                ChatColor.GRAY + "All players on the server gain\n" + ChatColor.AQUA +
                "2x XP " + ChatColor.GRAY + " and " + ChatColor.AQUA + "2x map XP\n\n" +
                ChatColor.GRAY + "Active: " + ChatColor.YELLOW + xpActive + "!\n" +
                ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                "You have: " + ChatColor.YELLOW + XpBoosterData.getXpBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                "Click to activate booster!", 1, true);
    }

    public static ItemStack GoldBoosterItem(String uuid){
        return ItemMaker(Material.GOLD_INGOT, ChatColor.RED + "Gold Booster",
                ChatColor.GRAY + "All players on the server gain\n" + ChatColor.AQUA +
                        "2x gold\n\n" + ChatColor.GRAY + "Active: " + ChatColor.YELLOW + goldActive + "!\n" +
                        ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                        "You have: " + ChatColor.YELLOW + GoldBoosterData.getGoldBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                        "Click to activate booster!", 1, true);
    }

    public static ItemStack BotBoosterItem(String uuid){
        return ItemMaker(Material.IRON_SWORD, ChatColor.RED + "Bot Booster",
                ChatColor.GRAY + "Spawns in +30 more bots for\n" + ChatColor.GRAY +
                        "the whole server to enjoy\n\n" +
                        ChatColor.GRAY + "Active: " + ChatColor.YELLOW + botActive + "!\n" +
                        ChatColor.GRAY + "Use a booster to activate\n\n" + ChatColor.GRAY +
                        "You have: " + ChatColor.YELLOW + BotBoosterData.getBotBooster(uuid) + "\n\n" + ChatColor.YELLOW +
                        "Click to activate booster!", 1, true);
    }

    public static Inventory getBoosterGUI(Player player){
        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Boosters");

        ItemStack base_glass = advancedInventory.cGlass();
        ItemStack bot = BotBoosterItem(String.valueOf(player.getUniqueId()));
        ItemStack gold = GoldBoosterItem(String.valueOf(player.getUniqueId()));
        ItemStack xp = XpBoosterItem(String.valueOf(player.getUniqueId()));

        for (int i = 0; i < 10; i++) {
            advancedInventory.addInv(gui, base_glass, i, 1, false);
            advancedInventory.addInv(gui, base_glass, i, 2, false);
            advancedInventory.addInv(gui, base_glass, i, 3, false);
        }

        advancedInventory.addInv(gui, xp, 3, 2, false);
        advancedInventory.addInv(gui, bot, 5, 2, false);
        advancedInventory.addInv(gui, gold, 7, 2, false);

        return gui;
    }

    public static void purchaseBoosterCommand(String type, String uuid, int amount){
        switch (type){
            case "GOLD":
                GoldBoosterData.hasGoldBooster(uuid);
                GoldBoosterData.addGoldBooster(uuid, amount);
                break;
            case "BOT":
                BotBoosterData.hasBotBooster(uuid);
                BotBoosterData.addBotBooster(uuid, amount);
                break;
            default:
                XpBoosterData.hasXpBooster(uuid);
                XpBoosterData.addXpBooster(uuid, amount);
                break;
        }
    }

    public static void constantBoosterNotify(){
        new BukkitRunnable() {

            @Override
            public void run() {
                if(goldActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l2x &6gold &7booster!"));

                }

                if(xpActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l2x &bxp &7booster!"));

                }

                if(botActive){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if(!isNPC(player)){
                            Sounds.BOOSTER_REMIND.play(player.getLocation(), 1);
                        }
                    }

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                            "&7active &e&l+30 &cbot &7booster!"));

                }

            }
        }.runTaskTimer(redux.INSTANCE, 3600, 3600);
    }

    public static boolean activateBooster(Player player, String booster){
        String uuid = String.valueOf(player.getUniqueId());

        switch (booster){
            case "GOLD":
                GoldBoosterData.hasGoldBooster(uuid);
                if(GoldBoosterData.getGoldBooster(uuid) <= 0 || goldActive){return true;}

                GoldBoosterData.removeGoldBooster(uuid, 1);

                goldActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l2x &6gold &7booster!"));

                Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        goldActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;
            case "BOT":
                BotBoosterData.hasBotBooster(uuid);
                if(BotBoosterData.getBotBooster(uuid) <= 0 || botActive){return true;}

                BotBoosterData.removeBotBooster(uuid, 1);

                botActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l+30 &cbot &7booster!"));

                for (int i = 0; i < 30; i++) {
                    HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("world")), 3600, false);
                    HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 3600, false);
                    HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 3600, false);
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        botActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;
            default:
                XpBoosterData.hasXpBooster(uuid);
                if(XpBoosterData.getXpBooster(uuid) <= 0 || xpActive){return true;}

                XpBoosterData.removeXpBooster(uuid,1);

                xpActive = true;

                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lBOOSTER! &7There is currently an " +
                        "&7active &e&l2x &bxp &7booster!"));

                Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        xpActive = false;

                        Bukkit.broadcastMessage(colorCode("&e&lBOOSTER &7ended, all things will now return to normal"));
                    }
                }, 72000L);

                return false;

        }
    }

}
