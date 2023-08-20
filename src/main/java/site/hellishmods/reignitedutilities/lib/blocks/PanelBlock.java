package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.tileentities.GPProducingTile;

public class PanelBlock<T extends GPProducingTile> extends GPProducingBlock<T>{
    public PanelBlock(RegistryObject<TileEntityType<T>> tileType) {
        super(AbstractBlock.Properties.copy(Blocks.COBBLESTONE), tileType);

    }
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
