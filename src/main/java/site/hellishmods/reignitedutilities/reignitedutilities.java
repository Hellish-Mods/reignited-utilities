package site.hellishmods.reignitedutilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilities.init.Blocks;
import site.hellishmods.reignitedutilities.init.Items;
import site.hellishmods.reignitedutilities.init.TileEntities;
import site.hellishmods.reignitedutilities.lib.blocks.CompressedBlock;
import site.hellishmods.reignitedutilities.lib.items.AngelBlockItem;

@Mod(reignitedutilities.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = reignitedutilities.MOD_ID)
public class reignitedutilities // TODO: add license
{
    // Consts and vars
    public static final String MOD_ID = "reignitedutilities";
    public static final ItemGroup TAB = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.unstableIngot.get());
        }
    };

    // Registries
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    public reignitedutilities() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus(); // Get event bus

        // Register stuff
        Items.init(); // Items
        Blocks.init(); // Blocks
        TileEntities.init(); // TileEntities

        ITEMS.register(bus); // Add item registry to bus
        BLOCKS.register(bus); // Add block registry to bus
        TILE_ENTITIES.register(bus); // Add tile entity registry to bus

        MinecraftForge.EVENT_BUS.register(this); // Register mod
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            Blocks.SPIKES.forEach(s -> {
                RenderTypeLookup.setRenderLayer(s.get(), RenderType.translucent());
            });
        });
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> e) {
        for (RegistryObject<Block> block : BLOCKS.getEntries()) {
            if (!block.equals(Blocks.ANGEL_BLOCK))
                e.getRegistry().register(new BlockItem(block.get(), new Item.Properties().tab(TAB)) {
                    @Override
                    public ITextComponent getName(ItemStack i) {
                        if (FMLEnvironment.dist!=Dist.CLIENT) return null;
                        return block.get().getName();
                    }
                }.setRegistryName(block.get().getRegistryName()));
            else e.getRegistry().register(new AngelBlockItem().setRegistryName(block.get().getRegistryName()));
        }

        // TODO: FIX!!
        Blocks.CompressedBlocks.forEach(rb -> {
            CompressedBlock b = rb.get();

            if (ModList.get().isLoaded("thermal")) {
                List<Ingredient> inputitems = new ArrayList<>();
                inputitems.add(Ingredient.of(new ItemStack(b.asItem(), 9)));
                List<ItemStack> outputitems = new ArrayList<>();
                outputitems.add(new ItemStack(b.asItem()));
                List<Float> chances = new ArrayList<>();
                chances.add(1f);
    
                cofh.thermal.core.util.managers.machine.PressRecipeManager.instance().addRecipe(new cofh.thermal.core.util.recipes.machine.PressRecipe(new ResourceLocation(reignitedutilities.MOD_ID, "compat/thermal/press/compressed_"+b.mat+"_"+b.compTier), 400, 0, inputitems, new ArrayList<cofh.lib.fluid.FluidIngredient>() , outputitems, chances, new ArrayList<FluidStack>()));
            }
        });
    }
}
