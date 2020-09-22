package fr.minemobs.puffermod.entity;

import fr.minemobs.puffermod.Main;
import fr.minemobs.puffermod.init.ItemInit;
import fr.minemobs.puffermod.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class TestEntity extends AnimalEntity {

    EatGrassGoal eatGrassGoal;
    private int testtimer;

    public TestEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
                                            @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        TestEntity entity = new TestEntity(ModEntityTypes.TEST_ENTITY.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING, (ILivingEntityData) null,
                (CompoundNBT) null);

        return entity;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.fromItems(ItemInit.cheese.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, this.eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, CreeperEntity.class, 1.0F, 1.2D, 0.5D));
    }

    @Override
    protected void updateAITasks() {
        testtimer = this.eatGrassGoal.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public boolean canBreed() {
        return true;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if(stack.getItem() == ItemInit.cheese.get()){

        }
        return true;
    }

    @Override
    public void livingTick() {
        if(world.isRemote){
            this.testtimer = Math.max(0, this.testtimer - 1);
        }
        super.livingTick();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.2F);
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
        Entity entity = this;
        World world = entity.getEntityWorld();
        BlockState bs = world.getBlockState(entity.getPosition());
        WitherEntity wiwi = new WitherEntity(EntityType.WITHER, world);
        wiwi.setCustomName(new TranslationTextComponent("name." + Main.modId + ".wither01"));
        world.addEntity(wiwi);
        entity.remove();
        super.onStruckByLightning(lightningBolt);
    }
}
