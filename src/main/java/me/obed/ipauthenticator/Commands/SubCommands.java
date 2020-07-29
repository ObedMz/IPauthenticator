package me.obed.ipauthenticator.Commands;


import me.obed.ipauthenticator.Main;
import net.md_5.bungee.api.CommandSender;

public abstract class SubCommands {
    protected Main plugin = Main.getInstance();

    public SubCommands(){

    }

    public abstract void execute(CommandSender sender, String[] args);
    public abstract String name();
    public abstract String info();
    public abstract String[] alias();
}
