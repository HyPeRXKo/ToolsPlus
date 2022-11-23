package fr.vlxproject.toolsplus;

import fr.vlxproject.toolsplus.commands.ToolsPlusCommand;
import fr.vlxproject.toolsplus.listeners.GuiListener;
import fr.vlxproject.toolsplus.listeners.PlayerBlockListener;
import fr.vlxproject.toolsplus.listeners.PlayerFirstConnect;
import fr.vlxproject.toolsplus.listeners.RightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class ToolsPlus extends JavaPlugin {

    private File pickaxeblocksConfigFile;
    private File messagesConfigFile;
    private FileConfiguration pickaxeblocksConfig;
    private FileConfiguration messagesConfig;
    private double configversion = 1.1;

    @Override
    public void onEnable() {
        this.saveDefaultConfig(); createBlocksConfig(); createMessagesConfig();
        getCommand("ToolsPlus").setExecutor(new ToolsPlusCommand());
        getServer().getPluginManager().registerEvents(new PlayerBlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFirstConnect(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        checkconfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void checkconfig(){
        if(getConfig().getDouble("configversion") != configversion){
            Bukkit.getLogger().severe("[ToolsPlus] Please regenerate your configs");
        }
    }

    public FileConfiguration getPickaxeblocksConfig() {
        return this.pickaxeblocksConfig;}

    public FileConfiguration getMessagesConfig(){
        return this.messagesConfig;}

    public void reloadAllConfigs(){
//      test and load blocks config
        if(!this.pickaxeblocksConfigFile.exists()){createBlocksConfig();}
        else{
            try{
                pickaxeblocksConfig.load(pickaxeblocksConfigFile);}
            catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();}}
//      test and load messages config
        if(!this.messagesConfigFile.exists()){createMessagesConfig();}
        else{
            try{
                messagesConfig.load(messagesConfigFile);}
            catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();}}
//      load config.yml
        this.saveDefaultConfig();
        super.reloadConfig();
    }

    private void createBlocksConfig() {
        pickaxeblocksConfigFile = new File(getDataFolder(), "pickaxeblocks.yml");
        if (!pickaxeblocksConfigFile.exists()) {
            pickaxeblocksConfigFile.getParentFile().mkdirs();
            saveResource("pickaxeblocks.yml", false);}
        pickaxeblocksConfig= new YamlConfiguration();
        try{
            pickaxeblocksConfig.load(pickaxeblocksConfigFile);}
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();}
    }

    private void createMessagesConfig() {
        messagesConfigFile = new File(getDataFolder(), "messages.yml");
        if (!messagesConfigFile.exists()) {
            messagesConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);}
        messagesConfig= new YamlConfiguration();
        try{
            messagesConfig.load(messagesConfigFile);}
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();}
    }


}
