package fr.vlxproject.toolsplus.utils;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class dataUtils {

    static ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);
    messagesUtils mu = new messagesUtils();

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

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER, 1);
        data.set(new NamespacedKey(plugin, "currentxp"), PersistentDataType.INTEGER, 0);
        data.set(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER, 170);
        data.set(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, 1);
        data.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, 0);


        item.setItemMeta(meta);
        return item;
    }


//----------------------ADD-----------------------------------
    public void addXP(ItemStack item, int XP, Player p){
        int oldxp = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "currentxp"), PersistentDataType.INTEGER);
        int maxxp = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER);
        int level = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);
        int points = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER);
        int newxp = oldxp + XP;
        ItemMeta meta = item.getItemMeta();

        if(newxp >= maxxp){ //On passe au niveau supérieur (xp+level+points)
            int newmaxxp = nextLevelMath(maxxp);
            int newnewxp = newxp - maxxp;
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentxp"), PersistentDataType.INTEGER, newnewxp);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER, newmaxxp);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, level + 1);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, points + 1);
            item.setItemMeta(meta);
            loreUtils lup = new loreUtils();
            lup.UpdateLevelLine(item, level);
            lup.UpdatePointsLine(item, points + 1);
            lup.UpdateXPLine(item, newnewxp, newmaxxp);
            mu.newlevel(level + 1, p);
            mu.newpoints(points + 1, p);
            mu.actionBarXP(p, newnewxp, newmaxxp);
        }
        else { //On reste dans le même niveau (xp only)
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentxp"), PersistentDataType.INTEGER, newxp);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER, maxxp);
            item.setItemMeta(meta);
            new loreUtils().UpdateXPLine(item, newxp, maxxp);
            mu.actionBarXP(p, newxp, maxxp);
        }
    }
    public void addLevelCommand(ItemStack item, Player p){
        int maxxp = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER);
        int level = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER);
        int points = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER);
        ItemMeta meta = item.getItemMeta();

        int newmaxxp = nextLevelMath(maxxp);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "currentxp"), PersistentDataType.INTEGER, 0);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "maxxp"), PersistentDataType.INTEGER, newmaxxp);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, level + 1);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, points + 1);
        item.setItemMeta(meta);
        loreUtils lup = new loreUtils();
        lup.UpdateLevelLine(item, level);
        lup.UpdatePointsLine(item, points + 1);
        lup.UpdateXPLine(item, 0, newmaxxp);
        mu.newlevel(level + 1, p);
        mu.newpoints(points + 1, p);
        mu.actionBarXP(p, 0, newmaxxp);
    }





    public Integer nextLevelMath(int oldmaxxp){
        double random = (Math.random() * (1.35 - 1.15)) + 1.15;
        double addxp = Math.sqrt(oldmaxxp);
        int xp = (int) Math.pow(addxp, random);
        return oldmaxxp + (int) addxp + xp;
    }
    public boolean isToolsPlus(ItemStack item){
        return item != null
                && item.getType() != Material.AIR
                && item.hasItemMeta()
                && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER);
    }
}
