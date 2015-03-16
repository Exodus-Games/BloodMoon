package com.deltastrium.deltacraft.bloodmoon;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * Do not try playing with this mod yet you probably won't survive.
 * 
 * @author njd5475
 */
public class CustomEventHandler {

	private Set<EntityMob>	tracker		= new HashSet<EntityMob>();

	@SubscribeEvent
	public void init(WorldEvent.Load event) {
		if (event.world != null) {
			final long time = event.world.getWorldTime();
			System.out.println("[WORLDEVENT] CURRENT TIME >> " + time + " >> " + event.world.getTotalWorldTime());
		} else {
			System.out.println("[WORLDEVENT] THE WORLD HAS NOT LOADED YET");
		}
	}

	@SubscribeEvent
	public void init(ServerTickEvent event) {
		// collection for removal
		Set<EntityMob> dead = new HashSet<EntityMob>();
		
		// all mobs we're tracking shouldn't take long
		for (EntityMob mob : tracker) {
			if (mob.isDead) {
				dead.add(mob);
			}

			// costly method inside so we'll only do it if we need to
			if (mob.getAttackTarget() == null) {
				// find the closest player within a good amount of range
				EntityPlayer closestPlayerToEntity = mob.worldObj.getClosestPlayerToEntity(mob, 1000d);
				
				// since the target has not been set we'll set it now, mob will always go after player.
				mob.setTarget(closestPlayerToEntity);
				mob.setAttackTarget(closestPlayerToEntity);
			}
		}
		
		tracker.removeAll(dead);
	}

	@SubscribeEvent
	public void init(LivingSpawnEvent event) {
		if (event.entityLiving instanceof EntityMob) {
			EntityPlayer closestPlayerToEntity = event.world.getClosestPlayerToEntity(event.entityLiving, 10000d);
			if (closestPlayerToEntity != null) {
				// this won't last so we'll revert it later in ticks
				EntityMob mob = (EntityMob) event.entityLiving;
				mob.setTarget(closestPlayerToEntity);
				mob.setAttackTarget(closestPlayerToEntity);
				tracker.add(mob);
			}
		}
	}
}
