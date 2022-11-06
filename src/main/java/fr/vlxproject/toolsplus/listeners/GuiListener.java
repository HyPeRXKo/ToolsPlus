package fr.vlxproject.toolsplus.listeners;

import fr.vlxproject.toolsplus.ToolsPlus;
import fr.vlxproject.toolsplus.utils.dataUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public void openPickaxeGui(Player p, ItemStack item){
        dataUtils du = new dataUtils();
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', plugin.getMessagesConfig().getString("UpgradeMenuTitle")));
        gui.setItem(0, du.ItemGuiEfficiency(item));
        gui.setItem(1, du.ItemGuiUnbreaking(item));
        gui.setItem(2, du.ItemGuiFortune(item));

        gui.setItem(4, du.ItemGuiMending(item));

        gui.setItem(6, du.ItemGuiSilkTouch(item));

        gui.setItem(8, du.Barrier(9)); //integer code instead of args.

        p.openInventory(gui);
    }
}
