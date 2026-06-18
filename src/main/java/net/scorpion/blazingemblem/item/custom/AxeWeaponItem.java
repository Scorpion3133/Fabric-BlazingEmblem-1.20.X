package net.scorpion.blazingemblem.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AxeWeaponItem extends SwordItem {
    public AxeWeaponItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient() && target instanceof PlayerEntity player && player.isBlocking()) {
            player.getItemCooldownManager().set(Items.SHIELD, 100);
            player.clearActiveItem();
            player.getWorld().sendEntityStatus(player, (byte) 30);
        }
        return super.postHit(stack, target, attacker);
    }
}
