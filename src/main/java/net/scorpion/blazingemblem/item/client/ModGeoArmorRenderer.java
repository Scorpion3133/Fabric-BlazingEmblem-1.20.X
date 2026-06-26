package net.scorpion.blazingemblem.item.client;

import net.scorpion.blazingemblem.item.custom.ModGeoArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ModGeoArmorRenderer extends GeoArmorRenderer<ModGeoArmorItem> {
    public ModGeoArmorRenderer(String modelName, String textureName) {
        super(new ModGeoArmorModel(modelName, textureName));
    }
}
