package me.obed.ipauthenticator.Commands;

import me.obed.ipauthenticator.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class removeCommand extends SubCommands {
    private ConfigurationProvider cp = ConfigurationProvider.getProvider(YamlConfiguration.class);

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length <=0){
            sender.sendMessage(plugin.getMessageByConfig("message.remove.arguments"));
            return;
        }
        if(!plugin.players.containsKey(args[0])){
            sender.sendMessage(plugin.getMessageByConfig("message.remove.error"));
            return;
        }
        plugin.players.remove(args[0], plugin.players.get(args[0]));
        File file = new File(plugin.getDataFolder(), "config.yml");
        try {
            Configuration cgf = cp.load(file);
            ArrayList<String> data = new ArrayList<String>();
            for(String st : plugin.players.keySet()){
                data.add(st+"," + plugin.players.get(st));
            }
            cgf.set("config.players",data);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.remove.success")
            .replaceAll("%player%" , args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String name() {
        return plugin.commandManager.remove;
    }

    @Override
    public String info() {
        return "&a/ipauth remove <player>   &7Remove a player of the list.";
    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
