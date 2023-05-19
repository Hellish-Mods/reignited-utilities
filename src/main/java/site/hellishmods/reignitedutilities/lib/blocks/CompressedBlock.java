package site.hellishmods.reignitedutilities.lib.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class CompressedBlock extends Block {
    public Integer compTier;
    public String mat;

    public CompressedBlock(Integer compTier, String mat) {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE));

        this.compTier = compTier;
        this.mat = mat;
    }
    
    @Override
    public IFormattableTextComponent getName() {
        if (FMLEnvironment.dist!=Dist.CLIENT) return null;
        return new StringTextComponent(I18n.get("block."+reignitedutilities.MOD_ID+".compressed."+Integer.toString(compTier))+" "+I18n.get("block.minecraft."+mat));
    }
}
