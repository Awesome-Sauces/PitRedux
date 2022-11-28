package com.alpha.redux.Stash;

import com.alpha.redux.items.enchants;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class StashCore {
    public static boolean hasAvailableSlot(Player player){
        Inventory inv = player.getInventory();
        boolean check=false;
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                check = true;
                break;
            }
        }

        return check;
    }

    private static void claimedAllMessage(Player player){
        player.sendMessage(colorCode("&e&lCOLLECTED! &7You picked up &aall &7items from your item stash!"));
    }

    private static void claimedSomeMessage(Player player, int amount){
        player.sendMessage(colorCode("&e&lCOLLECTED! &7You picked up &a"+amount+" &7items from your item stash!"));
    }

    private static void emptyMessage(Player player){
        player.sendMessage(colorCode("&c&lTHAT'S ILLEGAL! &7There are no items in your stash!"));
    }

    private static void cannotMessage(Player player){
        player.sendMessage(colorCode("&c&lCANNOT UNSTASH! &7Your inventory is full!"));
    }

    public static void reminderMessage(Player player){
        if(!StashData.getStashData(player.getUniqueId()).isEmpty()){
            // Make a new component (Bungee API).
            TextComponent component = new TextComponent(TextComponent.fromLegacyText(colorCode("&e&lITEM STASH! &7You Have &e"+StashData.getStashData(player.getUniqueId()).size()+" &7items stashed away! [&e&lCLICK&7]")));
            // Add a click event to the component.
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/stash"));

            // Send it!
            player.spigot().sendMessage(component);
        }
    }

    public static void safeGive(Player player, ItemStack itemStack){
        if(hasAvailableSlot(player)){
            player.getInventory().addItem(itemStack);
        }else{
            StashData.addStashData(player.getUniqueId(), itemStack);
            StashCore.reminderMessage(player);
        }
    }

    public static void safeGiveMultiple(Player player, ItemStack itemStack, int amount){
        for (int i = 0; i < amount; i++) {
            safeGive(player, itemStack);
        }
    }

    public static void claimStash(Player player){
        if(StashData.getStashData(player.getUniqueId()).isEmpty()){
            emptyMessage(player);
        }else{
            if(!hasAvailableSlot(player)){
                cannotMessage(player);
            }else{
                List<ItemStack> items = StashData.getStashData(player.getUniqueId());

                int claimed = 0;

                if(items!=null) for(Object item : items.toArray()){
                    if(hasAvailableSlot(player)){
                        player.getInventory().addItem((ItemStack) item);
                        items.remove(item);
                        claimed++;
                    }else{
                        break;
                    }
                }

                if(items!=null&&items.isEmpty()){
                    claimedAllMessage(player);
                }else{
                    claimedSomeMessage(player, claimed);
                }

                StashData.setStashData(player.getUniqueId(), items);

                reminderMessage(player);

            }
        }
    }
}
