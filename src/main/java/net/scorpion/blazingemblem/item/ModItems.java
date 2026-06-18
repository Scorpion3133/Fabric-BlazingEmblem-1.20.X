package net.scorpion.blazingemblem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;
import net.scorpion.blazingemblem.item.custom.*;

public class ModItems {
    // Weapons
    public static final Item FALCHION = registerItem("falchion",
            new FalchionItem(ModToolMaterial.BLAZING_EMBLEM, 4, -2f, new FabricItemSettings()));
    public static final Item BINDING_BLADE = registerItem("binding_blade",
            new BindingBladeItem(ModToolMaterial.BLAZING_EMBLEM, 4, -2f, new FabricItemSettings()));
    public static final Item RAGNELL = registerItem("ragnell",
            new RagnellItem(ModToolMaterial.BLAZING_EMBLEM, 5, -2.5f, new FabricItemSettings()));
    public static final Item AREADBHAR = registerItem("areadbhar",
            new AreadbharItem(ModToolMaterial.BLAZING_EMBLEM, 3, -2f, new FabricItemSettings()));
    public static final Item AYMR = registerItem("aymr",
            new AymrItem(ModToolMaterial.BLAZING_EMBLEM, 5, -2.5f, new FabricItemSettings()));
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
