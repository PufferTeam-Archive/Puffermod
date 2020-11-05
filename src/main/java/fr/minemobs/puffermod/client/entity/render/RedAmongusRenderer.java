package fr.minemobs.puffermod.client.entity.render;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.client.entity.model.RedAmongusModel;
import fr.minemobs.puffermod.client.entity.model.TestEntityModel;
import fr.minemobs.puffermod.entity.RedAmongusEntity;
import fr.minemobs.puffermod.entity.TestEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RedAmongusRenderer extends MobRenderer<RedAmongusEntity, RedAmongusModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Main.modId,
            "textures/entity/texturemoche_red_amongus_entity.png");

    public RedAmongusRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RedAmongusModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(RedAmongusEntity entity) {
        return TEXTURE;
    }
}
