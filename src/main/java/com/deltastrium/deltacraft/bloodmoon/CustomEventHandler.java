package com.deltastrium.deltacraft.bloodmoon;

import net.minecraft.entity.EntityCreature;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;

import com.anor.behaviortree.Composite;
import com.anor.behaviortree.InverterNode;
import com.anor.behaviortree.LeafNode;
import com.anor.behaviortree.Node;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.AttackPlayerAction;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.BloodmoonAIBase;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.EntityBloodWorm;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.FindPlayerAction;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.FindRandomPath;
import com.deltastrium.deltacraft.bloodmoon.entity.monster.MoveToPlayerAction;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

/**
 * Do not try playing with this mod yet you probably won't survive.
 * 
 * @author njd5475
 */
public class CustomEventHandler {

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

	}

	@SubscribeEvent
	public void init(LivingSpawnEvent.CheckSpawn event) {

	}

	@SubscribeEvent
	public void init(LivingUpdateEvent event) {
		if (!(event.entityLiving instanceof EntityBloodWorm) && event.entity instanceof EntityCreature) {
			EntityCreature creature = (EntityCreature) event.entity;
			if (creature.tasks.taskEntries.size() == 0 || !(creature.tasks.taskEntries.get(0) instanceof BloodmoonAIBase)) {
				creature.tasks.taskEntries.clear();
				creature.tasks.addTask(0, new BloodmoonAIBase(creature, getZombieTree()));
			}
		}
	}

	private static Node<EntityCreature> getZombieTree() {
		Composite<EntityCreature> root = new Composite.InOrderSequence<EntityCreature>();

		Composite<EntityCreature> attack = new Composite.InOrderSequence<EntityCreature>();

		attack.addChild(new LeafNode<EntityCreature>(new FindPlayerAction(10d)));
		attack.addChild(new LeafNode<EntityCreature>(new MoveToPlayerAction(1.0d)));
		attack.addChild(new LeafNode<EntityCreature>(new AttackPlayerAction()));

		root.addChild(new InverterNode<EntityCreature>(attack));

		root.addChild(new LeafNode<EntityCreature>(new FindRandomPath()));

		return root;
	}
}
