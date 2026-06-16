package net.scorpion.blazingemblem.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;

public class ModItemGroups {
    public static final ItemGroup BE_WEAPONS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlazingEmblem.MOD_ID, "be_weapons"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.be_weapons"))
                    .icon(() -> new ItemStack(ModItems.BINDING_BLADE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FALCHION);
                        entries.add(ModItems.BINDING_BLADE);
                        entries.add(ModItems.RAGNELL);
                        entries.add(ModItems.AREADBHAR);

                    }).build());

    public static final ItemGroup EMBLEM_RINGS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlazingEmblem.MOD_ID, "emblem_rings"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.emblem_rings"))
                    .icon(() -> new ItemStack(ModItems.RING_HERO_KING)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RING_HERO_KING);
                        entries.add(ModItems.RING_YOUNG_LION);

                    }).build());

    public  static void registerItemGroups() {
        BlazingEmblem.LOGGER.info("Registering Item Groups for " + BlazingEmblem.MOD_ID);
    }
}
