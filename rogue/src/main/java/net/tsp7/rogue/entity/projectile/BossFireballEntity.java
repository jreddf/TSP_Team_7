package net.tsp7.rogue.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class BossFireballEntity extends SmallFireballEntity {
    public BossFireballEntity(EntityType<? extends SmallFireballEntity> type, World world) {
        super(type, world);
    }

    public BossFireballEntity(World world, LivingEntity owner, double x, double y, double z) {
        super(world, owner, x, y, z); // ✅ matches: (World, LivingEntity, double, double, double)
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        super.onEntityHit(hitResult);

        Entity target = hitResult.getEntity();
        Entity owner = this.getOwner();

        if (owner instanceof LivingEntity) {
            target.damage(this.getDamageSources().mobProjectile(this, (LivingEntity) owner), 30.0F); // custom damage
        } else {
            target.damage(this.getDamageSources().magic(), 30.0F);
        }

        // Optional: set on fire
        target.setOnFireFor(3);
    }
}
