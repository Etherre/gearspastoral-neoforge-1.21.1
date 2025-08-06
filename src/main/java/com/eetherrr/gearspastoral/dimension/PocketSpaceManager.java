package com.eetherrr.gearspastoral.dimension;

public class PocketSpace {

}
/*
* @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
        if (entity instanceof ServerPlayer serverPlayer) {
            PortalData portalData = new PortalData();
            portalData.setPortalDuration(20 * 60);
            portalData.firstPortal(serverPlayer.getUUID(), PortalPos.of(serverPlayer.level.dimension(), serverPlayer.position(), serverPlayer.getYRot()));

            PocketDimensionManager.INSTANCE.maybeGeneratePocketRoom(serverPlayer);
            BlockPos portalPos = PocketDimensionManager.INSTANCE.findPortalForStructure(serverPlayer.serverLevel(), PocketDimensionManager.INSTANCE.structurePosForPlayer(serverPlayer));
            ServerLevel pocketLevel = serverPlayer.getServer().getLevel(PocketDimensionManager.POCKET_DIMENSION);
            var portal = pocketLevel.getBlockEntity(portalPos);
            if (portal instanceof PortalFrameBlockEntity portalFrameBlockEntity) {
                Vec3 particlePos = serverPlayer.getBoundingBox().getCenter();
                MagicManager.spawnParticles(level, ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 100, 0.1, 0.2, 0.1, 0.1, false);

                var uuid = portalFrameBlockEntity.getUUID();
                portalData.secondPortal(uuid, PortalPos.of(PocketDimensionManager.POCKET_DIMENSION, portalPos.getBottomCenter(), 180));
                PortalManager.INSTANCE.addPortalData(uuid, portalData);
                portalFrameBlockEntity.setChanged();
                PortalManager.INSTANCE.addDirectPortalCooldown(serverPlayer, uuid); // Manually add cooldown as if the player used the portal to help prevent immediately teleporting back
                Scroll.attemptRemoveScrollAfterCast(serverPlayer); // Manually call this because this serverplayer will be removed from the level after the spellcast
                serverPlayer.changeDimension(new DimensionTransition(pocketLevel, portalData.globalPos2.pos(), Vec3.ZERO, portalData.globalPos2.rotation(), serverPlayer.getXRot(), DimensionTransition.DO_NOTHING));

            }
        }
    }
* */
