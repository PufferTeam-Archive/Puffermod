package fr.minemobs.puffermod.client.entity.model;

import fr.minemobs.puffermod.entity.RedAmongusEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class RedAmongusModel extends AnimatedEntityModel<RedAmongusEntity> {

    private final AnimatedModelRenderer head;
    private final AnimatedModelRenderer body;
    private final AnimatedModelRenderer leftLeg;
    private final AnimatedModelRenderer rightLeg;

    public RedAmongusModel()
    {
        textureWidth = 64;
        textureHeight = 64;
        head = new AnimatedModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(24, 0).addBox(-3.0F, 8.0F, -2.2F, 6.0F, 4.0F, 1.0F, 0.0F, false);
        head.setModelRendererName("head");
        this.registerModelRenderer(head);

        body = new AnimatedModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-4.0F, 7.0F, -2.0F, 8.0F, 13.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(1, 18).addBox(-3.0F, 10.0F, 2.0F, 6.0F, 5.0F, 4.0F, 0.0F, false);
        body.setModelRendererName("body");
        this.registerModelRenderer(body);

        leftLeg = new AnimatedModelRenderer(this);
        leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        leftLeg.setTextureOffset(24, 24).addBox(-1.0F, 8.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        leftLeg.setModelRendererName("leftLeg");
        this.registerModelRenderer(leftLeg);

        rightLeg = new AnimatedModelRenderer(this);
        rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        rightLeg.setTextureOffset(24, 24).addBox(-1.0F, 8.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        rightLeg.setModelRendererName("rightLeg");
        this.registerModelRenderer(rightLeg);

        this.rootBones.add(head);
        this.rootBones.add(body);
        this.rootBones.add(leftLeg);
        this.rootBones.add(rightLeg);
    }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("puffermod", "animations/redamongusentity.json");
    }
}