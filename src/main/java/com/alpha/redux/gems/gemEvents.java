package com.alpha.redux.gems;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.items.enchants;
import com.alpha.redux.well.EnchantingMechanics;
import com.alpha.redux.well.enchanters.FreshPants;
import com.alpha.redux.well.enchanters.MysticSword;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static com.alpha.redux.gems.gemMain.*;
import static com.alpha.redux.redux.billionaireLore;
import static com.alpha.redux.well.EnchantingMechanics.getUpgradeableEnchants;
import static com.alpha.redux.well.enchanters.MysticSword.*;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnSword;

public class gemEvents {

    private static HashMap<String, ItemStack> storedMystic = new HashMap<>();

    private static List<String> renewSwordEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnSword(lore);

        // Bukkit.broadcastMessage(enchants.toString());

        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(enchantTier(convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        //hoverText(ChatColor.translateAlternateColorCodes('&', "&d&lRARE! " + rank.getNameColor(player) + player.getDisplayName() + "&7 created &cTier I Sword!"), compileListToString(lore));
        //player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);

        lore.addAll(enchant);

        lore.add(ChatColor.BLUE + "+6.5 Attack Damage");

        return lore;
    }

    private static List<String> renewPantEnchant(List<String> lore, List<String> enchant){

        List<String> enchants = CheckEnchantOnPant(lore);



        lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Lives: &a5&7/5"));

        lore.add(" ");

        for (String bench : enchants){
            int ether = bench.length() - bench.replaceAll("I", "").length();

            lore.addAll(Arrays.asList(FreshPants.enchantTier(FreshPants.convertEnchant(bench.replaceAll("I", "")), ether).split("\n")));
            //lore.add(" ");
        }

        lore.addAll(enchant);

        lore.add(ChatColor.RED + "As strong as iron");

        return lore;
    }

    public static void gemClickEvent(InventoryClickEvent event){
        try{
            gemRunTime(event);
            acceptMenu(event);
        } catch (Exception ignored) {

        }


    }

    public static void gemRunTime(InventoryClickEvent event){
        if(event == null) return;
        if(event.getClickedInventory().getTitle() == null) return;
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Totally Legit Gem")){
            event.setCancelled(true);

            ItemStack base_glass = advancedInventory.cGlass();

            if(event.getCurrentItem() != null){
                if(event.getCurrentItem().getType() != base_glass.getType()){
                    storedMystic.put(String.valueOf(event.getWhoClicked().getUniqueId()), event.getCurrentItem());
                    if(storedMystic.get(String.valueOf(event.getWhoClicked().getUniqueId())).getItemMeta().getLore().get(0).contains("♦")){
                        event.getWhoClicked().sendMessage(ChatColor.RED + "You can't gem this item twice!");
                        return;
                    }
                    event.getWhoClicked().openInventory(confirmationGui((Player) event.getWhoClicked()));
                }

            }
        }
    }

    public static void acceptMenu(InventoryClickEvent event){
        if(event == null) return;
        if(event.getClickedInventory().getTitle() == null) return;
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Confirm or Cancel")) {

            if(event.getCurrentItem().equals(confirmButton) && event.getWhoClicked().getInventory().containsAtLeast(enchants.gem,1)){
                ItemStack mystic = storedMystic.get(String.valueOf(event.getWhoClicked().getUniqueId()));
                event.getWhoClicked().getInventory().removeItem(mystic);

                ItemMeta mysticMeta = mystic.getItemMeta();

                List<String> lore = null;

                List<String> upgrades = null;
                if(mystic.getType().equals(Material.GOLD_SWORD)) upgrades = getUpgradeableEnchants(mysticMeta.getLore(), "SWORD");
                if(mystic.getType().equals(Material.LEATHER_LEGGINGS)) upgrades = getUpgradeableEnchants(mysticMeta.getLore(), "PANT");
                if(mystic.getType().equals(Material.BOW)) upgrades = getUpgradeableEnchants(mysticMeta.getLore(), "BOW");
                assert upgrades != null;
                Collections.shuffle(upgrades);

                String ENCHANT = upgrades.get(0);
                int tier = ENCHANT.length() - ENCHANT.replaceAll("I", "").length();

                if(mystic.getType().equals(Material.GOLD_SWORD)){
                    List<String> templore = mysticMeta.getLore();
                    templore.removeAll(translateList(Arrays.asList(MysticSword.enchantTier(MysticSword.convertEnchant(ENCHANT.replaceAll("I", "")), tier).split("\n"))));
                    mysticMeta.setLore(templore);
                    lore = renewSwordEnchant(mysticMeta.getLore(), Arrays.asList(MysticSword.enchantTier(MysticSword.convertEnchant(ENCHANT.replaceAll("I", "")), tier+1).split("\n")));
                }else if(mystic.getType().equals(Material.LEATHER_LEGGINGS)){

                    List<String> templore = mysticMeta.getLore();

                    templore.removeAll(translateList(Arrays.asList(FreshPants.enchantTier(FreshPants.convertEnchant(ENCHANT.replaceAll("I", "")), tier).split("\n"))));

                    mysticMeta.setLore(templore);
                    lore = renewPantEnchant(mysticMeta.getLore(), Arrays.asList(FreshPants.enchantTier(FreshPants.convertEnchant(ENCHANT.replaceAll("I", "")), tier+1).split("\n")));
                }


                assert lore != null;
                lore.set(0, lore.get(0) + ChatColor.GREEN + " ♦");

                mysticMeta.setLore(lore);
                mystic.setItemMeta(mysticMeta);

                event.getWhoClicked().sendMessage(ChatColor.GRAY + "Mystic Item successfully upgraded!");
                event.getWhoClicked().closeInventory();
                Sounds.GEM_USE.play(event.getWhoClicked());
                event.getWhoClicked().getInventory().removeItem(enchants.gem);
                event.getWhoClicked().getInventory().addItem(mystic);


            }else if(event.getCurrentItem().equals(cancelButton)) {
                event.getWhoClicked().closeInventory();
            }

            event.setCancelled(true);
        }
    }
}
