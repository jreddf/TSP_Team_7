package net.tsp7.rogue.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tsp7.rogue.item.entity.FireballEntity;

import java.util.List;

public class Wand extends Item {
    public Wand(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            FireballEntity fireball = new FireballEntity(user.getWorld(), user);
            fireball.setPosition(user.getX(), user.getEyeY(), user.getZ());
            fireball.setVelocity(user.getRotationVec(1.0F).multiply(2.0));
            world.spawnEntity(fireball);
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH,
                    SoundCategory.PLAYERS,
                    0.3f,
                    1f
            );
        }

        user.getItemCooldownManager().set(this, 10); // 0.5 second cooldown

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        stack.addEnchantment(Enchantments.LOOTING, 5);
        return stack;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasEnchantments();
    }
}
