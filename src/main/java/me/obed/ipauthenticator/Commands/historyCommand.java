package me.obed.ipauthenticator.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class historyCommand extends SubCommands{
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!plugin.logger){
            sender.sendMessage(plugin.getMessageByConfig("message.history.notenable"));
            return;
        }
        if(args.length <=0){
            sender.sendMessage(plugin.getMessageByConfig("message.history.arguments"));
            return;
        }
        if(!plugin.players.containsKey(args[0])){
            sender.sendMessage(plugin.getMessageByConfig("message.history.error"));
            return;
        }
        sender.sendMessage(plugin.getMessageByConfig("message.history.header"));

        for(String str : plugin.data.get(args[0])){
            sender.sendMessage(ChatColor.GREEN + str);
        }
        sender.sendMessage(plugin.getMessageByConfig("message.history.footer"));



    }

    @Override
    public String name() {
        return plugin.commandManager.history;
    }

    @Override
    public String info() {
        return ChatColor.translateAlternateColorCodes('&', "&a/ipauth history <player>     &7Show history of failed logins in the accounts.");
    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
