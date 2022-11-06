package fr.vlxproject.toolsplus.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class loreUtils {

    messagesUtils mu = new messagesUtils();

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
    public void UpdateEnchantsPickaxe(ItemStack item){}

//------------------------------------------------------GETLINE---------------------------------------------------------
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


}