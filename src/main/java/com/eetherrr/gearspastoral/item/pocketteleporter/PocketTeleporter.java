package com.eetherrr.gearspastoral.item.pocketteleporter;

import com.eetherrr.gearspastoral.GearsPastoral;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PocketTeleporter extends Item {
	private static final Map<UUID, TeleportData> PLAYER_POSITION = new ConcurrentHashMap<>();
	private static final int COOLDOWN_TICKS = 20 * 5;
	private static final ResourceKey<Level> POCKET_SPACE = ResourceKey.create(Registries.DIMENSION, GearsPastoral.id("pocket_space"));
	
	public PocketTeleporter(Properties properties) {
		super(properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
		ItemStack itemStack = player.getItemInHand(usedHand);
		
		if(!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
			player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
			ServerLevel pocketLevel = player.getServer().getLevel(POCKET_SPACE);
			if(level.dimension().equals(POCKET_SPACE)) {
				returnFromPocket(serverPlayer);
			}else {
				savePositionAndTeleport(serverPlayer, pocketLevel);
			}
		}
		return InteractionResultHolder.consume(itemStack);
	}
	
	private void savePositionAndTeleport(ServerPlayer serverPlayer, ServerLevel pocketLevel) {
		TeleportData data = new TeleportData(
				serverPlayer
						.level()
						.dimension(), serverPlayer.position(), serverPlayer.getYRot(), serverPlayer.getXRot()
		);
		
		PLAYER_POSITION.put(serverPlayer.getUUID(), data);
		
		serverPlayer.changeDimension(new DimensionTransition(pocketLevel, new Vec3(-7, -11, 6), Vec3.ZERO, 180f, serverPlayer.getXRot(), DimensionTransition.DO_NOTHING));
		
		serverPlayer.displayClientMessage(Component.translatable("msg.pocket_dimension.enter").withStyle(ChatFormatting.GREEN), true);
	}
	
	private void returnFromPocket(ServerPlayer serverPlayer) {
		UUID uuid = serverPlayer.getUUID();
		if(!PLAYER_POSITION.containsKey(uuid)) {
			serverPlayer.displayClientMessage(Component.translatable("msg.pocket_dimension.no_position").withStyle(ChatFormatting.RED), true);
			return;
		}
		
		TeleportData data = PLAYER_POSITION.get(uuid);
		PLAYER_POSITION.remove(uuid);
		
		ServerLevel targetLevel = serverPlayer.getServer().getLevel(data.dimension());
		if(targetLevel == null) {
			targetLevel = serverPlayer.getServer().overworld();
		}
		
		serverPlayer.teleportTo(targetLevel, data.position().x, data.position().y, data.position().z, data.yRot(), data.xRot());
		
		serverPlayer.displayClientMessage(Component.translatable("msg.pocket_dimension.exit").withStyle(ChatFormatting.GREEN), true);
	}
	
	private record TeleportData(ResourceKey<Level> dimension, Vec3 position, float yRot, float xRot) {}
	
}
