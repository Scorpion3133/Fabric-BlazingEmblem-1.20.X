package net.scorpion.blazingemblem.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
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
import java.util.UUID;

public class ModArmorItem extends ArmorItem {

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }


    private static final UUID ACADEMY_HEALTH_UUID = UUID.fromString("6e84a3b2-cd49-46d1-a299-9fcfe8af4d71");
    private static final UUID MYRMIDON_HEALTH_UUID = UUID.fromString("c506e149-fd81-461f-b1b8-d0506a0874fc");
    private static final UUID SOLDIER_HEALTH_UUID = UUID.fromString("61d1829d-60d2-4b66-9944-0bdbd7ed5764");
    private static final UUID FIGHTER_HEALTH_UUID = UUID.fromString("6ce235c8-da99-492c-b774-461d960ab096");
    private static final UUID HUNTER_HEALTH_UUID = UUID.fromString("06ae5893-1d17-4f3e-8ecf-4caaf3fa4b1c");
    private static final UUID MONK_HEALTH_UUID = UUID.fromString("d6adc26c-98a4-439c-ae97-7dc299fc6c06");


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

        applyHealthBonus(player, ModArmorMaterial.ACADEMY, ACADEMY_HEALTH_UUID, 2.0);
        applyHealthBonus(player, ModArmorMaterial.MYRMIDON, MYRMIDON_HEALTH_UUID, 4.0);
        applyHealthBonus(player, ModArmorMaterial.SOLDIER, SOLDIER_HEALTH_UUID, 4.0);
        applyHealthBonus(player, ModArmorMaterial.FIGHTER, FIGHTER_HEALTH_UUID, 4.0);
        applyHealthBonus(player, ModArmorMaterial.HUNTER, HUNTER_HEALTH_UUID, 2.0);
        applyHealthBonus(player, ModArmorMaterial.MONK, MONK_HEALTH_UUID, 2.0);
    }

    private void applyHealthBonus(PlayerEntity player, ArmorMaterial material, UUID uuid, double hearts) {
        EntityAttributeInstance maxHealth =
                player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth == null) return;

        boolean wearing = hasCorrectArmorOn(material, player);

        if (wearing) {
            if (maxHealth.getModifier(uuid) == null) {
                maxHealth.addTemporaryModifier(new EntityAttributeModifier(
                        uuid, "Set health bonus", hearts, EntityAttributeModifier.Operation.ADDITION));
            }
        } else {
            if (maxHealth.getModifier(uuid) != null) {
                maxHealth.removeModifier(uuid);
                if (player.getHealth() > player.getMaxHealth()) {
                    player.setHealth(player.getMaxHealth());
                }
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
