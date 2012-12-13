package com.untamedears.CivPets;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Skull;
import org.bukkit.metadata.FixedMetadataValue;

public class EventListener implements Listener {
	
	protected CivPetsPlugin plugin;
	protected static Random rand;

	public EventListener(CivPetsPlugin plugin) {
		this.plugin = plugin;
		rand = new Random();
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	protected void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		if (p != null && e != null) {
			if (e instanceof Wolf || e instanceof Ocelot) {
				ItemStack i = p.getItemInHand();
				if (i != null) {
					if (i.getType() == plugin.getUntameMaterial()) {
						event.setCancelled(true);
						if (attemptUntame(e, p.getName())) {
							i.setAmount(i.getAmount()-1);
							p.setItemInHand(i);
						}
					}
				}
			}
		}
	}
	
	protected boolean untameSucceeds() {
		if (rand.nextFloat() > plugin.getUntameChance()) {
			return false;
		}
		return true;
	}
	
	protected boolean attemptUntame(Entity e, String name) {
		if (e instanceof Wolf) {
			Wolf w = (Wolf) e;
			if (w.isTamed()) {
				if (w.getOwner().getName() == name && untameSucceeds()) {
					w.setTamed(false);
					w.setSitting(false);
				}
				return true;
			}
		} else if (e instanceof Ocelot) {
			Ocelot o = (Ocelot) e;
			if (o.isTamed()) {
				if (o.getOwner().getName() == name && untameSucceeds()) {
					o.setTamed(false);
					o.setSitting(false);
				}
				return true;
			}
		}
		return false;
	}
}
