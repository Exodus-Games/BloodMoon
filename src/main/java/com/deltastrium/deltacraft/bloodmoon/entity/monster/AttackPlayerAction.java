package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;

import com.anor.behaviortree.Action;
import com.anor.behaviortree.NodeStatus;

public class AttackPlayerAction implements Action<EntityCreature> {

	@Override
	public NodeStatus perform(EntityCreature w) {
        if (w.getHeldItem() != null)
        {
            w.swingItem();
        }
        System.out.println("Creature: " + w.getClass().getSimpleName() + " attacking player");
        return w.attackEntityAsMob(w.getAttackTarget()) ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
	}

}
