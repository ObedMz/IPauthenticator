# IPauthenticator

This is a BungeeCord plugin created to protect player accounts using their IP Address.

## Short Wiki

### How to Install
You can compile it from the command line:
```bash
 git clone https://github.com/ObedMz/IPauthenticator.git
 cd IPauthenticator
 mvn clean install
 ```
 After this, you just drop the jar file into the /plugins folder of your BungeeCord server
or get the .jar directly from [descargar](https://github.com/ObedMz/IPauthenticator/releases/download/1.3-SNAPSHOT/IpAuthentication-1.3-SNAPSHOT.jar)

## Commands
* ipauth help
     > Show the list of available commands
* /ipauth add (player) (ip)
     > Add a player and his IP to the list of protected accounts
* /ipauth remove (player)
     > Remove a player and his IP of the list of protected accounts
* /ipauth list
     > List all players who are in the list of protected accounts
* /ipauth addip (player) (ip)
     > Add other IP to an account.
* /ipauth removeip (player)(ip)
     > Remove a certain IP of an account.
* /ipauth listip (player)
     > Show the list of IP.
* /ipauth history (player)
     > Show a list of failed attempts in the account.
* /ipauth purge (player)
     > Clean the list of failed attempts of an account.
* /ipauth ban (ip)
     > permanently ban an IP 
* /ipauth unban (ip)
     > Unban an IP 
* /ipauth banlist
     > Show the list of banned IP's
* ipauth reload
      Reload the config File

## Permissions
  * ipauth.reload
  * ipauth.add
  * ipauth.remove
  * ipauth.ban
  * ipauth.unban
  * ipauth.banlist
  * ipauth.list
  * ipauth.history
  * ipauth.purge
  * ipauth.ipadd
  * ipauth.ipremove
  * ipauth.iplist
  
  
  
  ### Wiki
  visit the wiki of this plugin to get more information [Wiki](https://github.com/ObedMz/IPauthenticator/wiki)
  
  
