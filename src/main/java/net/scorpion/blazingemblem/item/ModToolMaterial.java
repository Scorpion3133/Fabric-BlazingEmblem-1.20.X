package net.scorpion.blazingemblem.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.MinecartItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    STEEL(2, 750, 6.5f, 2.0f, 14,
            () -> Ingredient.ofItems(ModItems.STEEL_INGOT)),
    SILVER(2, 550, 7.0f, 3.0f, 22,
            () -> Ingredient.ofItems(ModItems.SILVER_INGOT)),
    UMBRAL_STEEL(4, 2200, 9f, 5.0f, 15,
            () -> Ingredient.ofItems(ModItems.UMBRAL_STEEL_INGOT)),
    MYTHRIL(4, 2400, 9f, 3f, 22,
                         () -> Ingredient.ofItems(ModItems.MYTHRIL_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
