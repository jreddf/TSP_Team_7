package net.tsp7.rogue.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tsp7.rogue.item.entity.FireballEntity;

public class Wand extends Item {
    public Wand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            FireballEntity fireball = new FireballEntity(user.getWorld(), user);
            System.out.println("1");
            fireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
            System.out.println("2");
            fireball.setVelocity(user.getRotationVec(1.0F).multiply(2.0));
            world.spawnEntity(fireball);  // Spawn the fireball in the world
        }

        user.getItemCooldownManager().set(this, 20);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
