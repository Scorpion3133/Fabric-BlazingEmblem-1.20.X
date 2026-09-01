package net.scorpion.blazingemblem.item.custom;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import java.util.UUID;

import java.util.UUID;

public class EmblemRingItem extends TrinketItem {
    private final EntityAttribute attribute;
    private final double attributeAmount;
    private final EntityAttributeModifier.Operation operation;
    private final String modifierName;

    private final StatusEffect effect;
    private final int amplifier;

    public EmblemRingItem(Settings settings, EntityAttribute attribute, double attributeAmount, EntityAttributeModifier.Operation operation, String modifierName, StatusEffect effect, int amplifier) {
        super(settings);
        this.attribute = attribute;
        this.attributeAmount = attributeAmount;
        this.operation = operation;
        this.modifierName = modifierName;

        this.effect = effect;
        this.amplifier = amplifier;
    }

    public EmblemRingItem(Settings settings, EntityAttribute attribute, double amount,
                          EntityAttributeModifier.Operation operation, String modifierName) {
        this(settings, attribute, amount, operation, modifierName, null, 0); // effect fields null/0
    }

    public EmblemRingItem(Settings settings, StatusEffect effect, int amplifier) {
        this(settings, null, 0, null, null, effect, amplifier); // attribute fields null/0
    }

    // Attribute buffs
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(
            ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        if (this.attribute != null) {
            modifiers.put(this.attribute,
                    new EntityAttributeModifier(uuid, this.modifierName, this.attributeAmount, this.operation));
        }
        return modifiers;
    }

    // Status Effect buffs
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (this.effect != null && entity instanceof PlayerEntity player && !player.getWorld().isClient()) {
            StatusEffectInstance current = player.getStatusEffect(this.effect);
            // refresh when absent, weaker, OR running low — so it never counts to zero
            if (current == null
                    || current.getAmplifier() < this.amplifier
                    || current.getDuration() < 200) {
                player.addStatusEffect(new StatusEffectInstance(
                        this.effect, 400, this.amplifier, false, false, true)); // 200 ticks, showIcon = true
            }
        }
        super.tick(stack, slot, entity);
    }
}
