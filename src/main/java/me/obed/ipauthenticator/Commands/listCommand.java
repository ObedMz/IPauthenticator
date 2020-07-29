package me.obed.ipauthenticator.Commands;

import me.obed.ipauthenticator.Main;
import net.md_5.bungee.api.CommandSender;

public class listCommand extends SubCommands {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("ipauth.list")){
            sender.sendMessage(plugin.getMessageByConfig("message.nopermission"));
            return;
        }
        sender.sendMessage(Main.getInstance().getMessageByConfig("message.list.header"));
        for(String str : Main.getInstance().players.keySet()){
            sender.sendMessage(Main.getInstance().getMessageByConfig("message.list.rows")
            .replaceAll("%player%", str).replaceAll("%ip%", Main.getInstance().players.get(str)));
        }

        sender.sendMessage(Main.getInstance().getMessageByConfig("message.list.footer"));

    }

    @Override
    public String name() {
        return plugin.commandManager.list;
    }

    @Override
    public String info() {
        return "&a/ipauth list        &7Show the list of accounts.";
    }

    @Override
    public String[] alias() {
        return new String[0];
    }
}
