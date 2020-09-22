package fr.minemobs.puffermod.client.entity.render;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.client.entity.model.TestEntityModel;
import fr.minemobs.puffermod.entity.TestEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TestEntityRender extends MobRenderer<TestEntity, TestEntityModel<TestEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Main.modId,
            "textures/entity/test_entity.png");

    public TestEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TestEntityModel<TestEntity>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TestEntity entity) {
        return TEXTURE;
    }
}
