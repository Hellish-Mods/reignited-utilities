package site.hellishmods.reignitedutilites.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilites.reignitedutilites;
import site.hellishmods.reignitedutilites.lib.tileentities.CreativeMillTile;
import site.hellishmods.reignitedutilites.lib.tileentities.DragonEggMillTile;
import site.hellishmods.reignitedutilites.lib.tileentities.FireMillTile;

public class TileEntities {
    public static RegistryObject<TileEntityType<FireMillTile>> fire_mill_entity_type;
    public static RegistryObject<TileEntityType<CreativeMillTile>> creative_mill_entity_type;
    public static RegistryObject<TileEntityType<DragonEggMillTile>> dragon_egg_mill_entity_type;

    public static void init() {
        fire_mill_entity_type = reignitedutilites.TILE_ENTITIES.register("fire_mill", () -> TileEntityType.Builder.of(FireMillTile::new, Blocks.FIRE_MILL.get()).build(null));
        creative_mill_entity_type = reignitedutilites.TILE_ENTITIES.register("creative_mill", () -> TileEntityType.Builder.of(CreativeMillTile::new, Blocks.CREATIVE_MILL.get()).build(null));
        dragon_egg_mill_entity_type = reignitedutilites.TILE_ENTITIES.register("dragon_egg_mill", () -> TileEntityType.Builder.of(DragonEggMillTile::new, Blocks.DRAGON_EGG_MILL.get()).build(null));
    }
}
