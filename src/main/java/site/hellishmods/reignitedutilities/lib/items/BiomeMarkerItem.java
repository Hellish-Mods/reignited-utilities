package site.hellishmods.reignitedutilities.lib.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class BiomeMarkerItem extends Item {
    public BiomeMarkerItem() {
        super(new Item.Properties().tab(reignitedutilities.TAB).stacksTo(1));
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }
    
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        CompoundNBT nbt = player.getItemInHand(hand).getOrCreateTag();
        if (player.isShiftKeyDown() && nbt.contains("biome")) nbt.remove("biome");
        else if (!player.isShiftKeyDown() && !nbt.contains("biome")) nbt.putString("biome", world.getBiome(new BlockPos(player.position())).getRegistryName().toString());

        return super.use(world, player, hand);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, java.util.List<ITextComponent> tooltip, ITooltipFlag flag) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("biome")) tooltip.add(new TranslationTextComponent("biome."+nbt.getString("biome").replace(":", ".")).withStyle(TextFormatting.GRAY));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void addBiomeColor(ColorHandlerEvent.Item e) {
        IItemColor biomeColor = (stack, index) -> {
            if (!stack.getOrCreateTag().contains("biome") || index==0) return 0xFFFFFF;

            Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(stack.getTag().getString("biome")));
            int[] colors = {biome.getSkyColor(), biome.getFoliageColor()};
            return colors[index-1];
        };
        e.getItemColors().register(biomeColor, this);
    }
}
