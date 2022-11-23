package fr.infinitystudios.toolsplus.utils;

import fr.infinitystudios.toolsplus.ToolsPlus;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class enchantsUtils {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public void addEffi(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        int enchantlevel = item.getEnchantmentLevel(Enchantment.DIG_SPEED);
        dataUtils du = new dataUtils();
        int newpoints = du.getPoints(item) - du.getEffiCost(enchantlevel);
        meta.addEnchant(Enchantment.DIG_SPEED, enchantlevel + 1, true);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, newpoints);
        item.setItemMeta(meta);
        loreUtils lu = new loreUtils();
        lu.UpdateEnchantsPickaxe(item);
        lu.UpdatePointsLine(item, newpoints);
    }
    public void addUnbreak(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        int enchantlevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        dataUtils du = new dataUtils();
        int newpoints = du.getPoints(item) - du.getUnbreakCost(enchantlevel);
        meta.addEnchant(Enchantment.DURABILITY, enchantlevel + 1, true);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, newpoints);
        item.setItemMeta(meta);
        loreUtils lu = new loreUtils();
        lu.UpdateEnchantsPickaxe(item);
        lu.UpdatePointsLine(item, newpoints);
    }
    public void addFortune(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        int enchantlevel = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
        dataUtils du = new dataUtils();
        int newpoints = du.getPoints(item) - du.getFortuneCost(enchantlevel);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, enchantlevel + 1, true);
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, newpoints);
        item.setItemMeta(meta);
        loreUtils lu = new loreUtils();
        lu.UpdateEnchantsPickaxe(item);
        lu.UpdatePointsLine(item, newpoints);
    }
    public void addMending(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.MENDING, 1, true);
        dataUtils du = new dataUtils();
        int newpoints = du.getPoints(item) - du.getMendingCost();
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, newpoints);
        item.setItemMeta(meta);
        loreUtils lu = new loreUtils();
        lu.UpdateSpecialPickaxe(item);
        lu.UpdatePointsLine(item, newpoints);
    }
    public void SwitchSilkTouch(ItemStack item){
        //0 = no buy / 1 = activated / 2 = deactivated
        int silktouchstateold = getsilktouchstate(item);
        if(silktouchstateold == 0){
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
            dataUtils du = new dataUtils();
            int newpoints = du.getPoints(item) - du.getSilkTouchCost();
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, newpoints);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER, 1);
            item.setItemMeta(meta);
            loreUtils lu = new loreUtils();
            lu.UpdateSpecialPickaxe(item);
            lu.UpdatePointsLine(item, newpoints);
        } //La on veut ACTIVER le silk touch, car il vient de l'acheter
        if(silktouchstateold == 1){
            ItemMeta meta = item.getItemMeta();
            meta.removeEnchant(Enchantment.SILK_TOUCH);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER, 2);
            item.setItemMeta(meta);
            loreUtils lu = new loreUtils();
            lu.UpdateSpecialPickaxe(item);
        } //La on veut DESACTIVER le silk touch, vu qu'il était déjà activer
        if(silktouchstateold == 2){
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER, 1);
            item.setItemMeta(meta);
            loreUtils lu = new loreUtils();
            lu.UpdateSpecialPickaxe(item);
        } //La on veut ACTIVER le silk touch, car il était desactiver
    }

    public int getsilktouchstate(ItemStack item){
        if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER)){
            return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER);
        }
        return 0;
    }
}
