package fr.minemobs.puffermod.entity;

import fr.minemobs.puffermod.init.ItemInit;
import fr.minemobs.puffermod.particle.ColouredParticle;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.minecart.MinecartCommandBlockEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.event.ParticleKeyFrameEvent;
import software.bernie.geckolib.event.SoundKeyframeEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedAmongusEntity extends AnimalEntity implements IAnimatedEntity {

    HashMap<UUID,String> uuid = new HashMap<UUID, String>();

    private int testtimer;

    private EntityAnimationManager manager = new EntityAnimationManager();
    private AnimationController controller = new EntityAnimationController(this, "moveController", 20,
            this::animationPredicate);

    public RedAmongusEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, PlayerEntity.class, 1.0F, 1.2D, 0.5D));
        super.registerGoals();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.4F);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.testtimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_) {
        if (this.testtimer <= 0) {
            return 0.0F;
        } else if (this.testtimer >= 4 && this.testtimer <= 36) {
            return 1.0F;
        } else {
            return this.testtimer < 4 ? ((float)this.testtimer - p_70894_1_) / 4.0F : -((float)(this.testtimer - 40) - p_70894_1_) / 4.0F;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_) {
        if (this.testtimer > 4 && this.testtimer <= 36) {
            float f = ((float)(this.testtimer - 4) - p_70890_1_) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
        } else {
            return this.testtimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * ((float)Math.PI / 180F);
        }
    }

    @Override
    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
        PlayerEntity player = Minecraft.getInstance().player;
        player.sendMessage(new StringTextComponent("There is\\u00A7c 1 imposter\\u00A7r among us"));
        super.onStruckByLightning(lightningBolt);
    }

    @Override
    public void onDeath(DamageSource cause) {
        PlayerEntity player = Minecraft.getInstance().player;
        if(player.getHeldItemMainhand().getItem() != Items.IRON_SWORD &&
        player.getHeldItemMainhand().getDisplayName() != new StringTextComponent("knife")) return;
        System.out.println(player.getHeldItemMainhand().getDisplayName());
        ChickenEntity ce = new ChickenEntity(EntityType.CHICKEN, world);
        ce.isChild();
        ce.setCustomName(new StringTextComponent("There is still 1 imposter among us"));
        ce.setPosition(getPosX(), getPosY(), getPosZ());
        ce.setCustomNameVisible(true);
        world.addEntity(ce);
        super.onDeath(cause);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends RedAmongusEntity> boolean animationPredicate(AnimationTestEvent<E> event) {
        if(event.isWalking()){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.puffermod.walk", true));
            return true;
        }else{
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.puffermod.idle", true));
            return true;
        }
    }

    private <E extends Entity> SoundEvent soundListener(SoundKeyframeEvent<E> event){
        if(event.sound.equals("test")){
            return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValues().toArray()[rand.nextInt(ForgeRegistries.SOUND_EVENTS.getValues().size())];
        }else {
            return getAmbientSound();
        }
    }

    private <E extends Entity> void particleListener(ParticleKeyFrameEvent<E> event) {
        if (event.effect.equals("testparticle")) {
            event.getEntity().getEntityWorld().addParticle(new ColouredParticle.ColouredParticleData(0.2f, 0.7f, 0.5f, 1.0f),
                    event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), 0.0f, 0.4f,
                    0.0f);
        }
    }

    private void registerAnimationControllers(){
        manager.addAnimationController(controller);
        //controller.registerSoundListener(this::soundListener);
        //controller.registerParticleListener(this::particleListener);
    }

}
