package net.scorpion.blazingemblem.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.scorpion.blazingemblem.item.ModArmorMaterial;

import java.util.Map;
import java.util.Set;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(ModArmorMaterial.ACADEMY, new StatusEffectInstance(StatusEffects.HASTE, 400, 1,
                            false, false, true)).build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                evaluateArmorEffects(player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance effect) {
        if (!hasCorrectArmorOn(material, player)) return;

        StatusEffectInstance current = player.getStatusEffect(effect.getEffectType());
        if (current == null || current.getDuration() < 10) {
            player.addStatusEffect(new StatusEffectInstance(effect));
        }
    }

    private static final Set<ArmorMaterial> THREE_PIECE = Set.of(ModArmorMaterial.ACADEMY);

    public static boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        boolean requiresHelmet = !THREE_PIECE.contains(material);

        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        // core 3 must always match
        if (!matches(boots, material) || !matches(leggings, material) || !matches(chestplate, material)) {
            return false;
        }
        // helmet only required for 4-piece sets
        if (requiresHelmet && !matches(helmet, material)) {
            return false;
        }
        return true;
    }

    private static boolean matches(ItemStack stack, ArmorMaterial material) {
        return stack.getItem() instanceof ArmorItem armor && armor.getMaterial() == material;
    }
}
