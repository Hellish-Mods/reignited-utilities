package site.hellishmods.reignitedutilites.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.GPData;
import site.hellishmods.reignitedutilites.lib.blocks.CompressedBlock;

public class Blocks {
    static public ArrayList<RegistryObject<CompressedBlock>> CompressedBlocks = new ArrayList<>();

    static void initCompressed() { 
        HashMap<String, Integer> mats = new HashMap<>();
        mats.put("cobblestone", 8);
        mats.put("dirt", 4);
        mats.put("sand", 2);
        mats.put("gravel", 2);
        mats.put("netherrack", 6);
        
        mats.forEach((mat, compTier) -> {
            IntStream.range(1, compTier+1).forEach(i -> {
                RegistryObject<CompressedBlock> b = reignitedutilites.BLOCKS.register(Integer.toString(i)+"_compressed_"+mat, () -> new CompressedBlock(i, mat));
                CompressedBlocks.add(b);
            });
        });
    }
    public static void init() {
        initCompressed();

        reignitedutilites.BLOCKS.register("creative_mill", () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.COBBLESTONE)) {
            void addGP(int amount, PlayerEntity player) {
                if (player.level instanceof ServerWorld) GPData.set(player, GPData.get(player, "o")+amount, "o");
            }

            @Override
            public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) { // TODO: add on break, fix crystal tooltip and make the data persistant 
                addGP(10000, (PlayerEntity)entity); // TODO: tooltip on screen
            }
            @Override
            public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
                addGP(-10000, player);
                return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
            }
        }); // TODO: replace properties & texture
    }
}
