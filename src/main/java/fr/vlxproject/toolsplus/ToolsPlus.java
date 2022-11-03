package fr.vlxproject.toolsplus;

import fr.vlxproject.toolsplus.listeners.PlayerBlockListener;
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

    @Override
    public void onEnable() {
        this.saveDefaultConfig(); createBlocksConfig(); createMessagesConfig();
        getCommand("ToolsPlus").setExecutor(new ToolsPlusCommand());
        getServer().getPluginManager().registerEvents(new PlayerBlockListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getBlocksConfig() {
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
