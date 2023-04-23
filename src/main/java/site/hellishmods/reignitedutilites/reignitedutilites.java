package site.hellishmods.reignitedutilites;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import site.hellishmods.reignitedutilites.init.Blocks;
import site.hellishmods.reignitedutilites.init.Items;

@Mod(reignitedutilites.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = reignitedutilites.MOD_ID)
public class reignitedutilites
{
    // Consts and vars
    public static final String MOD_ID = "reignitedutilites";
    public static final ItemGroup TAB = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.unstableIngot.get());
        }
    };

    // Registries
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public reignitedutilites() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus(); // Get event bus

        // Register stuff
        Items.init(); // Items
        Blocks.init(); // Blocks

        ITEMS.register(bus); // Add item registry to bus
        BLOCKS.register(bus); // Add block registry to bus

        MinecraftForge.EVENT_BUS.register(this); // Register mod
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> e) {
        for (RegistryObject<Block> block : BLOCKS.getEntries()) {
            e.getRegistry().register(new BlockItem(block.get(), new Item.Properties().tab(TAB)) {
                @Override
                public ITextComponent getName(ItemStack i) {
                    if (FMLEnvironment.dist!=Dist.CLIENT) return null;
                    return block.get().getName();
                }
            }.setRegistryName(block.get().getRegistryName()));
        }
    }
}
