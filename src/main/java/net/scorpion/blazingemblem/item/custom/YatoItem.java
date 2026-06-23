package net.scorpion.blazingemblem.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class YatoItem extends SwordItem {
    public YatoItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            NbtCompound nbt = stack.getOrCreateNbt();
            long now = attacker.getWorld().getTime();          // current tick
            long lastHit = nbt.getLong("YatoLastHit");
            int combo = nbt.getInt("YatoCombo");

            // If it's been more than 3 seconds (60 ticks) since the last hit, reset the streak
            if (now - lastHit > 60) {
                combo = 0;
            }

            // Build the streak, capped at 5
            combo = Math.min(combo + 1, 5);

            // Store the new state back on the stack
            nbt.putInt("YatoCombo", combo);
            nbt.putLong("YatoLastHit", now);

            // Bonus damage scales with the streak — +1 per stack beyond the first
            float bonus = (combo - 1) * 1.0f;
            if (bonus > 0) {
                target.timeUntilRegen = 0; // clear i-frames so the bonus lands
                target.damage(attacker.getWorld().getDamageSources().mobAttack(attacker), bonus);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.blazingemblem.yato.title").formatted(Formatting.GOLD, Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip.blazingemblem.yato.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
