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

        });
    }
}
