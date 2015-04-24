package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import com.anor.behaviortree.Condition;

public class ConditionTargetedPlayer implements Condition<EntityBloodmoonBase> {

	@Override
	public boolean pass(EntityBloodmoonBase w) {
		return w.getEntityToAttack() != null;
	}

}
