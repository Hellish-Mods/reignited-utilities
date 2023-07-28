package site.hellishmods.reignitedutilities.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import site.hellishmods.reignitedutilities.reignitedutilities;
import site.hellishmods.reignitedutilities.lib.effects.DoomEffect;
import site.hellishmods.reignitedutilities.lib.effects.FizzyLiftingEffect;
import site.hellishmods.reignitedutilities.lib.effects.GravityEffect;
import site.hellishmods.reignitedutilities.lib.effects.GreekFireEffect;
import site.hellishmods.reignitedutilities.lib.effects.LoveEffect;
import site.hellishmods.reignitedutilities.lib.effects.PurgingEffect;
import site.hellishmods.reignitedutilities.lib.effects.RelapseEffect;
import site.hellishmods.reignitedutilities.lib.effects.SecondChanceEffect;

public class Effects {
    public static RegistryObject<Effect> doom;
    public static RegistryObject<Effect> gravity;
    public static RegistryObject<Effect> secondChance;
    public static RegistryObject<Effect> greekFire;
    public static RegistryObject<Effect> fizzyLifting;
    public static RegistryObject<Effect> relapse;
    public static RegistryObject<Effect> love;
    public static RegistryObject<Effect> purging;

    public static void init() {
        doom = reignitedutilities.EFFECTS.register("doom", () -> new DoomEffect(EffectType.HARMFUL, 0x330000));
        gravity = reignitedutilities.EFFECTS.register("gravity", () -> new GravityEffect(EffectType.HARMFUL, 0x000000));
        secondChance = reignitedutilities.EFFECTS.register("second_chance", () -> new SecondChanceEffect(EffectType.BENEFICIAL, 0x22ee22));
        greekFire = reignitedutilities.EFFECTS.register("greek_fire", () -> new GreekFireEffect(EffectType.HARMFUL, 0xff4400));
        fizzyLifting = reignitedutilities.EFFECTS.register("fizzy_lifting", () -> new FizzyLiftingEffect(EffectType.HARMFUL, 0xbbffbb));
        relapse = reignitedutilities.EFFECTS.register("relapse", () -> new RelapseEffect(EffectType.NEUTRAL, 0x2200aa));
        love = reignitedutilities.EFFECTS.register("love", () -> new LoveEffect(EffectType.BENEFICIAL, 0xffdddd));
        purging = reignitedutilities.EFFECTS.register("purging", () -> new PurgingEffect(EffectType.NEUTRAL, 0xff8800));
    }
}
