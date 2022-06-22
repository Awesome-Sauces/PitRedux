package com.alpha.redux.commands.crates;

import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.items.enchants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class crate {

    String crate;
    String broadcast;
    Player player;
    CrateItems items;

    public crate(String crate, String playerName){
        this.crate = crate;
        this.player = Bukkit.getPlayer(playerName);
        this.items = new CrateItems();

        run();

    }

    public void broadcastMessage(){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', broadcast));
    }

    private void run(){
        
        Inventory inventory = player.getInventory();

        if(this.crate.equals("HJP")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l5x Hidden Jewel Pants!";

            ItemStack pants = items.getJewelPant();

            inventory.addItem(pants);
            inventory.addItem(pants);
            inventory.addItem(pants);
            inventory.addItem(pants);
            inventory.addItem(pants);

        }else if(this.crate.equals("HJS")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l5x Hidden Jewel Swords!";

            ItemStack sword = items.getJewelSword();

            inventory.addItem(sword);
            inventory.addItem(sword);
            inventory.addItem(sword);
            inventory.addItem(sword);
            inventory.addItem(sword);

        }else if(this.crate.equals("BLOB")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Pit Blob III!";

            ItemStack blob = items.getPitBlob();

            inventory.addItem(blob);

        }else if(this.crate.equals("PPP")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l64x Philosopher's Cactus!";

            for (int i = 0; i < 64; i++) {
                inventory.addItem(enchants.cactus);
            }

        }else if(this.crate.equals("PPH")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Helmet!";
            ItemStack helmet = items.getDiamondHelmet();
            inventory.addItem(helmet);
        }else if(this.crate.equals("PPC")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Chestplate!";
            ItemStack chestplate = items.getDiamondChestplate();
            inventory.addItem(chestplate);
        }else if(this.crate.equals("PPL")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Leggings!";
            ItemStack leggings = items.getDiamondLeggings();
            inventory.addItem(leggings);
        }else if(this.crate.equals("PPB")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l1x Protection II Diamond Boots!";
            ItemStack boots = items.getDiamondBoots();
            inventory.addItem(boots);
        }else if(this.crate.equals("FSIX")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l16x Funky Feathers!";
            ItemStack feather = items.getFeather();
            for (int i = 0; i < 16; i++) {
                inventory.addItem(feather);
            }
        }else if(this.crate.equals("FEIGHT")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l8x Funky Feathers!";
            ItemStack feather = items.getFeather();
            for (int i = 0; i < 8; i++) {
                inventory.addItem(feather);
            }
        }else if(this.crate.equals("VSTACK")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l64x Vile!";
            ItemStack vile = items.getVile();
            for (int i = 0; i < 64; i++) {
                inventory.addItem(vile);
            }
        }else if(this.crate.equals("VHALF")){
            this.broadcast = rank.getNameColor(player) + ChatColor.stripColor(player.getDisplayName()) + " &7has won &e&l32x Vile!";
            ItemStack vile = items.getVile();
            for (int i = 0; i < 32; i++) {
                inventory.addItem(vile);
            }
        }


    }
}
