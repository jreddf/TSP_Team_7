package net.tsp7.rogue.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ModAnimations {
    public static final Animation walk = Animation.Builder.create(1.0F).looping()
        .addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
                new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR)
        ))
        .addBoneAnimation("arm0", new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(17.596F, 6.4007F, 4.646F), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5F, AnimationHelper.createRotationalVector(-24.7867F, -1.2837F, -0.0588F), Transformation.Interpolations.LINEAR),
                new Keyframe(1.0F, AnimationHelper.createRotationalVector(17.596F, 6.4007F, 4.646F), Transformation.Interpolations.LINEAR)
        ))
        .addBoneAnimation("arm1", new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(-22.5901F, 5.5751F, 0.391F), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5F, AnimationHelper.createRotationalVector(14.7601F, -2.313F, 0.8841F), Transformation.Interpolations.LINEAR),
                new Keyframe(1.0F, AnimationHelper.createRotationalVector(-22.5901F, 5.5751F, 0.391F), Transformation.Interpolations.LINEAR)
        ))
        .addBoneAnimation("leg0", new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5157F, 2.3842F, -0.7522F), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5F, AnimationHelper.createRotationalVector(17.4843F, 2.3842F, -0.7522F), Transformation.Interpolations.LINEAR),
                new Keyframe(1.0F, AnimationHelper.createRotationalVector(-17.5157F, 2.3842F, -0.7522F), Transformation.Interpolations.LINEAR)
        ))
        .addBoneAnimation("leg1", new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(12.5115F, 2.4407F, 0.5414F), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5F, AnimationHelper.createRotationalVector(-14.9746F, 0.0257F, 1.1878F), Transformation.Interpolations.LINEAR),
                new Keyframe(1.0F, AnimationHelper.createRotationalVector(12.5115F, 2.4407F, 0.5414F), Transformation.Interpolations.LINEAR)
        ))
        .build();

    public static final Animation idle = Animation.Builder.create(2.0F).looping()
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -2.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(2.5024F, -2.4976F, -0.1091F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.1089F, -2.3861F, -2.6089F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(2.5024F, -2.4976F, -0.1091F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm0", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(2.6089F, 2.3861F, 2.6089F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm1", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg0", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg1", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();

    public static final Animation attack = Animation.Builder.create(0.75F)
            .addBoneAnimation("Head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(2.5024F, -2.4976F, -0.1091F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm0", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(17.596F, 6.4007F, 4.646F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-74.904F, 6.4007F, 4.646F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm1", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-22.5901F, 5.5751F, 0.391F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-67.7037F, 7.8824F, -0.5782F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg0", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-17.5157F, 2.3842F, -0.7522F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg1", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(12.5115F, 2.4407F, 0.5414F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();
    }

