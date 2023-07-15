package site.hellishmods.reignitedutilities.lib.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpikeBlock extends Block {
    int damage;
    DamageSource source;
    public SpikeBlock(Block propertiesBlock, int damage, DamageSource source) {
        super(AbstractBlock.Properties.copy(propertiesBlock).noOcclusion());
        this.damage = damage;
        this.source = source;
    }
    
    @Override
    public void	stepOn(World world, BlockPos pos, Entity entity) {
        entity.hurt(source, damage);
    }
}
