package net.scorpion.blazingemblem;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.scorpion.blazingemblem.block.ModBlocks;
import net.scorpion.blazingemblem.item.ModArmorMaterial;
import net.scorpion.blazingemblem.item.ModItemGroups;
import net.scorpion.blazingemblem.item.ModItems;
import net.scorpion.blazingemblem.item.custom.FalchionItem;
import net.scorpion.blazingemblem.item.custom.ModArmorItem;
import net.scorpion.blazingemblem.item.custom.ParallelFalchionItem;
import net.scorpion.blazingemblem.util.ModTags;
import net.scorpion.blazingemblem.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class BlazingEmblem implements ModInitializer {
	public static final String MOD_ID = "blazingemblem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		GeckoLib.initialize();

		ModWorldGeneration.generateModWorldGen();

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (!world.isClient()
					&& (player.getStackInHand(hand).getItem() instanceof FalchionItem
					|| player.getStackInHand(hand).getItem() instanceof ParallelFalchionItem)) {
				if (entity instanceof EnderDragonPart part) {
					part.owner.timeUntilRegen = 0;
					part.owner.damage(world.getDamageSources().mobAttack(player), 6.0f);
				}
			}

			if (!world.isClient() && entity instanceof LivingEntity target) {
				ItemStack weapon = player.getStackInHand(hand);

				if (weapon.isIn(ModTags.Items.SWORDS)
						&& ModArmorItem.hasCorrectArmorOn(ModArmorMaterial.MYRMIDON, player)) {
					int oldRegen = target.timeUntilRegen;
					target.timeUntilRegen = 0;
					target.damage(world.getDamageSources().mobAttack(player), 2.0f);
					target.timeUntilRegen = oldRegen;
				}
				if (weapon.isIn(ModTags.Items.SPEARS)
						&& ModArmorItem.hasCorrectArmorOn(ModArmorMaterial.SOLDIER, player)) {
					int oldRegen = target.timeUntilRegen;
					target.timeUntilRegen = 0;
					target.damage(world.getDamageSources().mobAttack(player), 2.0f);
					target.timeUntilRegen = oldRegen;
				}
				if (weapon.isIn(ModTags.Items.AXES)
						&& ModArmorItem.hasCorrectArmorOn(ModArmorMaterial.FIGHTER, player)) {
					int oldRegen = target.timeUntilRegen;
					target.timeUntilRegen = 0;
					target.damage(world.getDamageSources().mobAttack(player), 2.0f);
					target.timeUntilRegen = oldRegen;
				}
				// Add Monk when magic is in

			}

			return ActionResult.PASS;
		});

	}
}