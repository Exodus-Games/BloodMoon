package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

import com.anor.behaviortree.Action;
import com.anor.behaviortree.NodeStatus;

public class FindRandomPath implements Action<EntityCreature> {

	@Override
	public NodeStatus perform(EntityCreature w) {
		if (w.getNavigator().noPath()) {
			Vec3 vec3 = RandomPositionGenerator.findRandomTarget(w, 10, 7);
			if (vec3 != null && w.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, 1.0d)) {
				System.out.println("Wandering " + w.getClass().getSimpleName());
				return NodeStatus.SUCCESS;
			}else{
				return NodeStatus.FAILURE;
			}
		}else{
			return NodeStatus.SUCCESS;
		}
	}

}
