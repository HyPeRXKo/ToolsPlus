package fr.vlxproject.toolsplus.listeners;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class RightClickListener implements Listener {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);


    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();
        if(a == Action.RIGHT_CLICK_AIR && e.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "ToolsPlus"), PersistentDataType.INTEGER)){
            Bukkit.getConsoleSender().sendMessage("Check 1 passed");
            if(e.getItem().getType().toString().toLowerCase().contains("pickaxe")){
                Bukkit.getConsoleSender().sendMessage("Check 2 passed");
                GuiListener gl = new GuiListener();
                gl.openPickaxeGui(p, e.getItem());
            }
        }
    }
}
