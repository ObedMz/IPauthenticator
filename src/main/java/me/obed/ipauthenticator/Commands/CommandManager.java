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

    public void setup(){
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
        this.getCommands().put(reload, new ReloadCommand());
        this.getCommands().put(add, new addCommand());
        this.getCommands().put(remove, new removeCommand());
        this.getCommands().put(help, new helCommand());
        this.getCommands().put(list, new listCommand());
    }
    public HashMap<String,SubCommands> getCommands(){
        return commands;
    }

    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            sender.sendMessage(Main.getInstance().getMessageByConfig("config.message.noconsole"));
            return;
        }
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "Not enough arguments, type /ipauth help");
            return;
        }
        SubCommands sbcomand = null;
        switch (args[0].toLowerCase()){
            case "help":
                sbcomand = new helCommand();
                break;
            case "reload":
                sbcomand = new ReloadCommand();
                break;
            case "add":
                sbcomand = new addCommand();
                break;
            case "remove":
                sbcomand = new removeCommand();
                break;
            case "list":
                sbcomand = new listCommand();
                break;
            default:
                sender.sendMessage(ChatColor.RED + "This command doesnt exist.");
                break;
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
