package fr.vlxproject.toolsplus.utils;

import fr.vlxproject.toolsplus.ToolsPlus;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class messagesUtils {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public void efficiency(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("efficiency");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void actionBarXP(Player p,int XP, int MaxXP){
        if(p.getPersistentDataContainer().get(new NamespacedKey(plugin, "XPactionbar"), PersistentDataType.INTEGER) == 1){
            loreUtils lup = new loreUtils();
            String temp = "&7XP: &b" + XP + "&7/&b" + MaxXP + lup.getProgressBar(XP, MaxXP);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', temp)));
        }
        if(p.getPersistentDataContainer().get(new NamespacedKey(plugin, "XPSound"), PersistentDataType.INTEGER) == 1){
            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        }
    }

    public void unbreaking(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("unbreaking");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void fortune(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("fortune");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void newlevel(int level, Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("newlevel");
        temp = temp.replace("%level%", String.valueOf(level));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
        if(p.getPersistentDataContainer().get(new NamespacedKey(plugin, "LevelUpSound"), PersistentDataType.INTEGER) == 1){
            p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1, (float) 1.5);
        }
    }

    public void newpoints(int points, Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("newpoints");
        temp = temp.replace("%points%", String.valueOf(points));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void silkTouch(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("silktouch");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void mending(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("mending");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void newpick(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("newpick");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }

    public void reload(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("reload");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
    }
}
