package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import site.hellishmods.reignitedutilities.reignitedutilities;

import static site.hellishmods.reignitedutilities.utils.FindGround.findGround;

import com.mojang.blaze3d.systems.RenderSystem;

public class PortalBlock extends Block {
    Minecraft mc;
    RegistryKey<World> dimension;
    boolean cooldown = false;
    int tickcount = 0;

    public PortalBlock(RegistryKey<World> dimension) {
        super(AbstractBlock.Properties.copy(Blocks.STONE)); // TODO: replace

        if (FMLEnvironment.dist==Dist.CLIENT) mc = Minecraft.getInstance();
        MinecraftForge.EVENT_BUS.register(this);

        this.dimension = dimension;
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        if (!mc.player.getTags().contains("standingOn"+getRegistryName().getPath())) return;

        IngameGui gui = mc.gui;
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bind(new ResourceLocation(reignitedutilities.MOD_ID, "textures/block/"+getRegistryName().getPath()+".png"));
        gui.blit(event.getMatrixStack(), 0, 0, 0, 0, event.getWindow().getWidth(), event.getWindow().getHeight());
    }
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        if (tickcount%10!=0) return;

        PlayerEntity player = event.player;
        Vector3d pos = player.position();
        String tag = "standingOn"+getRegistryName().getPath();
        boolean blockUnder = player.getCommandSenderWorld().getBlockState(new BlockPos(pos.x, pos.y-1, pos.z)).getBlock()==this;

        if (!player.getTags().contains(tag) && blockUnder) player.addTag(tag);
        else if (!blockUnder) player.removeTag(tag);
    }
    
    @Override
    public void	stepOn(World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld && world.dimension()!=dimension) {
            World dimLevel = world.getServer().getLevel(dimension);
            world.getServer().getCommands().performCommand(entity.createCommandSourceStack().withSuppressedOutput().withLevel((ServerWorld)dimLevel).withPosition(findGround(dimLevel, pos.getX(), pos.getY())).withPermission(2), "/tp ~ ~ ~");
        }
    }
}
