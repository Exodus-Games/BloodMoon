package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

import com.anor.behaviortree.Node;

public class BloodmoonAIBase extends EntityAIBase {

	private EntityCreature	mob;
	private Node<EntityCreature> behaviorTree;
	
	public BloodmoonAIBase(EntityCreature mob, Node<EntityCreature> root) {
		this.mob = mob;
		this.behaviorTree = root;
	}
	
	

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public boolean continueExecuting() {
		// TODO Auto-generated method stub
		return super.continueExecuting();
	}

	@Override
	public void startExecuting() {
		// TODO Auto-generated method stub
		super.startExecuting();
	}

	@Override
	public void updateTask() {
		super.updateTask();
		behaviorTree.process(mob);
	}

}
