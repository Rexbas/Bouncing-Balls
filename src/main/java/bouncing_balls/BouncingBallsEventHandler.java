package bouncing_balls;

import bouncing_balls.api.capability.BounceCapabilityProvider;
import bouncing_balls.api.item.IBouncingBall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BouncingBalls.MODID)
public class BouncingBallsEventHandler {
	
	@SubscribeEvent
	public static void attachtCapability(AttachCapabilitiesEvent<Entity> event) {	
		if (event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(BouncingBalls.MODID, "capability.bounce"), new BounceCapabilityProvider());
		}
	}
	
	@SubscribeEvent
	public static void tick(PlayerTickEvent event) {
		event.player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
			if (event.phase == TickEvent.Phase.START) {
				cap.setStartTickOnGround(event.player.isOnGround());
			}
			else if (event.phase == TickEvent.Phase.END) {
				if (cap.getConsecutiveBounces() > 0 && event.player.isOnGround() && cap.getStartTickOnGround()) {
					cap.resetConsecutiveBounces();
					
					if (event.player.getMainHandItem().getItem() instanceof IBouncingBall) {
						((IBouncingBall) event.player.getMainHandItem().getItem()).onFall(event.player, event.player.getMainHandItem(), 0);
					}
					else if (event.player.getOffhandItem().getItem() instanceof IBouncingBall) {
						((IBouncingBall) event.player.getOffhandItem().getItem()).onFall(event.player, event.player.getOffhandItem(), 0);
					}
					event.player.hurtMarked = true;
				}
			}
		});
	}
	
	@SubscribeEvent
	public static void onCreativePlayerFall(PlayerFlyableFallEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.resetConsecutiveBounces();
			});
			
			if (player.getMainHandItem().getItem() instanceof IBouncingBall) {
				((IBouncingBall) player.getMainHandItem().getItem()).onFall(player, player.getMainHandItem(), event.getDistance());
			}
			else if (player.getOffhandItem().getItem() instanceof IBouncingBall) {
				((IBouncingBall) player.getOffhandItem().getItem()).onFall(player, player.getOffhandItem(), event.getDistance());
			}
			player.hurtMarked = true;
		}
	}
	
	@SubscribeEvent
	public static void onPlayerFall(LivingFallEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			player.getCapability(BounceCapabilityProvider.BOUNCE_CAPABILITY).ifPresent(cap -> {
				cap.resetConsecutiveBounces();
			});
			
			float multiplier = 1;
			if (player.getMainHandItem().getItem() instanceof IBouncingBall) {
				multiplier = ((IBouncingBall) player.getMainHandItem().getItem()).onFall(player, player.getMainHandItem(), event.getDistance());
			}
			else if (player.getOffhandItem().getItem() instanceof IBouncingBall) {
				multiplier = ((IBouncingBall) player.getOffhandItem().getItem()).onFall(player, player.getOffhandItem(), event.getDistance());
			}
			event.setDamageMultiplier(multiplier);
			player.hurtMarked = true;
		}
	}
}