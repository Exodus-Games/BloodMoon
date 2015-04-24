package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBloodmoonBase extends EntityZombie {

	private Vec3 moveTo;
	
	public EntityBloodmoonBase(World world) {
		super(world);
	}

	public void moveTo(Vec3 moveTo) {
		this.moveTo = moveTo;
	}
	
	public Vec3 getMoveTo() {
		return this.moveTo;
	}
}
