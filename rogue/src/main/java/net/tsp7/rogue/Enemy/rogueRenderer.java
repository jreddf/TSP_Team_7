package net.tsp7.rogue.Enemy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.RogueClient;

public class rogueRenderer extends MobEntityRenderer<roguebaseenemy, roguebaseenemyModel> {

    public rogueRenderer(EntityRendererFactory.Context context)
    {
        super(context, new roguebaseenemyModel(context.getPart(RogueClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(roguebaseenemy entity) {
        return new Identifier("rogue", "textures/entity/fox.png");
    }
}
