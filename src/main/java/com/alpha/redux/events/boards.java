package com.alpha.redux.events;

import com.alpha.redux.apis.FastBoard;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.playerdata.prestiges;
import com.alpha.redux.playerdata.xpManager;
import com.alpha.redux.redux;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.alpha.redux.apis.HeadNames.headnames.changeName;
import static com.alpha.redux.apis.chatManager.levelcolor.getLevelColor;
import static com.alpha.redux.apis.chatManager.prestigebrackets.prestigebracket;
import static com.alpha.redux.apis.chatManager.rank.ChatEventApi;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.apis.leaderboardsplus.leaderboards.RefreshBoard;
import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.events.*;
import static com.alpha.redux.funEvents.event.handleTwoEvent;
import static com.alpha.redux.funEvents.event.twoTimesEvent;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.hasEconomy;
import static com.alpha.redux.playerdata.prestiges.getPrestige;
import static com.alpha.redux.playerdata.prestiges.hasPrestige;
import static com.alpha.redux.playerdata.streaks.*;
import static com.alpha.redux.playerdata.xpManager.GetCurrentLevel;
import static com.alpha.redux.playerdata.xpManager.hasXp;
import static java.lang.Math.abs;
import static java.lang.Math.round;

public class boards implements Listener {

    public static Map<UUID, FastBoard> boardMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(twoTimesEvent == 2){
            BossBar bossBar = BossBarAPI.addBar(player,
                    new TextComponent(colorCode("&d&lMINOR EVENT! &e2x in &e&lPit Area")),
                    BossBarAPI.Color.GREEN,
                    BossBarAPI.Style.NOTCHED_6,
                    1.0f);
        }else{
            BossBarAPI.removeAllBars(player);
        }

        try {
            playerExists(event.getPlayer()).setSpeed(0);
        }catch (Exception ignored){}

        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));
        boardMap.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        BossBarAPI.removeAllBars(player);

        try {
            playerExists(event.getPlayer()).setSpeed(0);
        }catch (Exception ignored){}

        FastBoard board = boardMap.remove(player.getUniqueId());

        if(board!=null){
            board.delete();
        }
    }

    private static String getMegaData(String uuid){
        String select_mega = "";
        String mega_color = "";
        hasMegaStreak(uuid);
        if (Objects.equals(getMegaStreak(uuid), "highlander")){
            select_mega = "Highlander";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&6");
        }else if (Objects.equals(getMegaStreak(uuid), "beastmode")){
            select_mega = "Beastmode";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&a");
        }else if (Objects.equals(getMegaStreak(uuid), "magnum")){
            select_mega = "Magnum Opus";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&e");
        }else if (Objects.equals(getMegaStreak(uuid), "uber")){
            select_mega = "Uberstreak";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&d");
        }else if (Objects.equals(getMegaStreak(uuid), "moon")){
            select_mega = "To the Moon";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&b");
        }else if (Objects.equals(getMegaStreak(uuid), "overdrive")){
            select_mega = "Overdrive";
            mega_color = ChatColor.translateAlternateColorCodes('&', "&c");
        }

        return mega_color + select_mega;
    }

    private static int getMegaActiveData(String uuid){
        hasMegaStreak(uuid);
        if (Objects.equals(getMegaStreak(uuid), "highlander") ||
                Objects.equals(getMegaStreak(uuid), "beastmode") ||
                Objects.equals(getMegaStreak(uuid), "overdrive") ||
                Objects.equals(getMegaStreak(uuid), "magnum")){
            return 50;
        }else if (Objects.equals(getMegaStreak(uuid), "uber") ||
                Objects.equals(getMegaStreak(uuid), "moon")){
            return 100;
        }else {
            return 50;
        }
    }

    public static void updateBoard(FastBoard board, Player player){

        if(player==null) return;

        if(board == null) {

            FastBoard boardy = new FastBoard(player);
            boardy.updateTitle(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));
            boardMap.put(player.getUniqueId(), boardy);

            return;
        }

        String uuid = String.valueOf(player.getUniqueId());
        ReduxPlayer reduxPlayer = playerExists(player);
        int STRENGTH = reduxPlayer.getStrengthTier();

        //Bukkit.broadcastMessage(String.valueOf(reduxPlayer.getSTRENGTH_TIMER()));

        hasStreak(uuid);
        hasEconomy(uuid);
        hasPrestige(uuid);
        hasXp(uuid);

        DecimalFormat formatter = new DecimalFormat("#,###");

        int[] playerData = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        boolean MOON_ACTIVE = false;

        String MOON_STORAGE = "";

        hasMegaStreak(uuid);
        hasStreak(uuid);
        if(getMegaStreak(uuid).equals("moon") && getStreak(uuid) >= 101){
            MOON_ACTIVE=true;
            ReduxPlayer player1 = playerExists(player);

            MOON_STORAGE = colorCode("&fStored XP: &b" + formatter.format(player1.getMoonXP()));
        }

        String prestigeColor = prestigebracket(player);
        String lobby = "M2C";

        if(player.getWorld().getName().equals("world")){
            lobby = "M6B";
        }else if(player.getWorld().getName().equals("lobby")){
            lobby = "M14E";
        }

        String version = ChatColor.GRAY + "v1.4.6 " + ChatColor.DARK_GRAY + lobby; // Pit Redux Version

        String spacer1 = " "; //blank space
        String spacer2 = "  "; //blank space
        String spacer3 = "   "; //blank space
        String spacer4 = "    "; //blank space
        String spacer5 = "     "; //blank space
        String spacer6 = "      "; //blank space
        String spacer7 = "       "; //blank space
        String spacer8 = "        "; //blank space
        String spacer9 = "         "; //blank space
        String spacer10 = "         "; //blank space

        String levelData = ChatColor.WHITE + "Level: " + prestigeColor + "[" + ChatColor.AQUA + getLevelColor(level) + level + prestigeColor + "]";
        String goldData = ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(getEconomy(String.valueOf(player.getUniqueId()))) + "g";
        String streakData = ChatColor.WHITE + "Streak: " + ChatColor.GREEN + getStreak(uuid);
        String ipData = ChatColor.YELLOW + "mc.pitredux.net";
        String prestigeData = ChatColor.WHITE + "Prestige: " + ChatColor.YELLOW + integerToRoman(getPrestige(uuid));

        String strengthData;
        String xpData;
        String statusData;

        if (getStreak(String.valueOf(player.getUniqueId())) >= getMegaActiveData(uuid)){
            statusData = ChatColor.WHITE + "Status: " + ChatColor.RESET + getMegaData(uuid);
        }else if (cooldowns.containsKey(String.valueOf(player.getUniqueId())) &&
                cooldowns.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
            long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
            statusData = ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting " + ChatColor.GRAY + "(" + timeleft + ")";
        }else{statusData = ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling";}

        // If statement to determine needed xp position
        if (neededXP == 323232323){xpData = ChatColor.WHITE + "XP: " + ChatColor.AQUA + "MAXED!";
        }else{xpData = ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(neededXP));}



        if (STRENGTH <= 0){strengthData = ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE";
        }else{strengthData = ChatColor.WHITE + "Strength: " + ChatColor.RED + integerToRoman(STRENGTH) + ChatColor.GRAY + " (" + String.valueOf(reduxPlayer.getSTRENGTH_TIMER()) + "s)";}



        if(MOON_ACTIVE){
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    MOON_STORAGE,
                    spacer1,
                    ipData);
        }else if(STRENGTH > 0 &&
                getStreak(uuid) > 0 && getPrestige(uuid) > 0){
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    strengthData,
                    spacer1,
                    ipData);
        }else if (STRENGTH > 0 &&
                getStreak(uuid) > 0 && getPrestige(uuid) == 0){

            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    strengthData,
                    spacer1,
                    ipData);
        }else if (
                getStreak(uuid) > 0 && getPrestige(uuid) > 0){

            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    spacer1,
                    ipData);
        }else if (
                getStreak(uuid) > 0 && getPrestige(uuid) == 0){

            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    streakData,
                    spacer1,
                    ipData);
        }else if(STRENGTH <= 0 &&
                getStreak(uuid) <= 0 && getPrestige(uuid) > 0){
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        }else if (getPrestige(uuid) <= 0){
            board.updateLines(version,
                    spacer4,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        }else{
            board.updateLines(version,
                    spacer4,
                    prestigeData,
                    levelData,
                    xpData,
                    spacer3,
                    goldData,
                    spacer2,
                    statusData,
                    spacer1,
                    ipData);
        }

    }

    public static void CreateScore(Player player){
        /*ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Title", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + colorCode("&lTHE BETTER PIT"));

        String uuid = String.valueOf(player.getUniqueId());

        hasStreak(uuid);
        hasEconomy(uuid);
        hasPrestige(uuid);
        hasXp(uuid);

        DecimalFormat formatter = new DecimalFormat("#,###");

        int[] playerData = GetCurrentLevel(String.valueOf(player.getUniqueId()), xpManager.getXp(String.valueOf(player.getUniqueId())), prestiges.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = prestigebracket(player);

        Score version = objective.getScore(ChatColor.GRAY + "v1.0.0"); // Pit Redux Version

        Score spacer1 = objective.getScore(" "); //blank space
        Score spacer2 = objective.getScore("  "); //blank space
        Score spacer3 = objective.getScore("   "); //blank space
        Score spacer4 = objective.getScore("    "); //blank space
        Score spacer5 = objective.getScore("     "); //blank space
        Score spacer6 = objective.getScore("      "); //blank space
        Score spacer7 = objective.getScore("       "); //blank space
        Score spacer8 = objective.getScore("        "); //blank space
        Score spacer9 = objective.getScore("         "); //blank space
        Score spacer10 = objective.getScore("         "); //blank space

        Score levelData = objective.getScore(ChatColor.WHITE + "Level: " + prestigeColor + "[" + ChatColor.AQUA + getLevelColor(level) + level + prestigeColor + "]");
        Score goldData = objective.getScore(ChatColor.WHITE + "Gold: " + ChatColor.GOLD + formatter.format(getEconomy(String.valueOf(player.getUniqueId()))) + "g");
        Score streakData = objective.getScore(ChatColor.WHITE + "Streak: " + ChatColor.GREEN + getStreak(uuid));
        Score ipData = objective.getScore(ChatColor.YELLOW + "mc.pitredux.net");
        Score prestigeData = objective.getScore(ChatColor.WHITE + "Prestige: " + ChatColor.YELLOW + integerToRoman(getPrestige(uuid)));

        Score strengthData;
        Score xpData;
        Score statusData;

        if (getStreak(String.valueOf(player.getUniqueId())) >= getMegaActiveData(uuid)){
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RESET + getMegaData(uuid));
        }else if (cooldowns.containsKey(String.valueOf(player.getUniqueId())) &&
                cooldowns.get(String.valueOf(player.getUniqueId())) > System.currentTimeMillis()){
            long timeleft = (cooldowns.get(String.valueOf(player.getUniqueId())) - System.currentTimeMillis()) / 1000;
            statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.RED + "Fighting " + ChatColor.GRAY + "(" + timeleft + ")");
        }else{statusData = objective.getScore(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Idling");}

        // If statement to determine needed xp position
        if (neededXP == 323232323){xpData = objective.getScore(ChatColor.WHITE + "XP: " + ChatColor.AQUA + "MAXED!");
        }else{xpData = objective.getScore(ChatColor.WHITE + "Needed XP: " + ChatColor.AQUA + formatter.format(abs(neededXP)));}

        if (Strength.get(uuid) == null){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");
        }else if(Strength.get(uuid) >= 0 && Strength.get(uuid) <= .10){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "I");
        }else if(Strength.get(uuid) >= .10 && Strength.get(uuid) <= .20){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "II");
        }else if(Strength.get(uuid) >= .20 && Strength.get(uuid) <= .30){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "III");
        }else if(Strength.get(uuid) >= .30 && Strength.get(uuid) <= .40){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "IV");
        }else if(Strength.get(uuid) >= .40 && Strength.get(uuid) <= .50){strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "V");
        }else{strengthData = objective.getScore(ChatColor.WHITE + "Strength: " + ChatColor.RED + "NONE");}



        if(Strength.get(uuid) != null &&
                getStreak(uuid) > 0 && getPrestige(uuid) > 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            strengthData.setScore(3);
            streakData.setScore(4);
            statusData.setScore(5);
            spacer2.setScore(6);
            goldData.setScore(7);
            spacer3.setScore(8);
            xpData.setScore(9);
            levelData.setScore(10);
            prestigeData.setScore(11);
            spacer4.setScore(12);
            version.setScore(13);
        }else if (Strength.get(uuid) != null &&
                getStreak(uuid) > 0 && getPrestige(uuid) == 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            strengthData.setScore(3);
            streakData.setScore(4);
            statusData.setScore(5);
            spacer2.setScore(6);
            goldData.setScore(7);
            spacer3.setScore(8);
            xpData.setScore(9);
            levelData.setScore(10);
            spacer4.setScore(11);
            version.setScore(12);
        }else if(Strength.get(uuid) == null &&
                getStreak(uuid) <= 0 && getPrestige(uuid) > 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            prestigeData.setScore(9);
            spacer4.setScore(10);
            version.setScore(11);
        }else if (getPrestige(uuid) <= 0){
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            spacer4.setScore(9);
            version.setScore(10);
        }else{
            ipData.setScore(1);
            spacer1.setScore(2);
            statusData.setScore(3);
            spacer2.setScore(4);
            goldData.setScore(5);
            spacer3.setScore(6);
            xpData.setScore(7);
            levelData.setScore(8);
            prestigeData.setScore(9);
            spacer4.setScore(10);
            version.setScore(11);
        }

        player.setScoreboard(scoreboard);


         */

        updateBoard(boardMap.get(player.getUniqueId()), player);

    }

    public static StringBuilder integerToRoman(int num) {

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman;
    }



}
