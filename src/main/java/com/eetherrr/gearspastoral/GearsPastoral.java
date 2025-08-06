package com.eetherrr.gearspastoral;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(GearsPastoral.MODID)
public class GearsPastoral {
	public static final String MODID = "gears_pastoral";
	public static final Logger LOGGER = LogUtils.getLogger();
	
	public GearsPastoral(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.addListener(this::commonSetup);
		
		NeoForge.EVENT_BUS.register(this);
		
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
		
	}
	
	private void commonSetup(FMLCommonSetupEvent event) {
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");
		
		if(Config.LOG_DIRT_BLOCK.getAsBoolean()) {
			LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
		}
		
		LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());
		
		Config.ITEM_STRINGS.get().forEach((item)->LOGGER.info("ITEM >> {}", item));
	}
	
}
