package com.eetherrr.gearspastoral.item;

import com.eetherrr.gearspastoral.GearsPastoral;
import com.eetherrr.gearspastoral.item.pocketteleporter.PocketTeleporter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CGPItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GearsPastoral.MODID);
	
	public static final DeferredItem<Item> POCKET_TELEPORTER = ITEMS.register("pocket_teleporter", ()->new PocketTeleporter(new Item.Properties().rarity(Rarity.EPIC)));
	
	public static void registry(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
