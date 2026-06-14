package net.scorpion.blazingemblem.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;

public class ModItems {
    public static final Item BINDING_BLADE = registerItem("binding_blade", new Item(new FabricItemSettings()));

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries){
        entries.add(BINDING_BLADE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(BlazingEmblem.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BlazingEmblem.LOGGER.info("Registering Mod Items for " + BlazingEmblem.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
    }
}
