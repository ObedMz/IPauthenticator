package me.obed.ipauthenticator.Commands;

import me.obed.ipauthenticator.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandManager extends Command {
    private static HashMap<String, SubCommands> commands = new HashMap<String, SubCommands>();
    private Main plugin = Main.getInstance();

    public CommandManager(){
        super("ipauth");
    }

    //my subcommands

    public String reload = "reload";
    public String add = "add";
    public String remove = "remove";
    public String help = "help";
    public String list = "list";
    public String ban = "ban";
    public String unban = "unban";
    public String banlist = "banlist";
    public String history = "history";
    public String purge = "purge";
    public String addip = "addip";
    public String removeip = "removeip";
    public String listip = "listip";

    public void setup(){
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
        this.getCommands().put(reload, new ReloadCommand());
        this.getCommands().put(add, new addCommand());
        this.getCommands().put(remove, new removeCommand());
        this.getCommands().put(help, new helCommand());
        this.getCommands().put(list, new listCommand());
        this.getCommands().put(ban , new banCommand());
        this.getCommands().put(unban, new unbanCommand());
        this.getCommands().put(banlist, new banListCommand());
        this.getCommands().put(history, new historyCommand());
        this.getCommands().put(purge, new purgeCommand());
        this.getCommands().put(addip, new addIPCommand());
        this.getCommands().put(removeip, new removeIPCommand());
        this.getCommands().put(listip, new listIPCommand());
    }
    public HashMap<String,SubCommands> getCommands(){
        return commands;
    }

    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            if(plugin.onlyconsole)
                sender.sendMessage(Main.getInstance().getMessageByConfig("config.message.noconsole"));
                return;
        }

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "Not enough arguments, type /ipauth help");
            return;
        }
        SubCommands sbcomand = this.getCommands().get(args[0].toLowerCase());
        if(sbcomand == null){
            sender.sendMessage(ChatColor.RED + "This command doesnt exist.");
            return;
        }

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(Arrays.asList(args));
        arrayList.remove(0);
        String[] str = new String[arrayList.size()];
        for(int x=0; x<arrayList.size();x++){
            str[x] = arrayList.get(x);
        }


        try{
            sbcomand.execute(sender, str);
        }catch (Exception e){
            e.printStackTrace();

        }
    }


}
