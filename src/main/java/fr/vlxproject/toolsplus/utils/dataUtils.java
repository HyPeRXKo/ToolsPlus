package fr.vlxproject.toolsplus.utils;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class dataUtils {

    static ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);
    messagesUtils mu = new messagesUtils();
    public String cc(String string){
        return ChatColor.translateAlternateColorCodes('&', string);}


    public static ItemStack itemPickaxe(){
        String temp;
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
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
        data.set(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER, 0);


        item.setItemMeta(meta);
        return item;
    }
    public ItemStack ItemGuiEfficiency(ItemStack item){
        String temp;
        ItemStack itemEffi = new ItemStack(Material.IRON_BLOCK);
        ItemMeta meta = itemEffi.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        int effilevel = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
        if(effilevel == 0){
            temp = "&2&lEfficiency &8[&c0 &8> &a1&8]";
            meta.setDisplayName(cc(temp));
            lore.add("");
            temp = "&7Cost : &6" + getEffiCost(effilevel) + " &7points";
            lore.add(cc(temp));
            temp = "&aClick to upgrade";
            lore.add(cc(temp));
        }
        if(effilevel > 0){
            if(effilevel < 5){
                temp = "&2&lEfficiency &8[&c" + effilevel + " &8> &a" + (effilevel + 1) + "&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Cost : &6" + getEffiCost(effilevel) + " &7points";
                lore.add(cc(temp));
                temp = "&aClick to upgrade";
                lore.add(cc(temp));
            }
            else{
                return Barrier(1);
            }
        }
        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemEffi.setItemMeta(meta);
        return itemEffi;
    }
    public ItemStack ItemGuiUnbreaking(ItemStack item){
        String temp;
        ItemStack itemunbreak = new ItemStack(Material.ANVIL);
        ItemMeta meta = itemunbreak.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        int unbreaklevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        if(unbreaklevel == 0){
            temp = "&3&lUnbreaking &8[&c0 &8> &a1&8]";
            meta.setDisplayName(cc(temp));
            lore.add("");
            temp = "&7Cost : &6" + getUnbreakCost(unbreaklevel) + " &7points";
            lore.add(cc(temp));
            temp = "&aClick to upgrade";
            lore.add(cc(temp));
        }
        if(unbreaklevel > 0){
            if(unbreaklevel < 5){
                temp = "&3&lUnbreakng &8[&c" + unbreaklevel + " &8> &a" + (unbreaklevel + 1) + "&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Cost : &6" + getUnbreakCost(unbreaklevel) + " &7points";
                lore.add(cc(temp));
                temp = "&aClick to upgrade";
                lore.add(cc(temp));
            }
            else{
                return Barrier(2);
            }
        }
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemunbreak.setItemMeta(meta);
        return itemunbreak;
    }
    public ItemStack ItemGuiFortune(ItemStack item){
        String temp;
        ItemStack itemfortune = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = itemfortune.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        int fortunelevel = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        if(fortunelevel == 0){
            temp = "&6&lFortune &8[&c0 &8> &a1&8]";
            meta.setDisplayName(cc(temp));
            lore.add("");
            temp = "&7Cost : &6" + getFortuneCost(fortunelevel) + " &7points";
            lore.add(cc(temp));
            temp = "&aClick to upgrade";
            lore.add(cc(temp));
        }
        if(fortunelevel > 0){
            if(fortunelevel < 5){
                temp = "&6&lFortune &8[&c" + fortunelevel + " &8> &a" + (fortunelevel + 1) + "&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Cost : &6" + getFortuneCost(fortunelevel) + " &7points";
                lore.add(cc(temp));
                temp = "&aClick to upgrade";
                lore.add(cc(temp));
            }
            else{
                return Barrier(3);
            }
        }
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemfortune.setItemMeta(meta);
        return itemfortune;
    }
    public ItemStack ItemGuiMending(ItemStack item) {
        String temp;
        ItemStack itemmending = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = itemmending.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        if (item.getEnchantmentLevel(Enchantment.MENDING) == 0) {
            temp = "&d&lMending &8[&c0 &8> &a1&8]";
            meta.setDisplayName(cc(temp));
            lore.add("");
            temp = "&7Cost : &6" + getMendingCost() + " &7points";
            lore.add(cc(temp));
            temp = "&aClick to upgrade";
            lore.add(cc(temp));
        }
        else {
            return Barrier(4);
        }
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemmending.setItemMeta(meta);
        return itemmending;
    }
    public ItemStack ItemGuiSilkTouch(ItemStack item){
        String temp;
        ItemStack itemsilktouch = new ItemStack(Material.RESPAWN_ANCHOR);
        ItemMeta meta = itemsilktouch.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        if(item.containsEnchantment(Enchantment.SILK_TOUCH) && item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "SilkTouchState"), PersistentDataType.INTEGER) == 0){
            item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(plugin, "SilkTouchState"), PersistentDataType.INTEGER, 1);
        }
        int silktouchstate = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "SilkTouchState"), PersistentDataType.INTEGER);
        switch (silktouchstate){
            case 0:
                temp = "&9&lSilk Touch &8[&c0 &8> &a1&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Cost : &6" + getSilkTouchCost() + " &7points";
                lore.add(cc(temp));
                temp = "&aClick to upgrade";
                lore.add(cc(temp));
                meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                break;
            case 1:
                temp = "&9&lSilk Touch &8[&aActivated&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Silk Touch is currently &aActivated";
                lore.add(cc(temp));
                temp = "&cClick to deactivate";
                lore.add(cc(temp));
                meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
                break;
            case 2:
                temp = "&9&lSilk Touch &8[&cDeactivated&8]";
                meta.setDisplayName(cc(temp));
                lore.add("");
                temp = "&7Silk Touch is currently &cDeactivated";
                lore.add(cc(temp));
                temp = "&aClick to activate";
                lore.add(cc(temp));
                break;
        }
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        itemsilktouch.setItemMeta(meta);
        return itemsilktouch;
    }
    public ItemStack Barrier(int arg){
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta meta = barrier.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        switch (arg){
            case 1:
                meta.setDisplayName(cc("&2&lEfficiency"));
                lore.add(cc("&cYou have the maximum level"));
                meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                meta.setLore(lore);
                barrier.setItemMeta(meta);
                return barrier;
            case 2:
                meta.setDisplayName(cc("&3&lUnbreaking"));
                lore.add(cc("&cYou have the maximum level"));
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
                meta.setLore(lore);
                barrier.setItemMeta(meta);
                return barrier;
            case 3:
                meta.setDisplayName(cc("&6&lFortune"));
                lore.add(cc("&cYou have the maximum level"));
                meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                meta.setLore(lore);
                barrier.setItemMeta(meta);
                return barrier;
            case 4:
                meta.setDisplayName(cc("&d&lMending"));
                lore.add(cc("&cYou have the maximum level"));
                meta.addEnchant(Enchantment.MENDING, 1, true);
                meta.setLore(lore);
                barrier.setItemMeta(meta);
                return barrier;
            case 9:
                meta.setDisplayName(cc("&4Exit"));
                lore.add(cc("&cClick here to exit"));
                meta.setLore(lore);
                barrier.setItemMeta(meta);
                return barrier;
        }
        return null;
    }

    public int getEffiCost(int effilevel){
        int[] costlist = Arrays.stream(plugin.getConfig().getString("efficost").split(",")).mapToInt(Integer::parseInt).toArray();
        switch (effilevel){
            case 0: return costlist[0];
            case 1: return costlist[1];
            case 2: return costlist[2];
            case 3: return costlist[3];
            case 4: return costlist[4];
        }
        return 99;
    }
    public int getUnbreakCost(int unbreaklevel){
        int[] costlist = Arrays.stream(plugin.getConfig().getString("unbreakcost").split(",")).mapToInt(Integer::parseInt).toArray();
        switch (unbreaklevel){
            case 0: return costlist[0];
            case 1: return costlist[1];
            case 2: return costlist[2];
        }
        return 99;
    }
    public int getFortuneCost(int fortunelevel){
        int[] costlist = Arrays.stream(plugin.getConfig().getString("fortunecost").split(",")).mapToInt(Integer::parseInt).toArray();
        switch (fortunelevel){
            case 0: return costlist[0];
            case 1: return costlist[1];
            case 2: return costlist[2];
        }
        return 99;
    }
    public int getMendingCost(){
        return plugin.getConfig().getInt("mendingcost");
    }
    public int getSilkTouchCost(){
        return plugin.getConfig().getInt("silktouchcost");
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
