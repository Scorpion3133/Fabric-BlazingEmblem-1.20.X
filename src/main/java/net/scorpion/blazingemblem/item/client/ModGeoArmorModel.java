package net.scorpion.blazingemblem.item.client;

import net.minecraft.util.Identifier;
import net.scorpion.blazingemblem.BlazingEmblem;
import net.scorpion.blazingemblem.item.custom.ModGeoArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class ModGeoArmorModel extends GeoModel<ModGeoArmorItem> {
    private final Identifier model, texture, animation;

    public ModGeoArmorModel(String modelName, String textureName) {
        this.model = new Identifier(BlazingEmblem.MOD_ID, "geo/item/armor/" + modelName + ".geo.json");
        this.texture = new Identifier(BlazingEmblem.MOD_ID, "textures/item/armor/" + textureName + ".png");
        this.animation = new Identifier(BlazingEmblem.MOD_ID, "animations/item/armor/shared.animation.json");
    }

    @Override public Identifier getModelResource(ModGeoArmorItem a) { return this.model; }
    @Override public Identifier getTextureResource(ModGeoArmorItem a) { return this.texture; }
    @Override public Identifier getAnimationResource(ModGeoArmorItem a) { return this.animation; }
}