package fr.minemobs.puffermod.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.minemobs.puffermod.tileentity.ElectricFurnaceTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class ElectricFurnaceTileEntityRenderer extends TileEntityRenderer<ElectricFurnaceTileEntity> {

    public ElectricFurnaceTileEntityRenderer(final TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    /**
     * Render our TileEntity
     */


    @Override
    public void render(final ElectricFurnaceTileEntity tileEntityIn,
                       final float partialTicks, final MatrixStack matrixStack,
                       final IRenderTypeBuffer renderTypeBuffer, final int packedLight,
                       final int backupPackedLight) {

        // Set up GL state
//		RenderHelper.enableStandardItemLighting();
        RenderHelper.enableStandardItemLighting();

        final boolean hasEnergy = tileEntityIn.energy.getEnergyStored() >= 120;
        final BlockState renderState = Blocks.REDSTONE_TORCH.getDefaultState()
                .with(RedstoneTorchBlock.LIT, hasEnergy);
        // Render the torch (We use the depreciated method because we don't have an IModelData instance and want to use the default one)
        Minecraft.getInstance().getBlockRendererDispatcher()
                .renderBlock(renderState, matrixStack, renderTypeBuffer, packedLight, backupPackedLight);

        // Clean up GL state
        RenderHelper.disableStandardItemLighting();
    }

}