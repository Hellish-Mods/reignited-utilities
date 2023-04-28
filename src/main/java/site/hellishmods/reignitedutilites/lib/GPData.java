package site.hellishmods.reignitedutilites.lib;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import site.hellishmods.reignitedutilites.reignitedutilites;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = reignitedutilites.MOD_ID)
public class GPData extends WorldSavedData {
    public final static String NAME = reignitedutilites.MOD_ID+"_gp";
    CompoundNBT rawGPData = new CompoundNBT();

    public GPData(String name) {super(name);}
    public GPData() {
        super(NAME);
    }

    private static GPData getInstance() {
        return ServerLifecycleHooks.getCurrentServer().getLevel(World.OVERWORLD).getDataStorage().computeIfAbsent(GPData::new, NAME);
    }
    
    public static Integer get(String id, String mode) {
        return getInstance().rawGPData.getCompound(id).getInt(mode);
    }
    private static void set(String id, int ogp, int igp) {
        GPData instance = getInstance();

        CompoundNBT newData = new CompoundNBT();
        newData.putInt("i", igp);
        newData.putInt("o", ogp);

        instance.setDirty(false);
        instance.rawGPData.put(id, newData);
        instance.setDirty(true);
    }
    public static void set(String id, int gp, String mode) {
        if (mode=="o") set(id, gp, get(id, "i"));
        else set(id, get(id, "o"), gp);
    }
    public static void generatePlayerGP(String id) {
        if (getInstance().rawGPData.contains(id)) return;

        set(id, 0, "i");
        set(id, 0, "o");
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent e) {
        if (e.getEntity() instanceof PlayerEntity) generatePlayerGP(e.getEntity().getStringUUID());
    }

    @Override
    public void load(CompoundNBT nbt) {
        rawGPData = nbt;
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        return rawGPData;
    }
}
