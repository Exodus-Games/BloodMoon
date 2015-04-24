package com.deltastrium.deltacraft.bloodmoon;

import com.deltastrium.deltacraft.bloodmoon.entity.monster.EntityBloodWorm;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Bloodmoon.MODID, version = Bloodmoon.VERSION)
public class Bloodmoon {
	public static final String	MODID	= "Bloodmoon";
	public static final String	VERSION	= "1.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CustomEventHandler handler = new CustomEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);

		int redColor = (255 << 16);
		int orangeColor = (255 << 16) + (200 << 8);

		// Register mob
		EntityRegistry.registerGlobalEntityID(EntityBloodWorm.class, "BloodWorm",
				EntityRegistry.findGlobalUniqueEntityId(), redColor, orangeColor);

		// Localize mob name
		LanguageRegistry.instance().addStringLocalization("entity.bloodworm.name", "en_US", "Blood Worm");

		// Add mob spawn
		for (BiomeGenBase base : BiomeGenBase.getBiomeGenArray()) {
			if (base != null) {
				try {
					EntityRegistry.addSpawn(EntityBloodWorm.class, 10, 2, 2, EnumCreatureType.monster, base);
				} catch (Exception e) {
					System.err.println("Problem generating blood worm spawner in biome " + base.biomeName);
				}
			}
		}
	}

}
