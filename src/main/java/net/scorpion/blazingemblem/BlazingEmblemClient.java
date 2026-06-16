package net.scorpion.blazingemblem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.scorpion.blazingemblem.item.ModItems;

public class BlazingEmblemClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack,context,lines) -> {
           if (stack.isOf(ModItems.FALCHION)) {
               lines.add(Text.literal("Divine Blade of the Hero-King")
                       .formatted(Formatting.GOLD, Formatting.ITALIC));
           } else if (stack.isOf(ModItems.BINDING_BLADE)) {
               lines.add(Text.literal("Sealing Flame of the Young Lion")
                       .formatted(Formatting.GOLD, Formatting.ITALIC));
           } else if (stack.isOf(ModItems.RAGNELL)) {
               lines.add(Text.literal("Legendary Sword of the Radiant Hero")
                       .formatted(Formatting.GOLD, Formatting.ITALIC));
           } else if (stack.isOf(ModItems.AREADBHAR)) {
               lines.add(Text.literal("Hero's Relic of the Savior King")
                       .formatted(Formatting.GOLD, Formatting.ITALIC));
           }
        });
    }
}
