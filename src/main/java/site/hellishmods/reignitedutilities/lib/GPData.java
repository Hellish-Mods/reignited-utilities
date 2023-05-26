package site.hellishmods.reignitedutilities.lib;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import site.hellishmods.reignitedutilities.reignitedutilities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = reignitedutilities.MOD_ID)
public class GPData extends WorldSavedData {
    public final static String NAME = reignitedutilities.MOD_ID+"_gp";
    CompoundNBT rawGPData = new CompoundNBT();

    public GPData(String name) {super(name);}
    public GPData() {
        super(NAME);
    }

    private static GPData getInstance() {
        return ServerLifecycleHooks.getCurrentServer().getLevel(World.OVERWORLD).getDataStorage().computeIfAbsent(GPData::new, NAME);
    }
    
    public static Float get(String id, String mode) {
        return getInstance().rawGPData.getCompound(id).getFloat(mode);
    }
    private static void set(String id, Float ogp, Float igp) {
        GPData instance = getInstance();

        CompoundNBT newData = new CompoundNBT();
        newData.putFloat("i", igp);
        newData.putFloat("o", ogp);

        instance.setDirty(false);
        instance.rawGPData.put(id, newData);
        instance.setDirty(true);
    }
    public static void set(String id, Float gp, String mode) {
        if (mode=="o") set(id, gp, get(id, "i"));
        else set(id, get(id, "o"), gp);
    }
    public static void generatePlayerGP(String id) {
        if (getInstance().rawGPData.contains(id)) return;

        set(id, 0f, "i");
        set(id, 0f, "o");
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
