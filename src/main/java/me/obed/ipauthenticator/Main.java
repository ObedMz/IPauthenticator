package me.obed.ipauthenticator;

import me.obed.ipauthenticator.Commands.CommandManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public final class Main extends Plugin implements Listener {
    private ConfigurationProvider cp = ConfigurationProvider.getProvider(YamlConfiguration.class);
    public HashMap<String,String> players = new HashMap<String,String>();
    public HashMap<String,String> tried = new HashMap<String,String>();
    ArrayList<String> data = new ArrayList<String>();
    private static Main instance;
    private ConfigManager config;
    public CommandManager commandManager;
    private boolean logger;
    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigManager();
        config.registerConfig();
        config.registerLogger();
        commandManager = new CommandManager();
        commandManager.setup();
        // setting up
        logger = Boolean.parseBoolean(config.getConfig().getString("config.logger"));
        for(String str : config.getConfig().getStringList("config.players")){
            String name = str.split(",")[0].toLowerCase();
            String ip = str.split(",")[1];
            players.put(name, ip);
        }

        if(logger){
            data.addAll(config.getLogger().getStringList("logger.try"));
        }

        getProxy().getConsole().sendMessage(getMessageByConfig("config.message.ips").replaceAll("%ip_int%", Integer.toString(players.size())));
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);


    }
    private void saveConfig(){
        File file = new File(getDataFolder(), "config.yml");
        try {
            Configuration cgf = cp.load(file);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMessageByConfig(String path){
        return ChatColor.translateAlternateColorCodes('&', config.getConfig().getString(path));
    }
    public void reloadConfig(){
        saveConfig();
        config.reloadConfig();
        players.clear();
        for(String str : config.getConfig().getStringList("config.players")){
            String hour = str.split(":")[1];
            String name = str.split("=")[1].toLowerCase().replaceAll(" at", "") + ":"+ hour;
            String ip = str.split(",")[0];

            players.put(name, ip);
        }
        getProxy().getConsole().sendMessage(ChatColor.GREEN + Integer.toString(players.size()) +" players added to the IPAuthenticator list.");

    }
    public static Main getInstance(){
        return instance;
    }

   @EventHandler
    public void postlogin(PostLoginEvent e){
       getProxy().getScheduler().runAsync(Main.getInstance(), new Runnable() {
           @Override
           public void run() {
               try {
                   String name = e.getPlayer().getName();
                   String ip = e.getPlayer().getAddress().getHostString();
                   if(players.containsKey(name.toLowerCase())){
                       if(!players.get(name.toLowerCase()).equalsIgnoreCase(ip)){
                           e.getPlayer().disconnect(ChatColor.translateAlternateColorCodes('&', config.getConfig().getString("config.message.kick")));
                           ProxyServer.getInstance().getConsole().sendMessage(getMessageByConfig("config.alert")
                                   .replaceAll("%ip%", ip)
                                   .replaceAll("%account%", name));
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                           LocalDateTime now = LocalDateTime.now();
                           data.add(ip + ", tried to join with the user=" + name + " at:" + now);

                           for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()){
                               if(pp.hasPermission(config.getConfig().getString("config.permission"))){
                                   pp.sendMessage(getMessageByConfig("config.message"
                                           .replaceAll("%ip%", ip)
                                           .replaceAll("%account%", name)));

                               }
                           }
                           if(logger){
                               File file = new File(getDataFolder(), "logger.yml");
                               try {
                                   Configuration cgf = cp.load(file);
                                   cgf.set("logger.try",data);
                                   ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(getDataFolder(), "logger.yml"));

                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }
                       }
                   }
               } catch(Exception ex) {
                   ex.printStackTrace();
               }
           }
       });





   }


}
