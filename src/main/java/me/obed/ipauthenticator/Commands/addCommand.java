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

public class addCommand extends SubCommands{
    private ConfigurationProvider cp = ConfigurationProvider.getProvider(YamlConfiguration.class);

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length <=1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.info()));
            return;
        }
        File file = new File(plugin.getDataFolder(), "config.yml");
        try {
            plugin.players.remove(args[0]);
            plugin.players.put(args[0], args[1]);
            Configuration cgf = cp.load(file);
            ArrayList<String> data = new ArrayList<String>();
            for(String st : plugin.players.keySet()){
                data.add(st+"," + plugin.players.get(st));
            }
            cgf.set("config.players",data);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Main.getInstance().getMessageByConfig("config.message.added")
            .replaceAll("%player%", args[0]).replaceAll("%ip%" , args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String name() {
        return plugin.commandManager.add;
    }

    @Override
    public String info() {
        return "&a/ipauth <player> <ip>    &7Add a player and his ip to the list.";
    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
