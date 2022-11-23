package fr.infinitystudios.toolsplus.listeners;

import fr.infinitystudios.toolsplus.ToolsPlus;
import fr.infinitystudios.toolsplus.utils.dataUtils;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerBlockListener implements Listener {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    public ArrayList<Location> blocksloc = new ArrayList<>();

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        dataUtils dup = new dataUtils();
        if(dup.isToolsPlus(item)){
            if(plugin.getPickaxeblocksConfig().contains(e.getBlock().getBlockData().getMaterial().toString())){
                Boolean silktouch = plugin.getConfig().getBoolean("silktouchgive-exp");
                Boolean playerblocks = plugin.getConfig().getBoolean("playerplacedblockgive-exp");
                if(!playerblocks && !blocksloc.contains(e.getBlock().getLocation())){
                    if(!silktouch && !item.containsEnchantment(Enchantment.SILK_TOUCH)){
                        int xp = plugin.getPickaxeblocksConfig().getInt(e.getBlock().getType().toString());
                        dup.addXP(item, xp, p);
                    } //On donne car il a pas silk touch ET qu'il le faut pas pour l'xp
                    else if(silktouch){
                        int xp = plugin.getPickaxeblocksConfig().getInt(e.getBlock().getType().toString());
                        dup.addXP(item, xp, p);
                    } //On donne car blc de si il a silk touch ou pas
                } //on donne car il n'a pas le droit MAIS y'a pas le bloc de save non plus
                else if(playerblocks){
                    if(!silktouch && !item.containsEnchantment(Enchantment.SILK_TOUCH)){
                        int xp = plugin.getPickaxeblocksConfig().getInt(e.getBlock().getType().toString());
                        dup.addXP(item, xp, p);
                    }
                    else if(silktouch){
                        int xp = plugin.getPickaxeblocksConfig().getInt(e.getBlock().getType().toString());
                        dup.addXP(item, xp, p);
                    }
                } //on donne car il a le droit dans tout les cas
                blocksloc.remove(e.getBlock().getLocation());
            }
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e){
        if(plugin.getPickaxeblocksConfig().contains(e.getBlock().getBlockData().getMaterial().toString())){
            blocksloc.add(e.getBlock().getLocation());
        }
    }
}
