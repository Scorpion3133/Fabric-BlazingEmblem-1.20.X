package net.scorpion.blazingemblem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.item.ModItems;
import net.scorpion.blazingemblem.item.custom.FailnaughtItem;

public class BlazingEmblemClient implements ClientModInitializer {

    public static void registerModelPredicateProviders(Item bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null)
                        return 0.0F;
                    return entity.getActiveItem() != stack ? 0.0F : (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
                });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

    }

    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack,context,lines) -> {});
        registerModelPredicateProviders(ModItems.FAILNAUGHT);

    }
}
