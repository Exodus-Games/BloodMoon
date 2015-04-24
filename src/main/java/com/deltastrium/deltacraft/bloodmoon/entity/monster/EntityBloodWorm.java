package com.deltastrium.deltacraft.bloodmoon.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

import com.anor.behaviortree.Composite;
import com.anor.behaviortree.InverterNode;
import com.anor.behaviortree.LeafNode;
import com.anor.behaviortree.Node;

public class EntityBloodWorm extends EntityZombie {

	private Node<EntityCreature>	behaviorTree;

	public EntityBloodWorm(World world) {
		super(world);
		this.tasks.taskEntries.clear();
		Composite<EntityCreature> root = new Composite.InOrderSequence<EntityCreature>();
		
		Composite<EntityCreature> attack = new Composite.InOrderSequence<EntityCreature>();
		
		attack.addChild(new LeafNode<EntityCreature>(new FindPlayerAction(2d)));
		attack.addChild(new LeafNode<EntityCreature>(new MoveToPlayerAction(1.0d)));
		attack.addChild(new LeafNode<EntityCreature>(new AttackPlayerAction()));
		
		root.addChild(new InverterNode<EntityCreature>(attack));
		
		Composite<EntityCreature>  wander = new Composite.InOrderSequence<EntityCreature>();
		
		wander.addChild(new LeafNode<EntityCreature>(new FindRandomPath()));
		
		root.addChild(wander);
		
		this.behaviorTree = root;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.behaviorTree.process(this);
	}
	
	

}
