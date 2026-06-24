package net.scorpion.blazingemblem.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;

public class ModTags {
    public static class Blocks {


        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(BlazingEmblem.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SPEARS = createTag("spears");
        public static final TagKey<Item> SWORDS = createTag("swords");
        public static final TagKey<Item> AXES = createTag("axes");
        public static final TagKey<Item> BOWS = createTag("bows");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(BlazingEmblem.MOD_ID, name));
        }
    }
}
