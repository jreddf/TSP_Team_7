package net.tsp7.rogue.item.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.tsp7.rogue.entity.ModEntities;

public class FireballEntity extends ThrownItemEntity{


    public FireballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    public FireballEntity(World world, LivingEntity owner) {
        super(ModEntities.FIREBALL, owner, world);
        this.setNoGravity(true);
    }

    public FireballEntity(World world, double x, double y, double z) {
        super(ModEntities.FIREBALL, x, y, z, world);
        this.setNoGravity(true);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.EXPLOSION: new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity target= entityHitResult.getEntity();
        if (this.getOwner() instanceof LivingEntity owner) {
            DamageSource damageSource = target.getWorld().getDamageSources().thrown(this, owner);
            target.damage(damageSource, 10.0f);
            target.setOnFireFor(5);
        }

        this.discard();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.getWorld().createExplosion(this, this.getX(), this.getY() + (double)(this.getHeight() / 16.0F), this.getZ(), 2.0F, World.ExplosionSourceType.NONE);
        this.discard();
    }
}
