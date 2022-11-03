package fr.vlxproject.toolsplus;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class messagesUtils {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public void efficiency(Player p) {
        String temp = plugin.getMessagesConfig().getString("Prefix") + " " + plugin.getMessagesConfig().getString("efficiency");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', temp));
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
