package com.alpha.redux.events;

import com.alpha.redux.MenuClicks.Cactus.CactusRunTime;
import com.alpha.redux.UpgradesNpc.gui.PermanentUpgrades;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.apis.locations;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.items.enchants;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.MenuClicks.InventoryEvent;
import com.alpha.redux.questMaster.questInventoryManager;
import com.alpha.redux.redux;
import com.alpha.redux.renownShop.RenownItems;
import com.alpha.redux.renownShop.RenownStorage;
//import com.comphenix.protocol.wrappers.EnumWrappers;
import com.alpha.redux.startup.CreateVillagers;
import com.nametagedit.plugin.NametagEdit;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.*;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import com.alpha.redux.items.itemManager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static com.alpha.redux.DeathHandler.ProccessHit.*;
import static com.alpha.redux.DeathHandler.killHandler.*;
import static com.alpha.redux.ItemEvents.pants.*;
import static com.alpha.redux.apis.chatManager.levelcolor.getLevelColor;
import static com.alpha.redux.apis.chatManager.prestigebrackets.prestigebracket;
import static com.alpha.redux.apis.chatManager.rank.*;
import static com.alpha.redux.apis.locations.*;
import static com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap.deleteBlob;
import static com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap.getPlayerFromBlob;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.ArmorJoin.GiveChain;
//import static com.alpha.redux.events.boards.PlayerJoinScore;
//import static com.alpha.redux.events.boards.PlayerLeaveScore;
import static com.alpha.redux.events.nonPermItems.ClearAndCheck;
import static com.alpha.redux.gems.gemEvents.gemClickEvent;
import static com.alpha.redux.gems.gemMain.makeGemGUI;
import static com.alpha.redux.playerdata.bounties.BountiesMap;
import static com.alpha.redux.playerdata.economy.*;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.MenuClicks.InventoryEvent.*;
import static com.alpha.redux.playerdata.uber.claimUberReward;
import static com.alpha.redux.playerdata.xpManager.GetCurrentLevel;
import static com.alpha.redux.questMaster.questMenu.makeMainMenu;
import static com.alpha.redux.renownShop.renownEvent.mainEvent;
import static com.alpha.redux.well.gui.*;
import static com.alpha.redux.well.loreChecker.CheckEnchantOnPant;

public class events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setStreak(String.valueOf(player.getUniqueId()), 0);
        xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
        Strength.put(String.valueOf(player.getUniqueId()), 0.0);
        mega_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
        true_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
        xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
        GiveChain(player);
        player.sendMessage(ChatColor.GOLD + "Welcome to " + ChatColor.LIGHT_PURPLE + "BetterPit!");
        player.sendMessage(ChatColor.GRAY + "Please do /kit to receive the normal kit!");
        player.sendMessage(ChatColor.GRAY + "Have Fun!");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        hasStreak(String.valueOf(player.getUniqueId()));
        setStreak(String.valueOf(player.getUniqueId()), 0);
        if (getStreak(String.valueOf(player.getUniqueId())) >= 1){NonPermanentItems(player);}
        boards.CreateScore(player);
        if(!isNPC(player)){
            //NametagEdit.getApi().setNametag(player, "§b§lMOON " + rank.getNameColor(player), "");
            NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
            //NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
        }
        xpManager.hasXp(String.valueOf(event.getPlayer().getUniqueId()));
        if(!prestiges.hasPrestige(String.valueOf(event.getPlayer().getUniqueId()))) prestiges.setPrestige(String.valueOf(event.getPlayer().getUniqueId()), 0);


        /*
        int amount = 0;
        if (this.data.getConfig().contains("players."+ player.getUniqueId() + ".total"))
            amount = this.data.getConfig().getInt("players."+ player.getUniqueId() + ".total");

        this.data.getConfig().set("players." + player.getUniqueId() + ".total", (amount+1));
        this.data.saveConfig();*/
    }

    @EventHandler
    public void CloseInv(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        try {
            if(event.getInventory().getTitle().equals(ChatColor.GRAY + "Mystic Well")){
                ItemStack items = event.getInventory().getItem(20);
                player.getInventory().addItem(items);
            }

        }catch (Exception e){

        }

    }

    // Perun Itteration

    static int iteration = 0;

        @EventHandler
        public static void onPlayerWalk(PlayerMoveEvent event) {
            Player player = event.getPlayer();
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            Material block = player.getWorld().getBlockAt(x, y-1, z).getType();
            if (block == Material.SLIME_BLOCK) {
                if(player.getLocation().getY() >= 85){
                    player.setVelocity(player.getLocation().getDirection().multiply(3).setY(1));
                }
            }
        }

    public static HashMap<String, Double> Strength = new HashMap<>();
    public static HashMap<String, Boolean> Has_Strength = new HashMap<>();
    public static Map<String, Long> cooldowns = new HashMap<String, Long>();

    /*
    @EventHandler
    public static void SkyblockUtilsTerminator(PlayerInteractEvent event){
        Terminator(event);
    }
 */

    public static void checkRange(Entity npce) {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            Bukkit.getScheduler().runTaskTimer(economy.getPlugin(), () -> {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (npc.getEntity().getLocation().distance(players.getLocation()) <= 4) {
                        // Here the players should get the Damage/Knockback/Etc

                        PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity)npc).getHandle(), 0);
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket(animationPacket);



                        //double y = 0.3333; // this way, like normal knockback, it hits a player a little bit up

                        //float x = players.getLocation().getX();
                        //float z = players.getLocation().getZ();

                        //double multiplier = Math.sqrt((0.5*0.5) / (x*x + y*y + z*z)); // get a constant that, when multiplied by the vector, results in the speed we want
                        //players.setVelocity(players.getLocation().multiply(multiplier));
                        players.setVelocity(npc.getEntity().getLocation().getDirection().multiply(2));
                        players.damage(0);
                    }
                }
            }, 0L, 20L);
        }
        return;
    }
/*
    @EventHandler
    public static void npcTakeDamage(NPCDamageByEntityEvent event){
        event.getNPC().getNavigator().setTarget(event.getDamager(), false);

        PacketPlayOutAnimation animationPacket = new PacketPlayOutAnimation(((CraftEntity)event.getNPC().getEntity()).getHandle(), 0);
        ((CraftPlayer)event.getDamager()).getHandle().playerConnection.sendPacket(animationPacket);

        checkRange(event.getNPC().getEntity());

        return;

    }

*/

    /*
    @EventHandler
    public void boardHandlerQuit(PlayerQuitEvent event){
        PlayerLeaveScore(event);
    }

    @EventHandler
    public void boardHandlerJoin(PlayerJoinEvent event){
        PlayerJoinScore(event);
    }

     */

    @EventHandler
    public static void CombatLog(PlayerQuitEvent event){

        Player player = event.getPlayer();

        deleteBlob(player);

        String uuid = String.valueOf(player.getUniqueId());

        if(cooldowns.containsKey(uuid)){
            if (cooldowns.get(uuid) > System.currentTimeMillis()){
                // They still have time left on mute
                long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                player.removePotionEffect(PotionEffectType.WEAKNESS);
                ClearAndCheck(player);


                setStreak(String.valueOf(player.getUniqueId()), 0);
                xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
                Strength.put(String.valueOf(player.getUniqueId()), 0.0);
                mega_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
                true_damage_amount.put(String.valueOf(player.getUniqueId()), 0.0);
                xp_amount_mega.put(String.valueOf(player.getUniqueId()), 0);
                Location loc = getSpawnLocation(player.getWorld());
                player.teleport(loc);
                boards.CreateScore(player);
                NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
            }
        }

    }

    @EventHandler
    public void spawnProtHandler(NPCDamageEntityEvent event){
        if(event.getNPC().getEntity() != null)
            if(event.getNPC().getEntity().getLocation().getY() >= getSpawnProtection())
                event.setCancelled(true);
    }

    @EventHandler
    public void spawnProtsHandler(NPCDamageByEntityEvent event){
        if(event.getNPC().getEntity() != null)
            if(event.getNPC().getEntity().getLocation().getY() >= getSpawnProtection())
                event.setCancelled(true);
    }

    @EventHandler
    public void BlobDamager(NPCCollisionEvent event){
        Entity slime = event.getCollidedWith();

        if(slime.getType().equals(EntityType.SLIME)){

            Player player = getPlayerFromBlob((Slime) slime);
            if(player!=null){
                if(player.getLocation().getY() >= getSpawnProtection()) deleteBlob(player);
                if(player.getLocation().distance(slime.getLocation()) >= 18) deleteBlob(player);

                if(player.getInventory().getLeggings() != null &&
                        player.getInventory().getLeggings().getItemMeta() != null &&
                        player.getInventory().getLeggings().getItemMeta().getLore() != null){
                    if(!CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobIII") &&
                            !CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobII") &&
                            !CheckEnchantOnPant(player.getInventory().getLeggings().getItemMeta().getLore()).contains("blobI")){
                        deleteBlob(player);
                    }
                }

                ((Player) event.getNPC().getEntity()).damage(4, player);
            }

        }
    }

    @EventHandler
    public static void HandleMegaStreakDamage(ReduxDamageEvent event){
        // Mega Streak Calculations
        if(!isNPC(event.getDefenders().getPlayerObject())){
            hasStreak(event.getDefenders().getPlayerUUID());
            hasMegaStreak(event.getDefenders().getPlayerUUID());
            String streak = getMegaStreak(event.getDefenders().getPlayerUUID());

            if(streak.equals("beastmode") && getStreak(event.getDefenders().getPlayerUUID()) >= 50){
                if((getStreak(event.getDefenders().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID())-50)/5);

                event.addReduxDamage(counter*.1);
            }else if(streak.equals("overdrive") && getStreak(event.getDefenders().getPlayerUUID()) >= 50){
                if((getStreak(event.getDefenders().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID())-50)/5);

                event.getDefenders().addPotionEffect(PotionEffectType.SPEED, 32000, 1);

                event.addReduxTrueDamage(counter*.1);
            }else if(streak.equals("highlander") && getStreak(event.getDefenders().getPlayerUUID()) >= 50){
                if((getStreak(event.getDefenders().getPlayerUUID())-50)<=0) return;
                int counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID())-50)/15);

                event.getDefenders().addPotionEffect(PotionEffectType.SPEED, 32000, 1);

                event.addReduxDamage(counter*.1);
            }else if(streak.equals("uber") && getStreak(event.getDefenders().getPlayerUUID()) >= 100){
                int counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID()))/100);

                event.addReduxDamage(counter*.20);
            }else if(streak.equals("moon") && getStreak(event.getDefenders().getPlayerUUID()) >= 100){
                if((getStreak(event.getDefenders().getPlayerUUID())-100)<=0) return;
                int counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID())-100)/20);

                event.addReduxDamage(event.getReduxDamage()*(counter*.10));

                if((getStreak(event.getDefenders().getPlayerUUID())-200)<=0) return;
                counter = (int) Math.round((double)(getStreak(event.getDefenders().getPlayerUUID())-200)/20);

                event.addReduxTrueDamage(counter*.1);
            }
        }

        // Mega Streak Calculations
        if(!isNPC(event.getAttacker().getPlayerObject())){
            hasStreak(event.getAttacker().getPlayerUUID());
            hasMegaStreak(event.getAttacker().getPlayerUUID());
            String streak = getMegaStreak(event.getAttacker().getPlayerUUID());

            if(streak.equals("beastmode") && getStreak(event.getAttacker().getPlayerUUID()) >= 50){
                event.addReduxDamage(event.getReduxDamage()*.25);
            }else if(streak.equals("highlander") && getStreak(event.getAttacker().getPlayerUUID()) >= 50){
                if(!isNPC(event.getDefenders().getPlayerObject()) &&
                BountiesMap.containsKey(event.getDefenders().getPlayerUUID()) &&
                BountiesMap.get(event.getDefenders().getPlayerUUID()) >= 0){
                    event.addReduxDamage(event.getReduxDamage()*.33);
                }
            }else if(streak.equals("uber") && getStreak(event.getAttacker().getPlayerUUID()) >= 100){
                if(isNPC(event.getDefenders().getPlayerObject())){
                    event.subtractReduxDamage(event.getReduxDamage()*.50);
                }
            }
        }
    }

    @EventHandler 
    public static void MainDamageEvent(EntityDamageByEntityEvent event){

        if (event.getEntity().getType().equals(EntityType.VILLAGER)){
            event.setCancelled(true);
            return;
        }

        if(event.getDamager().getType().equals(EntityType.SLIME)){
            event.setCancelled(true);
            return;
        }

        if(event.getEntity().getType().equals(EntityType.SLIME)){
            event.setCancelled(true);

            Player player = getPlayerFromBlob((Slime) event.getEntity());

            if(player != null){
                if(player.getLocation().distance(event.getEntity().getLocation()) >= 20) deleteBlob(player);

                PitBlobMap.removeBlobHealth(player);
    
                if(PitBlobMap.getBlobHealth(player) <= 1) deleteBlob(player);
    
            }
            
            return;
        }

        /*
        if(event.getDamager().getType().equals(EntityType.SLIME)){

            Bukkit.broadcastMessage("SLIME!");
            Player player = getPlayerFromBlob((Slime) event.getDamager());

            if(event.getEntity().getType().equals(EntityType.PLAYER)){
                ((Player) event.getEntity()).damage(5, player);

            }

            event.setCancelled(true);
            return;
        }


         */
        Player defender = null;

        if(CitizensAPI.getNPCRegistry().isNPC(event.getDamager())){
            if(event.getDamager().getLocation().getY() >= getSpawnProtection()){
                event.setCancelled(true);
                return;

            }

        }
        if(!(event.getEntity() instanceof Player)) {return;}
        if((event.getDamager() instanceof Arrow)) {
            event.setCancelled(true);
            Arrow arrow = (Arrow) event.getDamager();
            Player player = (Player) arrow.getShooter();
            ((Player) event.getEntity()).damage(event.getDamage(), player);

            if(player.getItemInHand() != null && player.getItemInHand().getItemMeta() != null &&
                    !player.getItemInHand().getItemMeta().getDisplayName().contains("Super Bow") &&
            player.getItemInHand().getType().equals(Material.BOW)){
                player.getInventory().removeItem(player.getItemInHand());
                player.setItemInHand(enchants.fresh_bow);
            }

            ReduxBowEvent me = new ReduxBowEvent(playerExists(player), playerExists((Player) event.getEntity()), 3, event);
            Bukkit.getPluginManager().callEvent(me);


            return;

        }else if((event.getDamager() instanceof Player)){
            defender = (Player) event.getEntity();
        }

        Player attacker = (Player) event.getDamager();

        if(attacker.getGameMode().equals(GameMode.SURVIVAL)){
            attacker.setAllowFlight(false);
        }


        if(event.getDamager().getLocation().getY() <= getSpawnProtection()){
            assert defender != null;
            defender.setAllowFlight(false);
            cooldowns.put(String.valueOf(event.getDamager().getUniqueId()), System.currentTimeMillis() + (5 * 1000));
            cooldowns.put(String.valueOf(event.getEntity().getUniqueId()), System.currentTimeMillis() + (5 * 1000));

            ReduxDamageEvent mainEvent = new ReduxDamageEvent(playerExists(attacker), playerExists(defender), event.getDamage(), event);
            if(mainEvent!=null)Bukkit.getPluginManager().callEvent(mainEvent);
            if (!mainEvent.isCancelled()) {

                mainEvent.run();

                event.setDamage(mainEvent.getReduxDamage());

                if(isNPC(defender)){
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 3){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }else{
                    if(((LivingEntity) event.getEntity()).getHealth() - event.getFinalDamage() <= 1){
                        event.setCancelled(true);
                        ((LivingEntity) event.getEntity()).setHealth(((LivingEntity) event.getEntity()).getMaxHealth());
                        KillMan((Player) event.getDamager(), (Player) event.getEntity());
                        return;
                    }
                }


                new TrueDamageHandler(playerExists(attacker), playerExists(defender), mainEvent.getReduxTrueDamage(), event.getFinalDamage()).run();
            }else{
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void Pero(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)) {return;}
        if(!(event.getDamager() instanceof Player)) {return;}

        Peroxides(event);

    }

    @EventHandler
    public static void Death(PlayerDeathEvent event){

        if(event.getEntity() == null) {return;}
        if(isNPC(event.getEntity())){
            NPC npc = getNPC(event.getEntity());
            if(npc != null){
                Player player = (Player) npc.getEntity();
                player.setHealth(player.getMaxHealth());
                player.setMaxHealth(20);
            }

            return;
        }
        Player player = event.getEntity();
        player.setHealth(player.getMaxHealth());
        player.setMaxHealth(20);
        try{
            escapeProc.put(String.valueOf(player.getUniqueId()), false);
            KillMan(player.getKiller(), player);
        } catch (Exception e) {

        }

        NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"YOU DIED\",\"color\":\"red\"}"),100,20,20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
    }

    @EventHandler
    public static void PrestigeLimit(PlayerLevelChangeEvent event){
        Player player = event.getPlayer();
        if (player.getLevel() > 120){
            player.setLevel(120);
        }else if(player.getLevel() == 0){
            return;
        }else{
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
            String pb = prestigebracket(player);
            int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
            PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                    IChatBaseComponent.ChatSerializer.a("{\"text\":\"LEVEL UP!\",\"bold\":true,\"color\":\"aqua\"}"),100,20,20);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
            PacketPlayOutTitle sub_title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\" " + pb + "[" + getLevelColor(randomDUDE[1]-1) + (randomDUDE[1] -1)  + pb + "]" +  ChatColor.GRAY + " ➟ "  + pb + "[" + getLevelColor(randomDUDE[1]) + (randomDUDE[1])  + pb + "]" +"\"}"),100,20,20);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(sub_title);
        }

    }
/*
    @EventHandler 
    public static void Executioner(EntityDamageByEntityEvent event){
        Player attacker = (Player) event.getDamager();
        Player defender = (Player) event.getEntity();

        if (attacker.getLocation().getY() >= 100){
            event.setCancelled(true);
            return;
        }

        if (attacker.hasPotionEffect(PotionEffectType.POISON)){
            return;
        }

        if (attacker.getInventory().getItemInHand().getItemMeta().equals(itemManager.executioner.getItemMeta())) {
            if(isNPC(defender)){
                if (defender.getHealth() - event.getDamage()+7 <= 0) {
                    event.setCancelled(true);
                    KillMan(attacker, defender);
                }
            }
        }
    }
 */

    /*
    @EventHandler 
    private static void Venom(EntityDamageByEntityEvent event) {
        Player attacker = (Player) event.getDamager();
        Player defender = (Player) event.getEntity();

        if (attacker.getLocation().getY() >= 100){
            event.setCancelled(true);
            return;
        }

        else{
            if (attacker.getInventory().getLeggings().getItemMeta().equals((itemManager.venom.getItemMeta()))) {
                if (isNPC(defender)){
                    return;
                }
                if (defender.getInventory().getBoots().getItemMeta().equals(itemManager.arma.getItemMeta())) {
                    defender.sendMessage(ChatColor.RED + "WOAH!" + ChatColor.RESET + ChatColor.AQUA + " Your armageddon boots just saved you!");
                    defender.setHealth(Math.min(defender.getHealth() + 2, defender.getMaxHealth()));
                    attacker.getWorld().playSound(attacker.getLocation(), Sound.BLAZE_HIT, 1, 2);
                    attacker.sendMessage(ChatColor.RED + "OOF!" + ChatColor.RESET + ChatColor.AQUA + " Your opponent's armageddon boots just countered you!");
                    event.setCancelled(true);
                } else {
                    if (defender.getHealth() - event.getDamage()+event.getDamage() <= 0) {
                        event.setCancelled(true);
                        KillMan(attacker, defender);
                    }else {

                        defender.removePotionEffect(PotionEffectType.POISON);
                        attacker.removePotionEffect(PotionEffectType.POISON);
                        defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0, true, true));
                        attacker.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0, true, true));
                        event.setDamage(2);
                        defender.setHealth(Math.min(defender.getHealth() - 1, defender.getMaxHealth()));
                    }
                }
            }
        }

    }

    @EventHandler 
    public static void Peroxide(EntityDamageEvent event){
        Player defender = (Player) event.getEntity();
        if (isNPC(defender)){
            return;
        }else if (defender.hasPotionEffect(PotionEffectType.POISON)){
            return;
        }else if (defender.getInventory().getLeggings().getItemMeta().equals((itemManager.peroxide.getItemMeta()))){
            defender.removePotionEffect(PotionEffectType.REGENERATION);
            defender.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1, true, true));
        }

    }


 */

    @EventHandler
    public static void ChatEvents(AsyncPlayerChatEvent event){
        ChatEvent(event);
    }

    public static Map<String, Long> CPcd = new HashMap<String, Long>();
    
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() != null && player.getItemInHand().getType().equals(Material.CACTUS)){
            player.openInventory(CactusRunTime.inventoryConstructor(player));
            return;
        }

        if(event.getItem() != null && event.getItem().getType().equals(Material.EMERALD)){
            player.openInventory(makeGemGUI(player));
            return;
        }

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR){

            if(event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.firstaidempty)){
                event.setCancelled(true);
                Sounds.NO.play(player);
                return;
            }

            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null &&
                    event.getPlayer().getItemInHand().getItemMeta().getLore() != null &&
            event.getPlayer().getItemInHand().getItemMeta().getLore().equals(enchants.firstaidfull.getItemMeta().getLore())){
                event.setCancelled(true);
                player.setItemInHand(enchants.firstaidempty);
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth()+5));
                Sounds.FIRST_AID.play(player);

                Bukkit.getScheduler().scheduleSyncDelayedTask(redux.INSTANCE, new Runnable() {
                    @Override
                    public void run() {
                        player.getInventory().removeItem(enchants.firstaidempty);
                        player.getInventory().addItem(enchants.firstaidfull);
                    }
                }, 5 * 20);

                return;
            }

            if (event.getPlayer().getItemInHand() != null &&
            event.getPlayer().getItemInHand().equals(enchants.fullPantPB)){
                for (int i = 0; i < 10; i++) {
                    event.getPlayer().getInventory().addItem(enchants.fresh_reds);
                }

                Sounds.ARMOR_SWAP.play(player);

                event.getPlayer().getInventory().removeItem(enchants.fullPantPB);
            }else if (event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.fullSwordPB)){
                for (int i = 0; i < 10; i++) {
                    event.getPlayer().getInventory().addItem(enchants.fresh_sword);
                }

                Sounds.ARMOR_SWAP.play(player);

                event.getPlayer().getInventory().removeItem(enchants.fullSwordPB);
            }

            if(event.getPlayer().getItemInHand() != null &&
                event.getPlayer().getItemInHand().getItemMeta() != null &&
                event.getPlayer().getItemInHand().equals(enchants.swordPB) &&
                    event.getPlayer().getInventory().contains(enchants.fresh_sword, 10)
            ){

                event.getPlayer().getInventory().removeItem(enchants.swordPB);

                for (int i = 0; i < 10; i++) {
                    event.getPlayer().getInventory().removeItem(enchants.fresh_sword);
                }

                event.getPlayer().getInventory().addItem(enchants.fullSwordPB);

                Sounds.FIRST_AID.play(player);

                return;

            }else if(event.getPlayer().getItemInHand() != null &&
                    event.getPlayer().getItemInHand().getItemMeta() != null &&
                    event.getPlayer().getItemInHand().equals(enchants.pantsPB)){

                if(event.getPlayer().getInventory().contains(enchants.fresh_reds, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_reds);
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().contains(enchants.fresh_blues, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_blues);
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().contains(enchants.fresh_yellows, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_yellows);
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().contains(enchants.fresh_greens, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_greens);
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else if(event.getPlayer().getInventory().contains(enchants.fresh_oranges, 10)){
                    for (int i = 0; i < 10; i++) {
                        event.getPlayer().getInventory().removeItem(enchants.fresh_oranges);
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }else{

                    int pant_amount = 0;

                    for (ItemStack item : event.getPlayer().getInventory()){
                        if (item!=null&&item.getItemMeta()!=null&& item.getItemMeta().getDisplayName() != null &&item.getItemMeta().getDisplayName().contains("Fresh")){
                            pant_amount+=1;
                        }
                    }

                    if(pant_amount < 10) return;

                    int removed = 0;

                    if(pant_amount>=10){
                        for(ItemStack item : event.getPlayer().getInventory()){
                            if(removed>=10){
                                break;
                            }

                            if(item!=null&&item.getItemMeta()!=null&& item.getItemMeta().getDisplayName() != null &&item.getItemMeta().getDisplayName().contains("Fresh")){
                                event.getPlayer().getInventory().removeItem(item);
                                removed+=1;
                            }
                        }
                    }

                    Sounds.FIRST_AID.play(player);
                    event.getPlayer().getInventory().addItem(enchants.fullPantPB);
                    event.getPlayer().getInventory().removeItem(enchants.pantsPB);
                    return;
                }

            }
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().equals(RenownStorage.getBlazeQuest())) {
                    if (cooldowns.containsKey(String.valueOf(player.getUniqueId()))){
                        // player is inside mute map
                        if (cooldowns.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
                            // They still have time left on mute
                            long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
                            player.sendMessage(ChatColor.RED + "You can't use this while in combat!");
                            player.setAllowFlight(false);
                            return;
                        }else{
                            player.setVelocity(player.getEyeLocation().getDirection());
                            player.setAllowFlight(true);
                            PacketPlayOutWorldParticles animationPacket = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) player.getLocation().getX(), (float) player.getLocation().getY(), (float) player.getLocation().getZ(), 255, 255, 255, (float) 1, 0);
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(animationPacket);
                            return;
                        }

                    }else{
                        player.setVelocity(player.getEyeLocation().getDirection());
                        player.setAllowFlight(true);
                        PacketPlayOutWorldParticles animationPacket = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) player.getLocation().getX(), (float) player.getLocation().getY(), (float) player.getLocation().getZ(), 255, 255, 255, (float) 1, 0);
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(animationPacket);
                    }
                    //player.getWorld().createExplosion(player.getLocation(), 2.0f);
                }else if(event.getItem().getType().equals(Material.EYE_OF_ENDER)){
                    try{
                        if (CPcd.containsKey(String.valueOf(event.getPlayer().getUniqueId()))){
                            // player is inside mute map
                            if (CPcd.get(String.valueOf(event.getPlayer().getUniqueId())) > System.currentTimeMillis()){
                                // They still have time left on mute
                                long timeleft = (CPcd.get(String.valueOf(event.getPlayer().getUniqueId())) - System.currentTimeMillis()) / 1000;
                                event.getPlayer().sendMessage(ChatColor.RED + "Please wait a little before doing that!");
                            }else{
                                List<Player> playerOnline = new ArrayList<>();
                                for (Entity players : event.getPlayer().getNearbyEntities(5, 5, 5)){
                                    if(players instanceof Player){
                                        if(!isNPC((Player) players)){
                                            if(players.getLocation().getY() <= getSpawnProtection()){
                                                if(event.getPlayer().getLocation().getY() <= getSpawnProtection()){
                                                    playerOnline.add((Player) players);
                                                }
                                            }
                                        }

                                    }

                                }

                                Random rand = new Random(); //instance of random class
                                int upperbound = playerOnline.size();
                                //generate random values from 0-24
                                int int_random = rand.nextInt(upperbound);

                                Player selected = playerOnline.get(int_random);

                                if((selected.getInventory().getBoots() != null && (selected.getInventory().getBoots().getItemMeta().equals(itemManager.arma.getItemMeta())))){
                                    event.getPlayer().sendMessage(ChatColor.RED + selected.getDisplayName() + " had armageddon boots and countered you!");
                                    return;
                                }

                                selected.teleport(event.getPlayer().getLocation());
                                selected.sendMessage(ChatColor.GRAY + "You've been " + ChatColor.GREEN + "venom'd!");
                                Sounds.VENOM.play(selected.getLocation(), 1);
                                event.getPlayer().sendMessage(ChatColor.GRAY + "You " + ChatColor.GREEN + "venom'd! " + ChatColor.YELLOW + selected.getDisplayName());
                                selected.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, true, true));
                                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, true, true));

                                CPcd.put(String.valueOf(event.getPlayer().getUniqueId()), System.currentTimeMillis() + (60 * 1000));
                            }

                        }else{
                            List<Player> playerOnline = new ArrayList<>();
                            for (Entity players : event.getPlayer().getNearbyEntities(5, 5, 5)){
                                if(players instanceof Player){
                                    if(!isNPC((Player) players)){
                                        if(players.getLocation().getY() <= getSpawnProtection()){
                                            if(event.getPlayer().getLocation().getY() <= getSpawnProtection()){
                                                playerOnline.add((Player) players);
                                            }
                                        }
                                    }

                                }

                            }

                            Random rand = new Random(); //instance of random class
                            int upperbound = playerOnline.size();
                            //generate random values from 0-24
                            int int_random = rand.nextInt(upperbound);

                            Player selected = playerOnline.get(int_random);
                            if((selected.getInventory().getBoots() != null && (selected.getInventory().getBoots().getItemMeta().equals(itemManager.arma.getItemMeta())))){
                                event.getPlayer().sendMessage(ChatColor.RED + selected.getDisplayName() + " had armageddon boots and countered you!");
                                return;
                            }

                            selected.teleport(event.getPlayer().getLocation());
                            selected.sendMessage(ChatColor.GRAY + "You've been " + ChatColor.GREEN + "venom'd!");
                            Sounds.VENOM.play(selected.getLocation(), 1);
                            event.getPlayer().sendMessage(ChatColor.GRAY + "You " + ChatColor.GREEN + "venom'd! " + ChatColor.YELLOW + selected.getDisplayName());
                            selected.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, true, true));
                            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, true, true));

                            CPcd.put(String.valueOf(event.getPlayer().getUniqueId()), System.currentTimeMillis() + (60 * 1000));

                        }
                    } catch (Exception ignored) {}


                }else if(event.getItem().getType().equals(Material.ENDER_CHEST)){
                    event.getPlayer().getInventory().removeItem(RenownStorage.getUberDrop());

                    event.getPlayer().getInventory().removeItem(new ItemStack(Material.ENDER_CHEST));

                    event.getPlayer().getInventory().removeItem(RenownItems.UberDrop());

                    claimUberReward(event.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void MysticWellEnchant(PlayerInteractEvent event){
        try{

            Material block = event.getClickedBlock().getType();

            if(block == Material.ENCHANTMENT_TABLE){
                event.setCancelled(true);
                Sounds.BOOSTER_REMIND.play(event.getPlayer());
                base(event.getPlayer());
            }else if(block == Material.CAKE_BLOCK){
                if(event.getPlayer().getInventory().getItemInHand().equals(RenownStorage.getStickQuest())){
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.CAKE));
                    changeCakeLocation();
                    Random rand = new Random(); //instance of random class
                    int upperbound = 10;
                    int int_random = rand.nextInt(upperbound);
                    switch (int_random){
                        case 1:
                        case 2:
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().getInventory().addItem(RenownStorage.getToken());
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ANVIL_LAND, 1, 1);
                            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lWOW! &7you found some &eRenown Tokens&7!"));
                            break;
                    }
                }else{
                    event.getPlayer().sendMessage(ChatColor.RED + "You need the right tool for this!");
                }

            }else if(!block.equals(Material.ENDER_CHEST)) {
                if(event.getPlayer().isOp()) return;
                if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) &&
                event.getPlayer().getItemInHand().getType().equals(Material.OBSIDIAN)) return;
                event.setCancelled(true);
            }
        }catch (Exception e){

        }

    }

    @EventHandler 
    public void onPlayerShootArrow(EntityShootBowEvent event){
        if(!(event.getEntity() instanceof Player)) {return;}


        Player shooter = (Player) event.getEntity();

        shooter.getInventory().addItem(new ItemStack(Material.ARROW, 1));

        if (shooter.getInventory().getItemInHand().getItemMeta().equals(itemManager.megalongbow.getItemMeta())){
            shooter.getInventory().removeItem(itemManager.megalongbow);
            shooter.getInventory().addItem(enchants.fresh_bow);
            /*
            event.getProjectile().remove();

            Location PlayerLocation = shooter.getEyeLocation();
            Location loc = PlayerLocation.add(PlayerLocation.getDirection().multiply(1.2));
            Arrow arrow = (Arrow) loc.getWorld().spawnEntity(loc, EntityType.ARROW);
            arrow.setCritical(true);
            arrow.setVelocity(shooter.getLocation().getDirection().multiply(2.90));
            arrow.setShooter(shooter);

            if(shooter.hasPotionEffect(PotionEffectType.JUMP)){
                shooter.removePotionEffect(PotionEffectType.JUMP);
            }

            shooter.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 5, true, true));

             */
        }
        if (shooter.getInventory().getItemInHand().getItemMeta().equals(itemManager.ftts.getItemMeta())){
            shooter.getInventory().removeItem(itemManager.ftts);
            shooter.getInventory().addItem(enchants.fresh_bow);
            /*
            if(shooter.hasPotionEffect(PotionEffectType.SPEED)){
                shooter.removePotionEffect(PotionEffectType.SPEED);
            }

            shooter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 4, true, true));

             */
        }

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void entityDamageEvent(EntityDamageEvent event) {

        if(event.getCause()== EntityDamageEvent.DamageCause.VOID){
            event.getEntity().teleport(locations.getBotSpawnLocation(event.getEntity().getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
            if(event.getEntity().getType().equals(EntityType.PLAYER) &&
            !isNPC((Player) event.getEntity())){
                Player player = (Player)event.getEntity();
                player.sendMessage(colorCode("&c&lOOPS! &7you fell out of the void!"));
                Sounds.ERROR.play(player);
            }
            event.setCancelled(true);
        }

        if(event.getCause()== EntityDamageEvent.DamageCause.FIRE){
            event.setCancelled(true);
        }

        if(event.getCause()== EntityDamageEvent.DamageCause.FIRE_TICK){
            event.setCancelled(true);
        }


        if(event.getCause()== EntityDamageEvent.DamageCause.LAVA){
            event.setCancelled(true);
        }

        if(event.getEntity().getType().equals(EntityType.SLIME)) event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
            event.setCancelled(true);
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            event.setCancelled(true);
        }

    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onFoodLevelChange (FoodLevelChangeEvent event) {
        if (event.getEntityType () != EntityType.PLAYER) return;
        event.setCancelled (true);
    }

    @EventHandler
    public void venomHandler(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getCause().equals(EntityDamageEvent.DamageCause.POISON)) event.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void clickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        gemClickEvent(event);

        try {
            if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Non-permanent items")) {
                event.setCancelled(true);
                NonPermItems(event);
            } else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "Prestige & Renown")) {
                PrestigeItems(event);
            } else if(event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Quest Master")){
                questInventoryManager.main(event);
            }else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Streak Hub")) {
                event.setCancelled(true);
            }else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Renown Shop")){
                mainEvent(event);
            }else if (event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Perks")){
                PerkItems(event);
                event.setCancelled(true);
            }else if(event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GOLD + "MegaStreak Hub")){


                int[] randomDUDE = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);

                switch (event.getCurrentItem().getType()){
                    case GOLD_SWORD:
                        if(Objects.equals(getMegaStreak(String.valueOf(player.getUniqueId())), "uber")){
                            player.sendMessage(ChatColor.RED + "You already have this selected!");
                            megaStreak(player);
                        }else{
                            if (getPrestige(String.valueOf(player.getUniqueId())) >= 20){
                                megaStreak(player);
                                if (!(randomDUDE[1] >= 100)) {event.setCancelled(true); player.sendMessage(ChatColor.RED + "You aren't high enough level to use this!");break;}
                                if (getEconomy(String.valueOf(player.getUniqueId())) >= 10000){
                                    removeEconomy(String.valueOf(player.getUniqueId()), 10000);
                                    hasMegaStreak(String.valueOf(player.getUniqueId()));
                                    setMega(String.valueOf(player.getUniqueId()), "uber");
                                    player.sendMessage(ChatColor.GREEN + "Uber Selected");
                                    boards.CreateScore(player);
                                    hasStreak(String.valueOf(player.getUniqueId()));
                                    setStreak(String.valueOf(player.getUniqueId()), 0);
                                    boards.CreateScore(player);
                                }else{
                                    player.sendMessage(ChatColor.RED + "You can't afford this!");
                                }
                            }else{
                                player.sendMessage(ChatColor.RED + "You aren't high enough prestige to use this!");
                            }
                        }

                        event.setCancelled(true);
                        break;
                    case GOLD_BOOTS:
                        if(getMegaStreak(String.valueOf(player.getUniqueId())).equals("highlander")){
                            megaStreak(player);
                            player.sendMessage(ChatColor.RED + "You already have this selected!");
                        }else{
                            if (getPrestige(String.valueOf(player.getUniqueId())) >= 5){
                                megaStreak(player);
                                if (randomDUDE[1] >= 50){
                                    hasMegaStreak(String.valueOf(player.getUniqueId()));
                                    setMega(String.valueOf(player.getUniqueId()), "highlander");
                                    player.sendMessage(ChatColor.GREEN + "Highlander Selected");
                                    boards.CreateScore(player);
                                    hasStreak(String.valueOf(player.getUniqueId()));
                                    setStreak(String.valueOf(player.getUniqueId()), 0);
                                    boards.CreateScore(player);
                                }else{
                                    megaStreak(player);
                                    player.sendMessage(ChatColor.RED + "You aren't high enough level to use this!");
                                }
                            }else{
                                megaStreak(player);
                                player.sendMessage(ChatColor.RED + "You aren't high enough prestige to use this!");
                            }
                        }


                        event.setCancelled(true);
                        break;
                    case DIAMOND_HELMET:
                        megaStreak(player);
                        hasMegaStreak(String.valueOf(player.getUniqueId()));
                        setMega(String.valueOf(player.getUniqueId()), "beastmode");
                        player.sendMessage(ChatColor.GREEN + "Beastmode Selected");
                        event.setCancelled(true);
                        boards.CreateScore(player);
                        hasStreak(String.valueOf(player.getUniqueId()));
                        setStreak(String.valueOf(player.getUniqueId()), 0);
                        boards.CreateScore(player);
                        break;
                    case ENDER_STONE:
                        if(getMegaStreak(String.valueOf(player.getUniqueId())).equals("moon")){
                            megaStreak(player);
                            player.sendMessage(ChatColor.RED + "You already have this selected!");
                        }else{
                            if (getPrestige(String.valueOf(player.getUniqueId())) >= 20){
                                megaStreak(player);
                                if(randomDUDE[1] >= 50){
                                    hasMegaStreak(String.valueOf(player.getUniqueId()));
                                    setMega(String.valueOf(player.getUniqueId()), "moon");
                                    player.sendMessage(ChatColor.GREEN + "To the Moon Selected");
                                    hasStreak(String.valueOf(player.getUniqueId()));
                                    setStreak(String.valueOf(player.getUniqueId()), 0);
                                    boards.CreateScore(player);
                                }else{
                                    player.sendMessage(ChatColor.RED + "You aren't high enough level to use this!");
                                }

                            }else{
                                player.sendMessage(ChatColor.RED + "You aren't high enough prestige to use this!");
                            }
                        }

                        event.setCancelled(true);
                        boards.CreateScore(player);
                        break;
                    case STAINED_GLASS_PANE:
                        event.setCancelled(true);
                        break;
                }
                event.setCancelled(true);
            }else if(player.getOpenInventory().getTitle().equals(ChatColor.GRAY + "Mystic Well")){
                InventoryEvent.main(event);
            }
        }catch (Exception e){

        }
        }


    @EventHandler
    public static void NpcShop(PlayerInteractEntityEvent event){

        if (event.getRightClicked().getType() != EntityType.VILLAGER &&
                event.getPlayer().getType() != EntityType.PLAYER){
            event.setCancelled(true);
            return;
        }



        Player player = (Player) event.getPlayer();
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(event.getRightClicked());

        if (Objects.equals(npc, CreateVillagers.non_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_non_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_non_perm_upgrades_npc)){
            NonPermanentItems(player);
        }else if (Objects.equals(npc, CreateVillagers.prestige_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_prestige_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_prestige_npc)){
            PrestigeMenu(player);
        }else if (Objects.equals(npc, CreateVillagers.perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_perm_upgrades_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_perm_upgrades_npc)){
            player.openInventory(PermanentUpgrades.getPermanentUpgrades(player));
        }else if(Objects.equals(npc, CreateVillagers.quest_npc) ||
                Objects.equals(npc, CreateVillagers.lobby_quest_npc) ||
                Objects.equals(npc, CreateVillagers.lobby2_quest_npc)){
            if(getPrestige(String.valueOf(player.getUniqueId())) >= 15){
                player.openInventory(makeMainMenu(player));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bQuest Master&8 >> &7Hey you need at least prestige &eXV&7 to talk to me!"));
            }

            //Perks(player);
        }else if(npc != null && npc.getName() != null && npc.getName().contains("Merchant")){
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Hey welcome to Better Pit!");
            player.sendMessage(ChatColor.GRAY + "Check out the store at: " + ChatColor.AQUA + "https://betterpit.tebex.io/");
            player.sendMessage(ChatColor.AQUA + "Join the discord at: " + ChatColor.DARK_AQUA + "https://discord.gg/jeGddMuH");
            //Perks(player);
        }
    }

    }