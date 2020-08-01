package me.obed.ipauthenticator.Commands;

import me.obed.ipauthenticator.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class addIPCommand extends SubCommands{
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("ipauth.ipadd")){
            sender.sendMessage(plugin.getMessageByConfig("message.nopermission"));
            return;
        }
        if(args.length <=1){
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.addip.arguments"));
            return;
        }
        if(!plugin.players.contains(args[0].toLowerCase())){
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.addip.error"));
            return;
        }
        List<String> data = config.getConfig().getStringList("config.accounts-ip." + args[0].toLowerCase());
        if(data.contains(args[1])){
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.addip.already"));
            return;
        }
        data.add(args[1]);
        File file = new File(plugin.getDataFolder(), "config.yml");
        try {
            Configuration cgf = cp.load(file);
            cgf.set("config.accounts-ip." + args[0], data);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cgf, new File(plugin.getDataFolder(), "config.yml"));
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.addip.success")
                    .replaceAll("%player%", args[0]).replaceAll("%ip%" , args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public String name() {
        return plugin.commandManager.addip;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/ipauth addip <player> <ip>  &7Add other IP to a player.");

    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
