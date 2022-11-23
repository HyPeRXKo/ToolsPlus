package fr.vlxproject.toolsplus.commands;

import fr.vlxproject.toolsplus.ToolsPlus;
import fr.vlxproject.toolsplus.utils.dataUtils;
import fr.vlxproject.toolsplus.utils.messagesUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ToolsPlusCommand implements TabExecutor {

    ToolsPlus plugin = ToolsPlus.getPlugin(ToolsPlus.class);
    private String help = ChatColor.translateAlternateColorCodes('&', "&b&lToolsPlus" +
            "\n");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    if(p.isOp()) {
                        plugin.reloadAllConfigs();
                        new messagesUtils().reload(p);
                    }
                }
                else if(args[0].equalsIgnoreCase("addlevel")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    dataUtils dup = new dataUtils();
                    if(dup.isToolsPlus(item)) {
                        if (args.length > 1) {
                            int commandlevel;
                            try{
                                commandlevel = Integer.parseInt(args[1]);
                            }
                            catch (NumberFormatException e){
                                messagesUtils mu = new messagesUtils();
                                mu.errorcommand(p);
                                return true;
                            }
                            dup.addLevelCommandMult(item, p, commandlevel);
                        } else {
                            dup.addLevelCommand(item, p);
                        }
                    }
                }
                else if(args[0].equalsIgnoreCase("givetool")){
                    if(args.length > 1){
                        if(args[1].equalsIgnoreCase("pickaxe")){
                        p.getInventory().addItem(dataUtils.itemPickaxe());
                        new messagesUtils().newpick(p);
                    }
                    }
                }
                else if(args[0].equalsIgnoreCase("upgradetool")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    dataUtils dup = new dataUtils();
                    if(dup.isToolsPlus(item)){
                        dup.upgradeToolCommand(item, p);
                    }
                }
                else if(args[0].equalsIgnoreCase("settings")){
                    if(args.length > 2){
                        if(args[1].equalsIgnoreCase("Actionbar")){
                            if(args[2].equalsIgnoreCase("true")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPactionbar"), PersistentDataType.INTEGER, 1);
                            }
                            else if(args[2].equalsIgnoreCase("false")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPactionbar"), PersistentDataType.INTEGER, 0);
                            }
                        }
                        else if(args[1].equalsIgnoreCase("Xpsound")){
                            if(args[2].equalsIgnoreCase("true")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPSound"), PersistentDataType.INTEGER, 1);
                            }
                            else if(args[2].equalsIgnoreCase("false")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "XPSound"), PersistentDataType.INTEGER, 0);
                            }
                        }
                        else if(args[1].equalsIgnoreCase("Levelupsound")){
                            if(args[2].equalsIgnoreCase("true")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "LevelUpSound"), PersistentDataType.INTEGER, 1);
                            }
                            else if(args[2].equalsIgnoreCase("false")){
                                p.getPersistentDataContainer().set(new NamespacedKey(plugin, "LevelUpSound"), PersistentDataType.INTEGER, 0);

                            }
                        }
                    }
                }
            }
            else{p.sendMessage(help);}
        }
        return true;
    }


    private List<String> args1 = new ArrayList<>();
    private List<String> args2tool = new ArrayList<>();
    private List<String> args2settings = new ArrayList<>();
    private List<String> args3truefalse = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args1.isEmpty()){
            args1.add("Givetool");
            args1.add("Reload");
            args1.add("Addlevel");
            args1.add("UpgradeTool");
            args1.add("Settings");
        }
        if(args2tool.isEmpty()) {
            args2tool.add("Pickaxe");
        }
        if(args2settings.isEmpty()){
            args2settings.add("Actionbar");
            args2settings.add("Xpsound");
            args2settings.add("Levelupsound");
        }
        if(args3truefalse.isEmpty()){
            args3truefalse.add("True");
            args3truefalse.add("False");
        }

        List<String> result = new ArrayList<>();
        if(args.length == 1){ //ToolsPlus <args1>
            for(String a : args1){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())) result.add(a);
            }
            return result;
        }
        else if(args.length == 2){ //ToolsPlus <args1> <args2>
            if(args[0].equalsIgnoreCase("givetool")){//ToolsPlus GiveTool <args2>
                for(String a : args2tool){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase())) result.add(a);
                }
                return result;

            }
            if(args[0].equalsIgnoreCase("settings")){
                for(String a : args2settings){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase())) result.add(a);
                }
                return result;
            }
        }
        else if(args.length == 3){ //ToolsPlus <args1> <args2> <args3>
            if(args[1].equalsIgnoreCase("actionbar") || args[1].equalsIgnoreCase("xpsound") || args[1].equalsIgnoreCase("levelupsound")){
                for(String a : args3truefalse){
                    if(a.toLowerCase().startsWith(args[2].toLowerCase())) result.add(a);
                }
                return result;
            }
        }
        return new ArrayList<>();
    }
}