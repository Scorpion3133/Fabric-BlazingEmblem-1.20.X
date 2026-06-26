package net.scorpion.blazingemblem.item.custom;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModGeoArmorItem extends ModArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    private final Supplier<GeoArmorRenderer<?>> rendererSupplier;

    public ModGeoArmorItem(ArmorMaterial material, Type type, Settings settings,
                           Supplier<GeoArmorRenderer<?>> rendererSupplier) {
        super(material, type, settings);
        this.rendererSupplier = rendererSupplier;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;
            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                                        EquipmentSlot slot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = rendererSupplier.get();   // <-- per-set renderer, passed in
                this.renderer.prepForRender(entity, stack, slot, original);
                return this.renderer;
            }
        });
    }

    @Override public Supplier<Object> getRenderProvider() { return this.renderProvider; }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return this.cache; }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> PlayState.STOP));
    }
}
