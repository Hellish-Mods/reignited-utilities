package site.hellishmods.reignitedutilities.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.lib.tileentities.*;
import site.hellishmods.reignitedutilities.reignitedutilities;

public class TileEntities {
    public static RegistryObject<TileEntityType<SlightlyLargerChestTile>> slightly_larger_chest_entity_type;

    public static RegistryObject<TileEntityType<SolarPanelMillTile>> solar_panel_mill_entity_type;
    public static RegistryObject<TileEntityType<LunarPanelMillTile>> lunar_panel_mill_entity_type;
    public static RegistryObject<TileEntityType<WindMillTile>> wind_mill_entity_type;
    public static RegistryObject<TileEntityType<FireMillTile>> fire_mill_entity_type;
    public static RegistryObject<TileEntityType<CreativeMillTile>> creative_mill_entity_type;
    public static RegistryObject<TileEntityType<ManualMillTile>> manual_mill_entity_type;
    public static RegistryObject<TileEntityType<DragonEggMillTile>> dragon_egg_mill_entity_type;

    public static RegistryObject<TileEntityType<ChunkLoadingWardTile>> chunk_loading_ward_entity_type;

    public static void init() {
        slightly_larger_chest_entity_type = reignitedutilities.TILE_ENTITIES.register("slightly_larger_chest", () -> TileEntityType.Builder.of(SlightlyLargerChestTile::new, Blocks.SLIGHTLY_LARGER_CHEST.get()).build(null));

        solar_panel_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("solar_panel_mill", () -> TileEntityType.Builder.of(SolarPanelMillTile::new, Blocks.SOLAR_PANEL.get()).build(null));
        lunar_panel_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("lunar_panel_mill", () -> TileEntityType.Builder.of(LunarPanelMillTile::new, Blocks.LUNAR_PANEL.get()).build(null));
        wind_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("wind_mill", () -> TileEntityType.Builder.of(WindMillTile::new, Blocks.WIND_MILL.get()).build(null));
        fire_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("fire_mill", () -> TileEntityType.Builder.of(FireMillTile::new, Blocks.FIRE_MILL.get()).build(null));
        creative_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("creative_mill", () -> TileEntityType.Builder.of(CreativeMillTile::new, Blocks.CREATIVE_MILL.get()).build(null));
        manual_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("manual_mill", () -> TileEntityType.Builder.of(ManualMillTile::new, Blocks.MANUAL_MILL.get()).build(null));
        dragon_egg_mill_entity_type = reignitedutilities.TILE_ENTITIES.register("dragon_egg_mill", () -> TileEntityType.Builder.of(DragonEggMillTile::new, Blocks.DRAGON_EGG_MILL.get()).build(null));

        chunk_loading_ward_entity_type = reignitedutilities.TILE_ENTITIES.register("chunk_loading_ward", () -> TileEntityType.Builder.of(ChunkLoadingWardTile::new, Blocks.CHUNK_LOADING_WARD.get()).build(null));
    }
}
