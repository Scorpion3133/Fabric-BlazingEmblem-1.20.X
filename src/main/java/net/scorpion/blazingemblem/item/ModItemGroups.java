package net.scorpion.blazingemblem.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;
import net.scorpion.blazingemblem.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup BE_WEAPONS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlazingEmblem.MOD_ID, "be_weapons"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.be_weapons"))
                    .icon(() -> new ItemStack(ModItems.FALCHION)).entries((displayContext, entries) -> {
                        entries.add(ModItems.STEEL_SWORD);
                        entries.add(ModItems.UMBRAL_STEEL_SWORD);
                        entries.add(ModItems.STEEL_AXE);
                        entries.add(ModItems.UMBRAL_STEEL_AXE);

                        entries.add(ModItems.WOODEN_SPEAR);
                        entries.add(ModItems.STONE_SPEAR);
                        entries.add(ModItems.IRON_SPEAR);
                        entries.add(ModItems.STEEL_SPEAR);
                        entries.add(ModItems.GOLDEN_SPEAR);
                        entries.add(ModItems.DIAMOND_SPEAR);
                        entries.add(ModItems.NETHERITE_SPEAR);
                        entries.add(ModItems.UMBRAL_STEEL_SPEAR);

                        entries.add(ModItems.FALCHION);
                        entries.add(ModItems.BINDING_BLADE);
                        entries.add(ModItems.RAGNELL);
                        entries.add(ModItems.AREADBHAR);
                        entries.add(ModItems.AYMR);
                        entries.add(ModItems.FAILNAUGHT);

                    }).build());

    public static final ItemGroup EMBLEM_RINGS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlazingEmblem.MOD_ID, "emblem_rings"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.emblem_rings"))
                    .icon(() -> new ItemStack(ModItems.RING_HERO_KING)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RING_HERO_KING);
                        entries.add(ModItems.RING_YOUNG_LION);
                        entries.add(ModItems.RING_RADIANT_HERO);
                        entries.add(ModItems.BRACELET_THREE_HOUSES);

                    }).build());

    public static final ItemGroup BE_METALS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(BlazingEmblem.MOD_ID, "be_metals"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.be_metals"))
                    .icon(() -> new ItemStack(ModItems.SILVER_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModBlocks.STEEL_BLOCK);
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);
                        entries.add(ModItems.UMBRAL_STEEL_INGOT);
                        entries.add(ModBlocks.UMBRAL_STEEL_BLOCK);

                    }).build());

    public  static void registerItemGroups() {
        BlazingEmblem.LOGGER.info("Registering Item Groups for " + BlazingEmblem.MOD_ID);
    }
}
