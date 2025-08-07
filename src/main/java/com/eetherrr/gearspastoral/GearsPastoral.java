package com.eetherrr.gearspastoral;

import com.eetherrr.gearspastoral.item.CGPItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod(GearsPastoral.MODID)
public class GearsPastoral {
	public static final String MODID = "gears_pastoral";
	//public static final Logger LOGGER = LogUtils.getLogger();
	
	public GearsPastoral(IEventBus modEventBus, ModContainer modContainer) {
		
		CGPItems.registry(modEventBus);
		
	}
	
	public static ResourceLocation id(@NotNull String path) {
		return ResourceLocation.fromNamespaceAndPath(GearsPastoral.MODID, path);
	}
}
