package net.scorpion.blazingemblem.item.client;

import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;
import net.scorpion.blazingemblem.item.custom.AcademyArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class AcademyArmorModel extends DefaultedItemGeoModel<AcademyArmorItem> {
    public AcademyArmorModel() {
        super(new Identifier(BlazingEmblem.MOD_ID, "armor/academy_armor"));
    }
}
