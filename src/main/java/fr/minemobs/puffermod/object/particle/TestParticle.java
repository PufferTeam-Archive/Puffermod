package fr.minemobs.puffermod.object.particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TestParticle extends SpriteTexturedParticle {

    public TestParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        float f = rand.nextInt() * 1.0f;
        particleRed = f;
        particleGreen = f;
        particleBlue = f;

        setSize(0.02f, 0.02f);
        particleScale *= rand.nextFloat() * 1.1f;
        motionX *= 0.02;
        motionY *= 0.02;
        motionZ *= 0.02;
        maxAge = (int)(20.0D / (Math.random() * 1.0));

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        if(maxAge-- <= 0){
            setExpired();
        }else{
            move(motionX, motionY, motionZ);
            motionX *= 1.0D;
            motionY *= 1.0D;
            motionZ *= 1.0D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {

        private static IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite){
            spriteSet = sprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TestParticle testParticle = new TestParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            testParticle.setColor(1.0f, 1.0f, 1.0f);
            testParticle.selectSpriteRandomly(spriteSet);
            return testParticle;
        }
    }
}
