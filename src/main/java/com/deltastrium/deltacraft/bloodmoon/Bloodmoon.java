package com.deltastrium.deltacraft.bloodmoon;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Bloodmoon.MODID, version = Bloodmoon.VERSION)
public class Bloodmoon {
	public static final String	MODID	= "Bloodmoon";
	public static final String	VERSION	= "1.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CustomEventHandler handler = new CustomEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
	}

}
