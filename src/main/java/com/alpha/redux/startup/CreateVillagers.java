package com.alpha.redux.startup;

import com.alpha.redux.apis.locations;
import com.alpha.redux.playerdata.economy;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;

import static com.alpha.redux.apis.locations.getLeaderBoardLocation;


public class CreateVillagers {

    public static NPC perm_upgrades_npc;
    public static NPC non_perm_upgrades_npc;

    public static NPC leaderboard_npc;
    public static NPC quest_npc;

    public static NPC prestige_npc;

    private static Hologram perm_upgrades_hologram;
    private static Hologram non_perm_upgrades_hologram;

    private static Hologram leaderboard_hologram;
    private static Hologram quest_hologram;

    private static Hologram prestige_hologram;

    private static Hologram ender_chest;
    private static Hologram ender_chest_lore;

    private static Hologram mystic_well;
    private static Hologram mystic_well_lore;

    public static NPC lobby_perm_upgrades_npc;
    public static NPC lobby_non_perm_upgrades_npc;

    public static NPC lobby_leaderboard_npc;
    public static NPC lobby_quest_npc;

    public static NPC lobby_prestige_npc;

    private static Hologram lobby_perm_upgrades_hologram;
    private static Hologram lobby_non_perm_upgrades_hologram;

    private static Hologram lobby_leaderboard_hologram;
    private static Hologram lobby_quest_hologram;

    private static Hologram lobby_prestige_hologram;

    private static Hologram lobby_ender_chest;
    private static Hologram lobby_ender_chest_lore;

    private static Hologram lobby_mystic_well;
    private static Hologram lobby_mystic_well_lore;

    public static void loadNPC(){

        perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Permanent");
        perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        perm_upgrades_npc.setProtected(true);

        lobby_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Permanent");
        lobby_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_perm_upgrades_npc.setProtected(true);

        non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
        non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        non_perm_upgrades_npc.setProtected(true);

        lobby_non_perm_upgrades_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Non-permanent");
        lobby_non_perm_upgrades_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_non_perm_upgrades_npc.setProtected(true);

        leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
        leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
        leaderboard_npc.setProtected(true);

        lobby_leaderboard_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "View your stats");
        lobby_leaderboard_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_leaderboard_npc.setProtected(true);

        quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
        quest_npc.setBukkitEntityType(EntityType.VILLAGER);
        quest_npc.setProtected(true);

        lobby_quest_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Quests & Contracts");
        lobby_quest_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_quest_npc.setProtected(true);

        prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
        prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
        prestige_npc.setProtected(true);

        lobby_prestige_npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GRAY + "Resets & Renown");
        lobby_prestige_npc.setBukkitEntityType(EntityType.VILLAGER);
        lobby_prestige_npc.setProtected(true);


        moveNPC();
        makeHolograms();
    }

    public static void unloadNPC(){
        deleteHolograms();
        deleteNPC();
    }

    private static void moveNPC(){
        if (!perm_upgrades_npc.isSpawned()) {
            perm_upgrades_npc.spawn(locations.perm_upgrades_loc);
        }

        if (!non_perm_upgrades_npc.isSpawned()) {
            non_perm_upgrades_npc.spawn(locations.non_perm_upgrades_loc);
        }

        if (!leaderboard_npc.isSpawned()) {
            leaderboard_npc.spawn(locations.leaderboard_npc_loc);
        }

        if (!quest_npc.isSpawned()) {
            quest_npc.spawn(locations.quest_npc_loc);
        }

        if (!prestige_npc.isSpawned()) {
            prestige_npc.spawn(locations.prestige_npc_loc);
        }

        if (!lobby_perm_upgrades_npc.isSpawned()) {
            lobby_perm_upgrades_npc.spawn(locations.lobby_perm_upgrades_loc);
        }

        if (!lobby_non_perm_upgrades_npc.isSpawned()) {
            lobby_non_perm_upgrades_npc.spawn(locations.lobby_non_perm_upgrades_loc);
        }

        if (!lobby_leaderboard_npc.isSpawned()) {
            lobby_leaderboard_npc.spawn(locations.lobby_leaderboard_npc_loc);
        }

        if (!lobby_quest_npc.isSpawned()) {
            lobby_quest_npc.spawn(locations.lobby_quest_npc_loc);
        }

        if (!lobby_prestige_npc.isSpawned()) {
            lobby_prestige_npc.spawn(locations.lobby_prestige_npc_loc);
        }

        perm_upgrades_npc.teleport(locations.perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        non_perm_upgrades_npc.teleport(locations.non_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        leaderboard_npc.teleport(locations.leaderboard_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        quest_npc.teleport(locations.quest_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        prestige_npc.teleport(locations.prestige_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);

        lobby_perm_upgrades_npc.teleport(locations.lobby_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_non_perm_upgrades_npc.teleport(locations.lobby_non_perm_upgrades_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_leaderboard_npc.teleport(locations.lobby_leaderboard_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_quest_npc.teleport(locations.lobby_quest_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        lobby_prestige_npc.teleport(locations.lobby_prestige_npc_loc, PlayerTeleportEvent.TeleportCause.PLUGIN);


        Location temp_quest = locations.quest_npc_loc;
        Location temp_leaderboard = locations.leaderboard_npc_loc;
        Location temp_prestige = locations.prestige_npc_loc;
        Location temp_perm_upgrades = locations.perm_upgrades_loc;
        Location temp_non_perm_upgrades = locations.non_perm_upgrades_loc;

        quest_npc.faceLocation(temp_quest.add(-1,0,0));
        leaderboard_npc.faceLocation(temp_leaderboard.add(-1,0,0));
        prestige_npc.faceLocation(temp_prestige.add(0,0,1));
        perm_upgrades_npc.faceLocation(temp_perm_upgrades.add(0,0,-1));
        non_perm_upgrades_npc.faceLocation(temp_non_perm_upgrades.add(0,0,-1));

        Location lobby_temp_quest = locations.lobby_quest_npc_loc;
        Location lobby_temp_leaderboard = locations.lobby_leaderboard_npc_loc;
        Location lobby_temp_prestige = locations.lobby_prestige_npc_loc;
        Location lobby_temp_perm_upgrades = locations.lobby_perm_upgrades_loc;
        Location lobby_temp_non_perm_upgrades = locations.lobby_non_perm_upgrades_loc;

        lobby_quest_npc.faceLocation(lobby_temp_quest.add(-1,0,0));
        lobby_leaderboard_npc.faceLocation(lobby_temp_leaderboard.add(-1,0,0));
        lobby_prestige_npc.faceLocation(lobby_temp_prestige.add(0,0,1));
        lobby_perm_upgrades_npc.faceLocation(lobby_temp_perm_upgrades.add(0,0,-1));
        lobby_non_perm_upgrades_npc.faceLocation(lobby_temp_non_perm_upgrades.add(0,0,-1));
    }

    private static void makeHolograms(){
        perm_upgrades_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.perm_upgrades_loc.add(0,2.75,1));
        perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&aUPGRADES"));
        non_perm_upgrades_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.non_perm_upgrades_loc.add(0,2.75,1));
        non_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&6ITEMS"));
        leaderboard_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.leaderboard_npc_loc.add(1,2.75,0));
        leaderboard_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&3STATS"));
        quest_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.quest_npc_loc.add(1,2.75,0));
        quest_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&bQUEST MASTER"));
        prestige_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.prestige_npc_loc.add(0,2.75,-1));
        prestige_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&ePRESTIGE"));

        ender_chest = HologramsAPI.createHologram(economy.getPlugin(), locations.getEnderChestLocation(Bukkit.getWorld("world")).add(0,-.5,0));
        ender_chest.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&5ENDER CHEST"));

        ender_chest_lore = HologramsAPI.createHologram(economy.getPlugin(), locations.getEnderChestLocation(Bukkit.getWorld("world")).add(0,-1,0));
        ender_chest_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

        mystic_well = HologramsAPI.createHologram(economy.getPlugin(), locations.getMysticWellLocation(Bukkit.getWorld("world")).add(0,-.5,0));
        mystic_well.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&dMYSTIC WELL"));

        mystic_well_lore = HologramsAPI.createHologram(economy.getPlugin(), locations.getMysticWellLocation(Bukkit.getWorld("world")).add(0,-1,0));
        mystic_well_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7item enchants"));

        lobby_perm_upgrades_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.lobby_perm_upgrades_loc.add(0,2.75,1));
        lobby_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&aUPGRADES"));
        lobby_non_perm_upgrades_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.lobby_non_perm_upgrades_loc.add(0,2.75,1));
        lobby_non_perm_upgrades_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&6ITEMS"));
        lobby_leaderboard_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.lobby_leaderboard_npc_loc.add(1,2.75,0));
        lobby_leaderboard_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&3STATS"));
        lobby_quest_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.lobby_quest_npc_loc.add(1,2.75,0));
        lobby_quest_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&bQUEST MASTER"));
        lobby_prestige_hologram = HologramsAPI.createHologram(economy.getPlugin(), locations.lobby_prestige_npc_loc.add(0,2.75,-1));
        lobby_prestige_hologram.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&ePRESTIGE"));

        lobby_ender_chest = HologramsAPI.createHologram(economy.getPlugin(), locations.getEnderChestLocation(Bukkit.getWorld("lobby")).add(0,-.5,0));
        lobby_ender_chest.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&5ENDER CHEST"));

        lobby_ender_chest_lore = HologramsAPI.createHologram(economy.getPlugin(), locations.getEnderChestLocation(Bukkit.getWorld("lobby")).add(0,-1,0));
        lobby_ender_chest_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7Store items forever"));

        lobby_mystic_well = HologramsAPI.createHologram(economy.getPlugin(), locations.getMysticWellLocation(Bukkit.getWorld("lobby")).add(0,-.5,0));
        lobby_mystic_well.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&l&dMYSTIC WELL"));

        lobby_mystic_well_lore = HologramsAPI.createHologram(economy.getPlugin(), locations.getMysticWellLocation(Bukkit.getWorld("lobby")).add(0,-1,0));
        lobby_mystic_well_lore.appendTextLine( ChatColor.translateAlternateColorCodes('&', "&7item enchants"));


    }

    private static void deleteHolograms(){
        perm_upgrades_hologram.delete();
        non_perm_upgrades_hologram.delete();
        leaderboard_hologram.delete();
        quest_hologram.delete();
        prestige_hologram.delete();
        ender_chest.delete();
        ender_chest_lore.delete();
        mystic_well.delete();
        mystic_well_lore.delete();

        lobby_perm_upgrades_hologram.delete();
        lobby_non_perm_upgrades_hologram.delete();
        lobby_leaderboard_hologram.delete();
        lobby_quest_hologram.delete();
        lobby_prestige_hologram.delete();
        lobby_ender_chest.delete();
        lobby_ender_chest_lore.delete();
        lobby_mystic_well.delete();
        lobby_mystic_well_lore.delete();
    }

    private static void deleteNPC(){
        perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(perm_upgrades_npc);

        non_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(non_perm_upgrades_npc);

        leaderboard_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(leaderboard_npc);

        quest_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(quest_npc);

        prestige_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(prestige_npc);

        lobby_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_perm_upgrades_npc);

        lobby_non_perm_upgrades_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_non_perm_upgrades_npc);

        lobby_leaderboard_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_leaderboard_npc);

        lobby_quest_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_quest_npc);

        lobby_prestige_npc.despawn();
        CitizensAPI.getNPCRegistry().deregister(lobby_prestige_npc);
    }

}
