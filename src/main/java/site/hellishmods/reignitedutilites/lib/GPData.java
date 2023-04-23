package site.hellishmods.reignitedutilites.lib;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import site.hellishmods.reignitedutilites.reignitedutilites;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = reignitedutilites.MOD_ID)
public class GPData extends WorldSavedData {
    public final static String NAME = reignitedutilites.MOD_ID+"_gp";
    CompoundNBT rawGPData = new CompoundNBT();

    public GPData(String name) {super(name);}
    public GPData() {
        super(NAME);
    }
    
    public static Integer get(PlayerEntity player, String mode) {
        return ((ServerWorld)player.level).getDataStorage().get(GPData::new, GPData.NAME).rawGPData.getCompound(player.getStringUUID()).getInt(mode);
    }
    private static void set(PlayerEntity player, int ogp, int igp) {
        GPData instance = ((ServerWorld)player.level).getDataStorage().get(GPData::new, GPData.NAME);

        CompoundNBT newData = new CompoundNBT();
        newData.putInt("i", igp);
        newData.putInt("o", ogp);

        instance.setDirty(false);
        instance.rawGPData.put(player.getStringUUID(), newData);
        instance.setDirty(true);
    }
    public static void set(PlayerEntity player, int gp, String mode) {
        if (mode=="o") set(player, gp, get(player, "i"));
        else set(player, get(player, "o"), gp);
    }
    public static void generatePlayerGP(PlayerEntity player) {
        GPData instance = ((ServerWorld)player.level).getDataStorage().computeIfAbsent(GPData::new, GPData.NAME);

        if (instance.rawGPData.contains(player.getStringUUID())) return;

        set(player, 0, "i");
        set(player, 0, "o");
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent e) {
        if (!(e.getEntity() instanceof PlayerEntity) || !(e.getWorld() instanceof ServerWorld)) return;

        generatePlayerGP((PlayerEntity)e.getEntity());
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
