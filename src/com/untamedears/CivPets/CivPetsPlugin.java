package com.untamedears.CivPets;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CivPetsPlugin extends JavaPlugin {
	protected static Material untameMaterial = Material.COOKIE;
	protected static float untameChance = 0.2f;
	
	protected EventListener petEventListener;
	
	protected Logger log;
	
	public void onEnable() {
		petEventListener = new EventListener(this);
		this.getServer().getPluginManager().registerEvents(petEventListener, this);
		
		log = Bukkit.getLogger();
	}
	
	public void onDisable() {
		
	}
	
	public Material getUntameMaterial() {
		return untameMaterial;
	}
	
	public float getUntameChance() {
		return untameChance;
	}
	
	public void log(String string) {
		log.info(string);
	}
}
