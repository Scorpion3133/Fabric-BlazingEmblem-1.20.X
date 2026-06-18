package net.scorpion.blazingemblem.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FailnaughtItem extends BowWeaponItem {
    public FailnaughtItem(Settings settings) {
        super(settings, 1.5);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.blazingemblem.failnaught.title").formatted(Formatting.GOLD, Formatting.ITALIC));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
