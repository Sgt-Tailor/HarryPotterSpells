package com.lavacraftserver.HarryPotterSpells;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PM extends JavaPlugin {
	private Logger log = Bukkit.getLogger();
	
	/**
	 * Logs a message to the console
	 * @param message the message to be logged
	 * @param level the level the log should be
	 * @deprecated Use {@link PM.log(Level level, String... message)}
	 */
	@Deprecated
	public void log(String message, Level level) {
		log.log(level, "[HarryPotterSpells] " + message);
	}
	
	/**
	 * Logs any amount of messages to the console
	 * @param level the level to log the message at
	 * @param message the message(s) to log
	 */
	public void log(Level level, String... message) {
		for(String str : message)
			log.log(level, "[HarryPotterSpells] " + str);
	}
	
	/**
	 * Sends a player any amount of messages with an informative aim
	 * @param player the player to send the message to
	 * @param message the message(s) to be sent
	 */
	public void tell(Player player, String... message) {
		for(String str : message)
			player.sendMessage("[" + ChatColor.GOLD + "HarryPotterSpells" + ChatColor.WHITE + "] " + ChatColor.YELLOW + str);
	}
	
	/**
	 * Sends a player any amount of messages with a warning aim
	 * @param player the player to send the message to
	 * @param message the message(s) to be sent
	 */
	public void warn(Player player, String... message) {
		for(String str : message)
			player.sendMessage("[" + ChatColor.GOLD + "HarryPotterSpells" + ChatColor.WHITE + "] " + ChatColor.RED + str);
	}
	
	/**
	 * Sends a player a new spell notification
	 * @param player the player to send the notification to
	 * @param spell the name of the spell the user has selected
	 */
	public void newSpell(Player player, String spell) {
		player.sendMessage(ChatColor.GOLD + "You have selected spell: " + ChatColor.AQUA + spell);
	}
	
	/**
	 * Notifies a player when a spell has been casting if enabled in the config
	 * @param player the player to notify
	 * @param spell the name of the spell they have cast
	 */
	public void notify(Player player, String spell) {
		if (HPS.Plugin.getConfig().getBoolean("notify-on-spell-use", true))
			player.sendMessage(ChatColor.GOLD + "You have cast " + ChatColor.AQUA + spell + "!");
	}
	
	/**
	 * Makes a player shout the name of a spell if enabled in the config
	 * @param player the player to shout
	 * @param spell the spell they have cast
	 */
	public void shout(Player player, String spell) {
		if (HPS.Plugin.getConfig().getBoolean("shout-on-spell-use", false))
			player.chat(spell + "!");
	}
	
	/**
	 * Logs a debug message to the console if enabled in the config
	 * @param message the message(s) to log
	 */
	public void debug(String... message) {
		if(HPS.Plugin.getConfig().getBoolean("DebugMode", false))
			for(String str : message)
				log(Level.INFO, "[HPS - Debug] " + str);
	}
	
	/**
	 * Checks if a player has a permission
	 * @deprecated This is exactly the same as the Bukkit API call. </br>
	 *             Vault also uses the Bukkit API call so this method is deprecated.
	 * @param permission the permission to check
	 * @param p the player to check
	 * @return {@code true} if the player has the specified permission
	 */
	@Deprecated
	public boolean hasPermission(String permission, Player p) {
        return p.hasPermission(permission);
	}
	
	/**
	 * Hacks a file to the classpath
	 * @param file the file to hack
	 * @return {@code true} if the file was added to the classpath
	 */
	public boolean hackFile(File file) {
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { file.toURI().toURL() });
        } catch (Throwable t) {
            return false;
        }
        return true;
	}

}
