package fr.infinitystudios.toolsplus.listeners;

import fr.infinitystudios.toolsplus.ToolsPlus;
import fr.infinitystudios.toolsplus.utils.dataUtils;
import fr.infinitystudios.toolsplus.utils.enchantsUtils;
import fr.infinitystudios.toolsplus.utils.messagesUtils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class RightClickListener implements Listener {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);


    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        if(a == Action.RIGHT_CLICK_AIR && e.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER)){
            if(e.getItem().getType().toString().toLowerCase().contains("pickaxe")){
                GuiListener gl = new GuiListener();
                gl.openPickaxeGui(p, e.getItem());
            }
        }
    }

    @EventHandler
    public void guiClickEvent(InventoryClickEvent e){
        if(!e.getView().getTitle().contains(ChatColor.translateAlternateColorCodes('&', plugin.getMessagesConfig().getString("UpgradeMenuTitle")))) {return;}
        //Bukkit.getConsoleSender().sendMessage("Check 2 passed");
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        ItemStack tool = p.getInventory().getItemInMainHand();
        dataUtils du = new dataUtils();
        enchantsUtils eu = new enchantsUtils();
        messagesUtils mu = new messagesUtils();
        if(tool.containsEnchantment(Enchantment.SILK_TOUCH) && eu.getsilktouchstate(tool) == 0){
            ItemMeta newmeta = tool.getItemMeta();
            newmeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "silktouchstate"), PersistentDataType.INTEGER, 1);
            tool.setItemMeta(newmeta);
        }
        if(e.getCurrentItem() == null){return;}
        switch (e.getCurrentItem().getType()){
            case IRON_BLOCK:
                p.closeInventory();
                if(du.getPoints(tool) >= du.getEffiCost(tool.getEnchantmentLevel(Enchantment.DIG_SPEED))){
                        eu.addEffi(tool);
                        mu.efficiency(p);
                    }
                else{mu.errorpoints(p);}
                break;
            case ANVIL:
                p.closeInventory();
                if(du.getPoints(tool) >= du.getUnbreakCost(tool.getEnchantmentLevel(Enchantment.DURABILITY))){
                        eu.addUnbreak(tool);
                        mu.unbreaking(p);
                    }
                else{mu.errorpoints(p);}
                break;
            case GOLD_BLOCK:
                p.closeInventory();
                if(du.getPoints(tool) >= du.getFortuneCost(tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS))){
                        eu.addFortune(tool);
                        mu.fortune(p);
                    }
                else{mu.errorpoints(p);}
                break;
            case EXPERIENCE_BOTTLE:
                p.closeInventory();
                if(du.getPoints(tool) >= du.getMendingCost()){
                    eu.addMending(tool);
                    mu.mending(p);
                }
                else{mu.errorpoints(p);}
            case RESPAWN_ANCHOR:
                p.closeInventory();
                int silktouchstate = du.getsilktouchstate(tool);
                if(silktouchstate == 0 && du.getPoints(tool) >= du.getSilkTouchCost()){
                    eu.SwitchSilkTouch(tool);
                    mu.silkTouch(p);
                }
                else if(silktouchstate == 0 && du.getPoints(tool) < du.getSilkTouchCost()){mu.errorpoints(p);}
                else if(silktouchstate == 1 || du.getsilktouchstate(tool) == 2){
                    eu.SwitchSilkTouch(tool);
                    mu.actionbarSwitchSilkTouch(p, silktouchstate);
                }
            case BARRIER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(du.cc("&4Exit"))){
                p.closeInventory();
                break;}
        }
    }
}
