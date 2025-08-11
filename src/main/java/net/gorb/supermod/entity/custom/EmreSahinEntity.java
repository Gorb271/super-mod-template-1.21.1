package net.gorb.supermod.entity.custom;

import net.gorb.supermod.entity.ModEntities;
import net.gorb.supermod.entity.custom.ai.goal.PickUpItemGoal;
import net.gorb.supermod.item.ModItems;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class EmreSahinEntity extends AnimalEntity {

    public final AnimationState idleAnimationState =  new AnimationState();
    private int idleAnimationTimeout = 0;

    public EmreSahinEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super( entityType, world);
    }
    @Override
    protected void initGoals() {
        // fox if this doesent work
        this.goalSelector.add(0,new PickUpItemGoal(this, ModItems.BOWLINGBALL, 1d));
        this.goalSelector.add(1, new TemptGoal(this, 1d, Ingredient.ofItems(ModItems.BOWLINGBALL), false));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
    }
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1d)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20D);
    }
    private void setUpAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else{
            --this.idleAnimationTimeout;
        }
    }
    @Override
    public  void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setUpAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.BOWLINGBALL);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.EMRE_SAHIN_ENTITY.create(world);
    }
}
