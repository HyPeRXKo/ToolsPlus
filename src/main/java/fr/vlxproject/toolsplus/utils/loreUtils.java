package fr.vlxproject.toolsplus.utils;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class loreUtils {
    public String cc(String string){
        return ChatColor.translateAlternateColorCodes('&', string);}

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);
    public String getProgressBar(int current, int max) {
        String progressBar = " ";
        int totalBars = 20;
        char symbol = '|';
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);
        progressBar = progressBar + ChatColor.GRAY + '[';
        for (int i = 0; i < progressBars; i++) {
            progressBar = progressBar + ChatColor.GREEN + symbol;
        }
        for (int i = 0; i < totalBars - progressBars; i++) {
            progressBar = progressBar + ChatColor.RED + symbol;
        }
        progressBar = progressBar + ChatColor.GRAY + ']';
        return progressBar;
    }
    public int getsilktouchstate(ItemStack item){
        if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER)){
            return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER);
        }
        return 0;
    }

//---------------------------------------------------------UPDATE-------------------------------------------------------

    public void UpdateLevelLine(ItemStack item, int oldLevel){
    ItemMeta meta = item.getItemMeta();
    ArrayList<String> lore = (ArrayList<String>) meta.getLore();
    int leveline = getLevelLine(lore);
    int newlevel = oldLevel + 1;
    lore.set(leveline, ChatColor.translateAlternateColorCodes('&', "&7Level : &b" + newlevel));
    meta.setLore(lore);
    item.setItemMeta(meta);
}
    public void UpdateXPLine(ItemStack item, int XP, int MaxXP){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        int xpline = getXPLine(lore);
        String temp = "&7XP: &b" + XP + "&7/&b" + MaxXP + getProgressBar(XP, MaxXP);
        lore.set(xpline, ChatColor.translateAlternateColorCodes('&', temp));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public void UpdatePointsLine(ItemStack item, int newPoints){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        int pointsline = getPointsLine(lore);
        if(pointsline != 0){
            lore.set(pointsline,ChatColor.translateAlternateColorCodes('&', "&7You have &b" + newPoints + " &7unused points"));
        } else {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7You have &b" + newPoints + " &7unused points"));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public void UpdateEnchantsPickaxe(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        String enchants = "";
        int enchantsline = getEnchantsLine(lore);
        if(item.containsEnchantment(Enchantment.DIG_SPEED)){
            enchants = "&2Efficiency &b" + item.getEnchantmentLevel(Enchantment.DIG_SPEED) + " ";
        }
        if(item.containsEnchantment(Enchantment.DURABILITY)){
            if(item.containsEnchantment(Enchantment.DIG_SPEED)){
                enchants = enchants + "&7| ";
            }
            enchants = enchants + "&3Unbreaking &b" + item.getEnchantmentLevel(Enchantment.DURABILITY) + " ";
        }
        if(item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            if (item.containsEnchantment(Enchantment.DIG_SPEED) || item.containsEnchantment(Enchantment.DURABILITY)) {
                enchants = enchants + "&7| ";
            }
            enchants = enchants + "&6Fortune &b" + item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        }
        enchants = cc(enchants);
        if(enchantsline == 99){
            lore.add(1, enchants);
        }
        else{
            lore.set(enchantsline, enchants);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public void UpdateSpecialPickaxe(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        String enchants = "";
        int specialline = getSpecialLine(lore);
        int silktouchstate = getsilktouchstate(item);
        if(silktouchstate == 1){
            enchants = "&aSilk Touch" + " ";
        }
        else if(silktouchstate == 2){
            enchants = "&cSilk Touch" + " ";
        }
        if(item.containsEnchantment(Enchantment.MENDING)){
            if(silktouchstate == 1 || silktouchstate == 2){
                enchants = enchants + "&7| ";
            }
            enchants = enchants + "&dMending";
        }
        enchants = cc(enchants);
        if(specialline == 99){
            lore.add(2, enchants);
        }
        else{
            lore.set(specialline, enchants);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

//------------------------------------------------------GETLINE---------------------------------------------------------
    public int getEnchantsLine(ArrayList<String> lore){
        int loreline = 99;
        String lorestring;
        for (int i = 0; i < lore.size(); i++) {
            lorestring = lore.get(i);
            lorestring = ChatColor.stripColor(lorestring);
            if (lorestring.contains("Efficiency") || lorestring.contains("Unbreaking") || lorestring.contains("Fortune")) {
                loreline = i;
                break;
            }
        }
        return loreline;
    }
    public int getLevelLine(ArrayList<String> lore){
        int loreline = 0;
        String lorestring;
        for (int i = 0; i < lore.size(); i++) {
            lorestring = lore.get(i);
            lorestring = ChatColor.stripColor(lorestring);
            if (lorestring.contains("Level")) {
                loreline = i;
                break;
            }
        }
        return loreline;
    }
    public int getXPLine(ArrayList<String> lore){
        int loreline = 0;
        String lorestring;
        for (int i = 0; i < lore.size(); i++) {
            lorestring = lore.get(i);
            lorestring = ChatColor.stripColor(lorestring);
            if (lorestring.contains("XP")) {
                loreline = i;
                break;
            }
        }
        return loreline;
    }
    public int getPointsLine(ArrayList<String> lore){
        int loreline = 0;
        String lorestring;
        for (int i = 0; i < lore.size(); i++) {
            lorestring = lore.get(i);
            lorestring = ChatColor.stripColor(lorestring);
            if (lorestring.contains("points")) {
                loreline = i;
                break;
            }
        }
        return loreline;
    }

    public int getSpecialLine(ArrayList<String> lore){
        int loreline = 99;
        String lorestring;
        for (int i = 0; i < lore.size(); i++) {
            lorestring = lore.get(i);
            lorestring = ChatColor.stripColor(lorestring);
            if (lorestring.contains("Mending") || lorestring.contains("Silk Touch")) {
                loreline = i;
                break;
            }
        }
        return loreline;
    }
}