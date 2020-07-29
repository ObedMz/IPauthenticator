package me.obed.ipauthenticator.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class purgeCommand extends SubCommands{
    private ConfigurationProvider cp = ConfigurationProvider.getProvider(YamlConfiguration.class);

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!plugin.logger){
            sender.sendMessage(plugin.getMessageByConfig("message.history.notenable"));
            return;
        }

        if(args.length <=0){
            sender.sendMessage(plugin.getMessageByConfig("message.purge.arguments"));
            return;
        }
        if(!plugin.data.containsKey(args[0])){
            sender.sendMessage(plugin.getMessageByConfig("message.purge.error"));
            return;
        }
        plugin.data.get(args[0]).clear();
        plugin.data.remove(args[0], new ArrayList<String>());
        File file = new File(plugin.getDataFolder(), "logger.yml");
        try {
            Configuration cgf = cp.load(file);
            for(String str : plugin.data.keySet()){
                cgf.set("logger.try." + str, plugin.data.get(str));
            }
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(plugin.getDataFolder(), "logger.yml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String name() {
        return plugin.commandManager.purge;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/ipauth purge <player>   &7Purge all history of failed IP's");
    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
