package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.tileentities.GPProducingTile;

public class GPProducingBlock<T extends GPProducingTile> extends GPBlock<T> {
    public GPProducingBlock(Properties properties, RegistryObject<TileEntityType<T>> tileType) {
        super(properties, tileType);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, IBlockReader reader, java.util.List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("block.reignitedutilities.gp_producing.tooltip", tileType.get().create().getGP()));
    }
}
