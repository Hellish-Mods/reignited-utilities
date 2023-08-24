package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class CompressedBlock extends Block {
    public Integer compTier;
    public String mat;

    public CompressedBlock(Integer compTier, String mat) {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE));
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        this.compTier = compTier;
        this.mat = mat;
    }
    
    @Override
    public IFormattableTextComponent getName() {
        if (FMLEnvironment.dist!=Dist.CLIENT) return null;
        if (I18n.exists(getDescriptionId())) return new TranslationTextComponent(getDescriptionId());
        return new StringTextComponent(I18n.get("block."+reignitedutilities.MOD_ID+".compressed."+Integer.toString(compTier))+" "+I18n.get("block.minecraft."+mat));
    }
    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }
}
