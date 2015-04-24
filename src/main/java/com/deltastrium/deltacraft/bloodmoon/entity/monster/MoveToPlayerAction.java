package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.PathNavigate;

import com.anor.behaviortree.Action;
import com.anor.behaviortree.NodeStatus;

public class MoveToPlayerAction implements Action<EntityCreature> {

	private double	speed;

	public MoveToPlayerAction(double speed) {
		this.speed = speed;
	}

	@Override
	public NodeStatus perform(EntityCreature w) {
		PathNavigate navigator = w.getNavigator();
		EntityLivingBase attackTarget = w.getAttackTarget();
		if (navigator != null && navigator.tryMoveToEntityLiving(attackTarget, speed)) {
			double d0 = w.getDistanceSq(attackTarget.posX, attackTarget.boundingBox.minY, attackTarget.posZ);
			double d1 = (double) (w.width * 2.0F * w.width * 2.0F + attackTarget.width);
			return d0 <= d1 ? NodeStatus.SUCCESS : NodeStatus.RUNNING;
		} else {
			return NodeStatus.FAILURE;
		}
	}

}
