package com.alpha.redux;

import com.alpha.redux.UpgradesNpc.gui.PermanentUpgrades;
import com.alpha.redux.UpgradesNpc.perks.*;
import com.alpha.redux.UpgradesNpc.perks.items.GoldenHeadItem;
import com.alpha.redux.apis.Sounds;
import com.alpha.redux.apis.chatManager.rank;
import com.alpha.redux.apis.locations;
import com.alpha.redux.apis.skyblock.skyblockEvents;
import com.alpha.redux.apis.skyblock.skyblockItems;
import com.alpha.redux.boosters.Booster;
import com.alpha.redux.boosters.BoosterEvents;
import com.alpha.redux.commands.command;
import com.alpha.redux.commands.repairs.ClickHandler;
import com.alpha.redux.commands.view.ViewCore;
import com.alpha.redux.entityHandlers.MysticHandler.MysticEventHandler.MysticEventHandlers;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap;
import com.alpha.redux.entityHandlers.ReduxPlayerHandler;
import com.alpha.redux.eventManagers.ArmorEvents.ArmorListener;
import com.alpha.redux.eventManagers.ReduxEvents;
import com.alpha.redux.events.boards;
import com.alpha.redux.playerdata.Renown;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.events.events;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.items.itemManager;
import com.alpha.redux.items.enchants;
import com.alpha.redux.questMaster.bossBattles.bossEvents;
import com.alpha.redux.renownShop.CookieMonster.Monster;
import com.alpha.redux.renownShop.DataStorage.*;
import com.alpha.redux.renownShop.atomizer.InventoryEventManager;
import com.alpha.redux.renownShop.gui.RenownShopGUI;
import com.alpha.redux.renownShop.gui.RenownShopUpgradesGUI;
import com.alpha.redux.startup.CreateVillagers;
import com.alpha.redux.well.enchants.*;
import com.alpha.redux.well.enchants.global.*;
import com.nametagedit.plugin.NametagEdit;
import me.alpha.hunter.api.HunterAPI;
import me.alpha.hunter.api.hunterTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApiGetLevelColor;
import static com.alpha.redux.apis.leaderboardsplus.leaderboards.*;
import static com.alpha.redux.apis.locations.getCakeLocation;
import static com.alpha.redux.entityHandlers.MysticHandler.Pants.data.PitBlobMap.*;
//import static com.alpha.redux.events.boards.ClearAllScore;
import static com.alpha.redux.events.boards.*;
import static com.alpha.redux.funEvents.event.handleTwoEvent;
import static com.alpha.redux.funEvents.event.twoTimesEvent;
import static com.alpha.redux.questMaster.bossBattles.maldingBoss.*;

public class redux extends JavaPlugin {

    public static redux INSTANCE;
    public static Vampire vampire = new Vampire();
    public static GoldenHeads goldenHeads = new GoldenHeads();
    public static Dirty dirty = new Dirty();

    public static Gladiator gladiator = new Gladiator();
    public static StrengthChaining strengthChaining = new StrengthChaining();
    public static Streaker streaker = new Streaker();

    public static PitPocketLore pitPocketLore = new PitPocketLore();
    public static GrasshopperLore grasshopperLore = new GrasshopperLore();
    public static GoldBoostedLore goldBoostedLore = new GoldBoostedLore();
    public static BerserkerLore berserkerLore = new BerserkerLore();
    public static PunisherLore punisherLore = new PunisherLore();
    public static ComboDamageLore combodamageLore = new ComboDamageLore();
    public static FancyRaiderLore fancyraiderLore = new FancyRaiderLore();
    public static XpboostLore xpboostLore = new XpboostLore();
    public static XpbumpLore xpbumpLore = new XpbumpLore();
    public static SweatyLore sweatyLore = new SweatyLore();
    public static GoldboostLore goldboostLore = new GoldboostLore();
    public static GoldbumpLore goldbumpLore = new GoldbumpLore();
    public static MoctezumaLore moctezumaLore = new MoctezumaLore();
    public static PantsRadarLore pantsRadarLore = new PantsRadarLore();
    public static BooBooLore booBooLore = new BooBooLore();
    public static DavidGoliathLore davidGoliathLore = new DavidGoliathLore();
    public static DiamondAllergyLore diamondAllergyLore = new DiamondAllergyLore();
    public static CriticallyFunkyLore criticallyFunkyLore = new CriticallyFunkyLore();
    public static GoldenHeartLore goldenHeartLore = new GoldenHeartLore();
    public static ProtectionLore protectionLore = new ProtectionLore();
    public static SolitudeLore solitudeLore = new SolitudeLore();
    public static CricketLore cricketLore = new CricketLore();
    public static RetroGravityMicrocosmLore retroGravityMicrocosmLore = new RetroGravityMicrocosmLore();
    public static RegularityLore regularityLore = new RegularityLore();
    public static NotGladiatorLore notGladiatorLore = new NotGladiatorLore();
    public static MirrorLore mirrorLore = new MirrorLore();
    public static FractionalReserveLore fractionalReserveLore = new FractionalReserveLore();
    public static PitBlobLore pitBlobLore = new PitBlobLore();
    public static EscapePodLore escapePodLore = new EscapePodLore();
    public static PrickLore prickLore = new PrickLore();
    public static GottaGoFastLore gottaGoFastLore = new GottaGoFastLore();
    public static BillyLore billyLore = new BillyLore();
    public static SelfCheckoutLore selfCheckoutLore = new SelfCheckoutLore();
    public static PebbleLore pebbleLore = new PebbleLore();
    public static ArrowArmoryLore arrowArmoryLore = new ArrowArmoryLore();
    public static FletchingLore fletchingLore = new FletchingLore();
    public static JumpspammerLore jumpspammerLore = new JumpspammerLore();
    public static PeroxideLore peroxideLore = new PeroxideLore();
    public static BillionaireLore billionaireLore = new BillionaireLore();
    public static PerunLore perunLore = new PerunLore();
    public static ExecutionerLore executionerLore = new ExecutionerLore();
    public static GambleLore gambleLore = new GambleLore();
    public static KingBusterLore kingBusterLore = new KingBusterLore();
    public static LifestealLore lifestealLore = new LifestealLore();
    public static PainFocusLore painFocusLore = new PainFocusLore();
    public static SharkLore sharkLore = new SharkLore();
    public static SharpLore sharpLore = new SharpLore();
    public static DiamondStompLore diamondStompLore = new DiamondStompLore();
    public static TelebowLore telebowLore = new TelebowLore();
    public static PullBowLore pullBowLore = new PullBowLore();
    public static MegaLongBowLore megaLongBowLore = new MegaLongBowLore();
    public static FasterThenTheirShadowLore fasterThenTheirShadowLore = new FasterThenTheirShadowLore();
    public static SprintDrainLore sprintDrainLore = new SprintDrainLore();
    public static VolleyLore volleyLore = new VolleyLore();
    public static ExplosiveLore explosiveLore = new ExplosiveLore();

    // Data Store
    public static MoonStreak moonStreak = new MoonStreak("moon");

    public static ExperienceIndustrialComplex experienceIndustrialComplex = new ExperienceIndustrialComplex("industrial");
    public static FastPass fastPass = new FastPass("fastpass");
    public static Heresy heresy = new Heresy("heresy");
    public static RenownGoldBoost renownGoldBoost = new RenownGoldBoost("goldboost");
    public static RenownXpBump renownXpBump = new RenownXpBump("xpbump");
    public static Tenacity tenacity = new Tenacity("tenacity");
    public static TheWay theWay = new TheWay("way");
    public static Mysticism mysticism = new Mysticism("mysticChance");
    public static Celebrity celebrity = new Celebrity("celebrity");


    @Override
    public void onEnable() {

        redux plugin = this;

        INSTANCE = this;

        new prestiges(this);
        new economy(this);
        new xpManager(this);
        new Renown(this);
        new Monster(this);


        skyblockItems.initializeItemstack();
        itemManager.init();
        enchants.init();

        List<String> listy = new ArrayList<>();

        getServer().getPluginManager().registerEvents(new ArmorListener(listy), this);

        getServer().getPluginManager().registerEvents(new boards(), this);
        getServer().getPluginManager().registerEvents(new PerkHandler(), this);
        getServer().getPluginManager().registerEvents(new events(),this);
        getServer().getPluginManager().registerEvents(new BoosterEvents(), this);
        getServer().getPluginManager().registerEvents(new bossEvents(), this);
        getServer().getPluginManager().registerEvents(new ReduxEvents(), this);
        getServer().getPluginManager().registerEvents(new skyblockEvents(), this);
        getServer().getPluginManager().registerEvents(new ReduxPlayerHandler(), this);
        getServer().getPluginManager().registerEvents(new InventoryEventManager(), this);
        getServer().getPluginManager().registerEvents(new MysticEventHandlers(), this);
        getServer().getPluginManager().registerEvents(new ClickHandler(), this);
        getServer().getPluginManager().registerEvents(new PermanentUpgrades(), this);
        getServer().getPluginManager().registerEvents(new GoldenHeadItem(), this);
        getServer().getPluginManager().registerEvents(new ViewCore(), this);
        getServer().getPluginManager().registerEvents(new RenownShopUpgradesGUI(), this);
        getServer().getPluginManager().registerEvents(new RenownShopGUI(), this);

        commandRegistration();

        SLAPI.loadPrestige();

        //SLAPI.loadGold();
        SLAPI.loadXp();
        xpManager.XpLevelCalculation();
        SLAPI.loadXPInc();
        SLAPI.loadGoldReq();
        SLAPI.loadMonster();
        SLAPI.loadDmgInc();
        SLAPI.loadRenown();
        SLAPI.loadDmgDec();
        SLAPI.loadMystic();
        SLAPI.loadGoldInc();

        // DataStore
        experienceIndustrialComplex.loadHashMap();
        fastPass.loadHashMap();
        heresy.loadHashMap();
        renownGoldBoost.loadHashMap();
        renownXpBump.loadHashMap();
        tenacity.loadHashMap();
        theWay.loadHashMap();
        mysticism.loadHashMap();
        celebrity.loadHashMap();

        SLAPI.loadGoldBooster();
        SLAPI.loadXpBooster();
        SLAPI.loadBotBooster();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Redux] plugin is enabled");

        Booster.constantBoosterNotify();

        new BukkitRunnable() {

            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()){
                    if(!isNPC(player)){
                        if(player.getInventory().getBoots() != null){
                            if(player.getInventory().getBoots().equals(enchants.malding_boots)){
                                PacketPlayOutWorldParticles animationPacket = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) player.getLocation().getX(), (float) player.getLocation().getY(), (float) player.getLocation().getZ(), 255, 255, 255, (float) 1, 0);
                                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(animationPacket);
                                for (Player players : gearNearby(player, 5, 5, 5)){
                                    ((CraftPlayer) players).getHandle().playerConnection.sendPacket(animationPacket);
                                }
                            }
                        }
                    }
                }

            }
        }.runTaskTimer(plugin, 1, 1);


        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {

                getServer().getScheduler().runTaskTimer(redux.INSTANCE, () -> {
                    for(UUID uuid : boardMap.keySet()){
                        updateBoard(boardMap.get(uuid),Bukkit.getPlayer(uuid));
                    }
                },0,20);
            }
        }, 200L);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {


                RefreshBoard();
                handleTwoEvent();
                Bukkit.broadcastMessage(ChatColor.RED + "Refreshing leader boards!");

            }
        }, 0L, 12000L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getWorlds()) {
                    for (Entity e : w.getEntities()) {
                        if (e instanceof Arrow) {
                            e.remove();

                        }
                    }

            }

            for (Player player : Bukkit.getOnlinePlayers()){
                CreateScore(player);
            }
        }}, 0L,  200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()){
                    NametagEdit.getApi().setNametag(player, ChatEventApiGetLevelColor(player.getDisplayName(), String.valueOf(player.getUniqueId()))+ rank.getNameColor(player), "");
                }
            }
        }, 50L);

/*
        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, true);
        }

        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0, false);
        }

        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 0, true);
        }

        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 0, false);
        }

        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 0, true);
        }

        for (int i = 0; i < 15; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby2")), 0, false);
        }
/*
        for (int i = 0; i < 50; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("world")), 0);
        }

        for (int i = 0; i < 50; i++) {
            HunterAPI.createHunterNon(locations.getBotSpawnLocation(Bukkit.getWorld("lobby")), 0);
        }

        */


        new BukkitRunnable() {

            @Override
            public void run() {
                SLAPI.saveDmgInc();
                SLAPI.saveXPInc();
                SLAPI.saveDmgDec();
                SLAPI.saveMystic();
                SLAPI.savePrestige();
                SLAPI.saveXp();
                SLAPI.saveRenown();
                SLAPI.saveMonster();
                SLAPI.saveBotBooster();
                SLAPI.saveXpBooster();
                SLAPI.saveGoldBooster();
                SLAPI.saveGoldInc();
                SLAPI.saveGoldReq();
                experienceIndustrialComplex.saveHashMap();
                fastPass.saveHashMap();
                heresy.saveHashMap();
                renownGoldBoost.saveHashMap();
                renownXpBump.saveHashMap();
                tenacity.saveHashMap();
                theWay.saveHashMap();
                mysticism.saveHashMap();
                celebrity.saveHashMap();

                SLAPI.loadGoldInc();
                SLAPI.loadMonster();
                SLAPI.loadXPInc();
                SLAPI.loadDmgInc();
                SLAPI.loadGoldReq();
                SLAPI.loadDmgDec();
                SLAPI.loadMystic();
                SLAPI.loadXp();
                SLAPI.loadBotBooster();
                SLAPI.loadGoldBooster();
                SLAPI.loadBotBooster();
                celebrity.loadHashMap();
                experienceIndustrialComplex.loadHashMap();
                mysticism.loadHashMap();
                fastPass.loadHashMap();
                heresy.loadHashMap();
                renownGoldBoost.loadHashMap();
                renownXpBump.loadHashMap();
                tenacity.loadHashMap();
                theWay.loadHashMap();
            }
        }.runTaskTimer(plugin, 12000, 12000);

        getCakeLocation().getBlock().setType(Material.CAKE_BLOCK);

        CreateVillagers.loadNPC();

    }

    @Override
    public void onDisable() {
        getCakeLocation().getBlock().setType(Material.AIR);
        SLAPI.savePrestige();
        SLAPI.saveXp();
        SLAPI.saveDmgInc();
        SLAPI.saveXPInc();
        SLAPI.saveDmgDec();
        SLAPI.saveMystic();
        SLAPI.saveGoldInc();
        SLAPI.saveRenown();
        SLAPI.saveGoldReq();
        SLAPI.saveMonster();
        SLAPI.saveBotBooster();
        SLAPI.saveGoldBooster();
        SLAPI.saveXpBooster();
        experienceIndustrialComplex.saveHashMap();
        fastPass.saveHashMap();
        celebrity.saveHashMap();
        heresy.saveHashMap();
        renownGoldBoost.saveHashMap();
        renownXpBump.saveHashMap();
        tenacity.saveHashMap();
        theWay.saveHashMap();
        mysticism.saveHashMap();
        CreateVillagers.unloadNPC();
        delBoard();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Redux] plugin is disabled");

        for (Player player : MaldingPlayerHandlers.keySet()){

            NPC npc = MaldingPlayerHandlers.get(player).getBoss();
            assert npc != null;
            npc.despawn();
            MaldingPlayerHandlers.remove(player);
            CitizensAPI.getNPCRegistry().deregister(npc);

        }

        for(Player player : blob.keySet()){
            deleteBlob(player);
        }
    }

    private void commandRegistration(){
        command commands = new command();
        getCommand("cookiemonster").setExecutor(commands);
        getCommand("crategive").setExecutor(commands);
        getCommand("enchantPant").setExecutor(commands);
        getCommand("activateBooster").setExecutor(commands);
        getCommand("purchaseDyes").setExecutor(commands);
        getCommand("show").setExecutor(commands);
        getCommand("repairs").setExecutor(commands);
        getCommand("hub").setExecutor(commands);
        getCommand("veloCheck").setExecutor(commands);
        getCommand("play").setExecutor(commands);
        getCommand("oof").setExecutor(commands);
        getCommand("mkBoard").setExecutor(commands);
        getCommand("makeMonersRankers").setExecutor(commands);
        getCommand("rBoard").setExecutor(commands);
        getCommand("spawn").setExecutor(commands);
        getCommand("spawn").setExecutor(commands);
        getCommand("all").setExecutor(commands);
        getCommand("feed").setExecutor(commands);
        getCommand("shop").setExecutor(commands);
        getCommand("kit").setExecutor(commands);
        getCommand("streak").setExecutor(commands);
        getCommand("balance").setExecutor(commands);
        getCommand("KillMessageToggle").setExecutor(commands);
        getCommand("prestige").setExecutor(commands);
        getCommand("prestiges").setExecutor(commands);
        getCommand("mega").setExecutor(commands);
        getCommand("gold").setExecutor(commands);
        getCommand("checkPants").setExecutor(commands);
        getCommand("well").setExecutor(commands);
        getCommand("getXp").setExecutor(commands);
        getCommand("pants").setExecutor(commands);
        getCommand("malding").setExecutor(commands);
        getCommand("damage").setExecutor(commands);
        getCommand("renown").setExecutor(commands);
        getCommand("booster").setExecutor(commands);
        getCommand("atest").setExecutor(commands);
        getCommand("view").setExecutor(commands);
       // getCommand("trade").setExecutor(commands);
    }

    private Plugin getPlugin() {
        return this;
    }

}
