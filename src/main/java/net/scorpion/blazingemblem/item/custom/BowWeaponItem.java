package net.scorpion.blazingemblem.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class BowWeaponItem extends BowItem {
    private final double bonusDamage;
    private final float drawSpeed;

    public BowWeaponItem(Settings settings, double bonusDamage, float drawSpeed) {
        super(settings);
        this.bonusDamage = bonusDamage;
        this.drawSpeed = drawSpeed;
    }

    public float getDrawSpeed() {
        return this.drawSpeed;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        boolean infinite = player.getAbilities().creativeMode
                || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack arrowStack = player.getProjectileType(stack);
        if (arrowStack.isEmpty() && !infinite) return;
        if (arrowStack.isEmpty()) arrowStack = new ItemStack(Items.ARROW);

        int used = this.getMaxUseTime(stack) - remainingUseTicks;
        float pull = getPullProgress((int)(used * this.drawSpeed));
        if ((double) pull < 0.1) return;

        boolean freeArrow = infinite && arrowStack.isOf(Items.ARROW);
        if (!world.isClient) {
            ArrowItem arrowItem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
            PersistentProjectileEntity arrow = arrowItem.createArrow(world, arrowStack, player);
            arrow.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pull * 3.0F, 1.0F);
            if (pull == 1.0F) arrow.setCritical(true);

            arrow.setDamage(arrow.getDamage() + this.bonusDamage); // <-- your bonus

            int power = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
            if (power > 0) arrow.setDamage(arrow.getDamage() + (double) power * 0.5 + 0.5);
            int punch = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
            if (punch > 0) arrow.setPunch(punch);
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) arrow.setOnFireFor(100);

            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            if (freeArrow || player.getAbilities().creativeMode
                    && (arrowStack.isOf(Items.SPECTRAL_ARROW) || arrowStack.isOf(Items.TIPPED_ARROW))) {
                arrow.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            world.spawnEntity(arrow);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
                1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + pull * 0.5F);

        if (!freeArrow && !player.getAbilities().creativeMode) {
            arrowStack.decrement(1);
            if (arrowStack.isEmpty()) player.getInventory().removeOne(arrowStack);
        }
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

}
