package net.tsp7.rogue.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;

public class ModModelLayers {
    public static final EntityModelLayer EVIL_GOLEM =
            new EntityModelLayer(new Identifier(Rogue.MOD_ID, "evil_golem"), "main");
}
