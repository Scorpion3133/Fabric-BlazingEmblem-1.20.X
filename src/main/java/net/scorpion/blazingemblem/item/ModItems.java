package net.scorpion.blazingemblem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;
import net.scorpion.blazingemblem.item.custom.*;

public class ModItems {
    // Metals
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new FabricItemSettings()));
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new FabricItemSettings()));
    public static final Item UMBRAL_STEEL_INGOT = registerItem("umbral_steel_ingot", new Item(new FabricItemSettings()));
    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot", new Item(new FabricItemSettings()));

    // Regular Weapons
    //  Old Metal's Spears
    public static final Item WOODEN_SPEAR = registerItem("wooden_spear",
            new SpearItem(ToolMaterials.WOOD, 2, -2.4f, new FabricItemSettings()));
    public static final Item STONE_SPEAR = registerItem("stone_spear",
            new SpearItem(ToolMaterials.STONE, 2, -2.4f, new FabricItemSettings()));
    public static final Item IRON_SPEAR = registerItem("iron_spear",
            new SpearItem(ToolMaterials.IRON, 2, -2.4f, new FabricItemSettings()));
    public static final Item GOLDEN_SPEAR = registerItem("golden_spear",
            new SpearItem(ToolMaterials.GOLD, 2, -2.4f, new FabricItemSettings()));
    public static final Item DIAMOND_SPEAR = registerItem("diamond_spear",
            new SpearItem(ToolMaterials.DIAMOND, 2, -2.4f, new FabricItemSettings()));
    public static final Item NETHERITE_SPEAR = registerItem("netherite_spear",
            new SpearItem(ToolMaterials.NETHERITE, 2, -2.4f, new FabricItemSettings()));
    //  Steel
    public static final Item STEEL_SWORD = registerItem("steel_sword",
            new SwordItem(ModToolMaterial.STEEL, 3, -2.2f, new FabricItemSettings()));
    public static final Item STEEL_AXE = registerItem("steel_axe",
            new AxeItem(ModToolMaterial.STEEL, 5, -2.9f, new FabricItemSettings()));
    public static final Item STEEL_SPEAR = registerItem("steel_spear",
            new SpearItem(ModToolMaterial.STEEL, 2, -2.2f, new FabricItemSettings()));
    // Silver
    public static final Item SILVER_SWORD = registerItem("silver_sword",
            new SwordItem(ModToolMaterial.SILVER, 3, -2.7f, new FabricItemSettings()));
    public static final Item SILVER_AXE = registerItem("silver_axe",
            new AxeItem(ModToolMaterial.SILVER, 6, -3.1f, new FabricItemSettings()));
    public static final Item SILVER_SPEAR = registerItem("silver_spear",
            new SpearItem(ModToolMaterial.SILVER, 2, -2.7f, new FabricItemSettings()));
    // Umbral Steel
    public static final Item UMBRAL_STEEL_SWORD = registerItem("umbral_steel_sword",
            new SwordItem(ModToolMaterial.UMBRAL_STEEL, 3, -2.6f, new FabricItemSettings()));
    public static final Item UMBRAL_STEEL_AXE = registerItem("umbral_steel_axe",
            new AxeItem(ModToolMaterial.UMBRAL_STEEL, 6f, -3.2f, new FabricItemSettings()));
    public static final Item UMBRAL_STEEL_SPEAR = registerItem("umbral_steel_spear",
            new SpearItem(ModToolMaterial.UMBRAL_STEEL, 2, -2.6f, new FabricItemSettings()));
    // Mithril
    public static final Item MYTHRIL_SWORD = registerItem("mythril_sword",
            new SwordItem(ModToolMaterial.MYTHRIL, 3, -2.2f, new FabricItemSettings()));
    public static final Item MYTHRIL_AXE = registerItem("mythril_axe",
            new AxeItem(ModToolMaterial.MYTHRIL, 5, -2.9f, new FabricItemSettings()));
    public static final Item MYTHRIL_SPEAR = registerItem("mythril_spear",
            new SpearItem(ModToolMaterial.MYTHRIL, 2, -2.2f, new FabricItemSettings()));


    // Unique Weapons
    public static final Item FALCHION = registerItem("falchion",
            new FalchionItem(ModToolMaterial.MYTHRIL, 4, -2f, new FabricItemSettings()));
    public static final Item BINDING_BLADE = registerItem("binding_blade",
            new BindingBladeItem(ModToolMaterial.MYTHRIL, 4, -2f, new FabricItemSettings()));
    public static final Item RAGNELL = registerItem("ragnell",
            new RagnellItem(ModToolMaterial.MYTHRIL, 7, -2.6f, new FabricItemSettings()));
    public static final Item AREADBHAR = registerItem("areadbhar",
            new AreadbharItem(ModToolMaterial.UMBRAL_STEEL, 3, -2.4f, new FabricItemSettings()));
    public static final Item AYMR = registerItem("aymr",
            new AymrItem(ModToolMaterial.UMBRAL_STEEL, 10, -3f, new FabricItemSettings()));
    public static final Item FAILNAUGHT = registerItem("failnaught",
            new FailnaughtItem(new FabricItemSettings().maxDamage(768)));

    // Emblem Rings
    public static final Item RING_HERO_KING = registerItem("ring_hero_king", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item RING_YOUNG_LION = registerItem("ring_young_lion", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item RING_RADIANT_HERO = registerItem("ring_radiant_hero", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item BRACELET_THREE_HOUSES = registerItem("bracelet_three_houses", new Item(new FabricItemSettings().maxCount(16)));




    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries){

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlazingEmblem.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BlazingEmblem.LOGGER.info("Registering Mod Items for " + BlazingEmblem.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
    }
}
