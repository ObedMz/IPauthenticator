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
or get the .jar directly from [descargar](https://github.com/ObedMz/IPauthenticator/releases/download/1.1-SNAPSHOT/IpAuthentication-1.1-SNAPSHOT.jar)

## Commands
* ipauth help
     > Muestra la lista de comandos disponibles
* /ipauth add (player) (ip)
     > Agrega una cuenta y a su ip a la lista de cuentas protegidas.
* /ipauth remove (player)
     > Elimina una cuenta de la lista.
 * /ipauth list
     > Muestra todas las cuentas protegidas.
* /ipauth addip (player) (ip)
     > Add other IP to an account.
* /ipauth removeip (player)(ip)
     > Remove a certain IP of an account.
* /ipauth listip (player)
     > Show the list of IP.
* /ipauth history (player)
     > Muestra el historial de intentos fallidos de la cuenta.
* /ipauth purge (player)
     > Limpia el historial de la cuenta.
* /ipauth ban (ip)
     > Banea permanentemente una ip.
* /ipauth unban (ip)
     > Unbanea una IP 
* /ipauth banlist
     > Muestra la lista de ips baneadas
* ipauth reload
      Recarga la configuración del plugin.

## Permisos
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
  
  
