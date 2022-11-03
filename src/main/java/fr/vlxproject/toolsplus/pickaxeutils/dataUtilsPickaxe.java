package fr.vlxproject.toolsplus.pickaxeutils;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class dataUtilsPickaxe {

    static ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public static ItemStack itemPickaxe(){
        String temp;
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lToolsPlus Pickaxe &4&lDEV"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', plugin.getMessagesConfig().getString("Headerpickaxe")));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Level : &b1"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7XP : &b0/170 &7[&c||||||||||||||||||||&7]"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }
}
