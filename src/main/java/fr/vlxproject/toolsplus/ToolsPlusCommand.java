package fr.vlxproject.toolsplus;

import fr.vlxproject.toolsplus.pickaxeutils.dataUtilsPickaxe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ToolsPlusCommand implements TabExecutor {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    plugin.reloadAllConfigs();
                    new messagesUtils().reload(p);
                }
                else if(args[0].equalsIgnoreCase("givetool")){
                    if(args[1].equalsIgnoreCase("pickaxe")){
                        p.getInventory().addItem(dataUtilsPickaxe.itemPickaxe());
                        new messagesUtils().newpick(p);
                    }
                }
                //else if(args[0].equalsIgnoreCase(addlevel)){}
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){ //ToolsPlus <args1>
            List<String> args1 = new ArrayList<>();
            args1.add("GiveTool");
            args1.add("reload");
            args1.add("AddLevel");
            return args1;
        }
        else if(args.length == 2){ //ToolsPlus <args1> <args2>
            if(args[1].equalsIgnoreCase("GiveTool")){ //ToolsPlus GiveTool <args2>
                List<String> args2 = new ArrayList<>();
                args2.add("Pickaxe");
                return args2;
            }
        }
        return null;
    }
}
