package fr.vlxproject.toolsplus.listeners;

import fr.vlxproject.toolsplus.ToolsPlus;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class PlayerFirstConnect implements Listener {


    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    @EventHandler
    public void onPlayerFirstConnect(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            if(plugin.getConfig().getBoolean("XPactionbar")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPactionbar"), PersistentDataType.INTEGER, 1);
            }
            else if(!plugin.getConfig().getBoolean("XPactionbar")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPactionbar"), PersistentDataType.INTEGER, 0);
            }
            if(plugin.getConfig().getBoolean("XPSound")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPSound"), PersistentDataType.INTEGER, 1);
            }
            else if(!plugin.getConfig().getBoolean("XPSound")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPSound"), PersistentDataType.INTEGER, 0);
            }
            if(plugin.getConfig().getBoolean("LevelUpSound")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "LevelUpSound"), PersistentDataType.INTEGER, 1);
            }
            else if(!plugin.getConfig().getBoolean("LevelUpSound")){
                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "LevelUpSound"), PersistentDataType.INTEGER, 0);
            }
        }
    }
}
