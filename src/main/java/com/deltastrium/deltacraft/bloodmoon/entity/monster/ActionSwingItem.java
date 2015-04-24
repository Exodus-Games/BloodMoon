package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;

import com.anor.behaviortree.Action;
import com.anor.behaviortree.NodeStatus;

public class ActionSwingItem implements Action<EntityCreature> {

	public ActionSwingItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public NodeStatus perform(EntityCreature w) {
		if (w.getHeldItem() != null)
        {
            w.swingItem();
            return NodeStatus.SUCCESS;
        }
		return NodeStatus.FAILURE;
	}

}
