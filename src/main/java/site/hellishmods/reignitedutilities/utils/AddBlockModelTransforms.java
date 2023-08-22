package site.hellishmods.reignitedutilities.utils;

import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;

public class AddBlockModelTransforms {
    public static BlockModelBuilder addTransforms(BlockModelBuilder original) {
        return original.transforms()
            .transform(Perspective.GUI).rotation(30, 225, 0).translation(0, 0, 0).scale(.625f, .625f, .625f).end()
            .transform(Perspective.GROUND).rotation(0, 0, 0).translation(0, 3, 0).scale(.25f, .25f, .25f).end()
            .transform(Perspective.FIXED).rotation(0, 0, 0).translation(0, 0, 0).scale(.5f, .5f, .5f).end()
            .transform(Perspective.THIRDPERSON_RIGHT).rotation(75, 45, 0).translation(0, 2.5f, 0).scale(.375f, .375f, .375f).end()
            .transform(Perspective.FIRSTPERSON_RIGHT).rotation(0, 45, 0).translation(0, 0, 0).scale(.4f, .4f, .4f).end()
            .transform(Perspective.FIRSTPERSON_LEFT).rotation(0, 225, 0).translation(0, 0, 0).scale(.4f, .4f, .4f).end()
        .end();
    }
}
