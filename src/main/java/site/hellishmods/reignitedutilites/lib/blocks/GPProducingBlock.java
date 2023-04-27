package site.hellishmods.reignitedutilites.lib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilites.lib.tileentities.GPProducingTile;

public class GPProducingBlock<T extends GPProducingTile> extends Block {
    T tile;
    RegistryObject<TileEntityType<T>> tileType;

    public GPProducingBlock(Properties properties, RegistryObject<TileEntityType<T>> tileType) {
        super(properties);
        this.tileType = tileType;
    }

    @Override
    public void appendHoverText(ItemStack stack, IBlockReader reader, java.util.List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (tile!=null) tooltip.add(new TranslationTextComponent("block.reignitedutilites.gp_producing.tooltip", tile.getGPOutput()));
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
        tile = tileType.get().create();
        tile.getTileData().putString("owner", player.getStringUUID()); // TODO: fix duplication duplication
    }

    @Override
    public boolean hasTileEntity(BlockState state) {return true;}
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {return tile;}
}
