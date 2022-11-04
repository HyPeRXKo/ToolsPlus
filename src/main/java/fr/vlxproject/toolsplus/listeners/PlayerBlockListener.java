package fr.vlxproject.toolsplus.listeners;

import fr.vlxproject.toolsplus.ToolsPlus;
import fr.vlxproject.toolsplus.pickaxeutils.dataUtilsPickaxe;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerBlockListener implements Listener {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public ArrayList<Location> blocksloc = new ArrayList<>();

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        dataUtilsPickaxe dup = new dataUtilsPickaxe();
        if(dup.isToolsPlus(item)){
            if(plugin.getPickaxeblocksConfig().contains(e.getBlock().getBlockData().getMaterial().toString())){
                int xp = plugin.getPickaxeblocksConfig().getInt(e.getBlock().getType().toString());
                dup.addXP(item, xp, p);
            }
        }
    }
}
