package com.eetherrr.gearspastoral.block;

import com.eetherrr.gearspastoral.GearsPastoral;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CGPBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GearsPastoral.MODID);
	
	
	public static void registry(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
