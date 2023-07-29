package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class PortalBlock extends Block {
    Minecraft mc;
    RegistryKey<World> dimension;
    boolean cooldown = false;

    public PortalBlock(RegistryKey<World> dimension) {
        super(AbstractBlock.Properties.copy(Blocks.STONE)); // TODO: replace

        if (FMLEnvironment.dist==Dist.CLIENT) mc = Minecraft.getInstance();
        MinecraftForge.EVENT_BUS.register(this);

        this.dimension = dimension;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void renderOverlay(RenderGameOverlayEvent.Post event) { // TODO: fix
        if (!mc.player.getTags().contains("standingOn"+getRegistryName().getPath())) return;

        IngameGui gui = mc.gui;
        mc.getTextureManager().bind(new ResourceLocation(reignitedutilities.MOD_ID, "block/"+getRegistryName().getPath()));
        gui.blit(event.getMatrixStack(), 0, 0, event.getWindow().getWidth(), event.getWindow().getHeight(), 0, 0);
    }
    
    @Override
    public void	stepOn(World world, BlockPos pos, Entity entity) {
        entity.addTag("standingOn"+getRegistryName().getPath());
        if (world instanceof ServerWorld && world.dimension()!=dimension && !cooldown) { // TODO: test
            entity.changeDimension(world.getServer().getLevel(dimension));
            cooldown = true;
        }
    }
}
