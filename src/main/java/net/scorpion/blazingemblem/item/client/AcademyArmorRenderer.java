package net.scorpion.blazingemblem.item.client;

import net.scorpion.blazingemblem.item.custom.AcademyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AcademyArmorRenderer extends GeoArmorRenderer<AcademyArmorItem> {
    public AcademyArmorRenderer() {
        super(new AcademyArmorModel());
    }
}
