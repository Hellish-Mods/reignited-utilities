package site.hellishmods.reignitedutilities.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.effects.DoomEffect;
import site.hellishmods.reignitedutilities.lib.effects.FizzyLiftingEffect;
import site.hellishmods.reignitedutilities.lib.effects.GravityEffect;
import site.hellishmods.reignitedutilities.lib.effects.GreekFireEffect;
import site.hellishmods.reignitedutilities.lib.effects.PurgingEffect;

public class Effects {
    public static RegistryObject<Effect> doom;
    public static RegistryObject<Effect> gravity;
    public static RegistryObject<Effect> greekFire;
    public static RegistryObject<Effect> fizzyLifting;
    public static RegistryObject<Effect> purging;

    public static void init() {
        doom = reignitedutilities.EFFECTS.register("doom", () -> new DoomEffect(EffectType.HARMFUL, 0x330000));
        gravity = reignitedutilities.EFFECTS.register("gravity", () -> new GravityEffect(EffectType.HARMFUL, 0x000000));
        greekFire = reignitedutilities.EFFECTS.register("greek_fire", () -> new GreekFireEffect(EffectType.HARMFUL, 0xff4400));
        fizzyLifting = reignitedutilities.EFFECTS.register("fizzy_lifting", () -> new FizzyLiftingEffect(EffectType.HARMFUL, 0xbbffbb));
        purging = reignitedutilities.EFFECTS.register("purging", () -> new PurgingEffect(EffectType.HARMFUL, 0xff8800));
    }
}
