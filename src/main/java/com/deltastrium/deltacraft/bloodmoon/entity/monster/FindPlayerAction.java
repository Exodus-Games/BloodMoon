package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;

import com.anor.behaviortree.Action;
import com.anor.behaviortree.NodeStatus;

public class FindPlayerAction implements Action<EntityCreature> {

	private double	distance;
	private static final int COUNTER = 20;
	private int count = COUNTER;
	
	public FindPlayerAction(double distance) {
		this.distance = distance;
	}

	@Override
	public NodeStatus perform(EntityCreature w) {
		--count;
		if (w.getAttackTarget() == null && count <= 0) {
			EntityPlayer closestPlayerToEntity = w.worldObj.getClosestPlayerToEntity(w, distance);
			if (closestPlayerToEntity != null) {
				System.out.println("Player to attack found");
				w.setAttackTarget(closestPlayerToEntity);
			}
			count = COUNTER;
		}
		return w.getAttackTarget() == null ? NodeStatus.FAILURE : NodeStatus.SUCCESS;
	}

}
