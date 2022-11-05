package fr.vlxproject.toolsplus.commands;

import fr.vlxproject.toolsplus.ToolsPlus;
import fr.vlxproject.toolsplus.utils.dataUtils;
import fr.vlxproject.toolsplus.utils.messagesUtils;
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    plugin.reloadAllConfigs();
                    new messagesUtils().reload(p);
                }
                else if(args[0].equalsIgnoreCase("addlevel")){
                    ItemStack item = p.getInventory().getItemInMainHand();
                    dataUtils dup = new dataUtils();
                    if(dup.isToolsPlus(item)){
                        dup.addLevelCommand(item, p);
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
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){ //ToolsPlus <args1>
            List<String> args1 = new ArrayList<>();
            args1.add("Givetool");
            args1.add("Reload");
            args1.add("Addlevel");
            args1.add("Settings");
            return args1;
        }
        else if(args.length == 2){ //ToolsPlus <args1> <args2>
            if(args[0].equalsIgnoreCase("givetool")){ //ToolsPlus GiveTool <args2>
                List<String> args2 = new ArrayList<>();
                args2.add("Pickaxe");
                return args2;

            }
            if(args[0].equalsIgnoreCase("settings")){
                List<String> args2 = new ArrayList<>();
                args2.add("Actionbar");
                args2.add("Xpsound");
                args2.add("Levelupsound");
                return args2;
            }
        }
        else if(args.length == 3){ //ToolsPlus <args1> <args2> <args3>
            if(args[1].equalsIgnoreCase("actionbar") || args[1].equalsIgnoreCase("xpsound") || args[1].equalsIgnoreCase("levelupsound")){
                List<String> args3 = new ArrayList<>();
                args3.add("True");
                args3.add("False");
                return args3;
            }
        }
        return new ArrayList<>();
    }
}