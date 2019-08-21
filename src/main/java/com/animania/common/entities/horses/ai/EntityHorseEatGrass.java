package com.animania.common.entities.horses.ai;

import java.util.List;

import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityHorseEatGrass extends EntityAIBase
{
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityAnimaniaHorse grassEaterEntity;
	private final World entityWorld;
	int eatingGrassTimer;

	public EntityHorseEatGrass(EntityLiving grassEaterEntityIn)
	{
		this.grassEaterEntity = (EntityAnimaniaHorse) grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.setMutexBits(7);
	}

	public boolean shouldExecute()
	{
		
		if (!grassEaterEntity.world.isDaytime()) {
			return false;
		}
		
		if (this.grassEaterEntity.isBeingRidden()) {
			return false;
		}
		
		if (this.grassEaterEntity.getFed())
		{
			return false;
		}
		
		List carts = AnimaniaHelper.getCartsInRange(EntityCart.class, 3, entityWorld, this.grassEaterEntity);
		if (!carts.isEmpty()) {
			EntityCart cart = (EntityCart) carts.get(0);
			if (cart.pulled && cart.puller == this.grassEaterEntity) {
				return false;
			}
		}
		
		List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, entityWorld, this.grassEaterEntity);
		if (!wagons.isEmpty()) {
			EntityWagon wagon = (EntityWagon) wagons.get(0);
			if (wagon.pulled && wagon.puller == this.grassEaterEntity) {
				return false;
			}
		}
		
		
		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 150) != 0)
		{
			return false;
		}
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true : (this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS);
		}
	}

	public void startExecuting()
	{
		this.eatingGrassTimer = 100;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
		this.grassEaterEntity.getNavigator().clearPath();
	}

	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	public boolean shouldContinueExecuting()
	{

		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)))
			{

				this.entityWorld.destroyBlock(blockpos, false);

				if (grassEaterEntity instanceof EntityAnimaniaHorse) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)grassEaterEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 
				this.grassEaterEntity.eatGrassBonus();
			}

			else

			{
				BlockPos blockpos1 = blockpos.down();

				if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS)
				{

					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
					if (AnimaniaConfig.gameRules.plantsRemovedAfterEating) {
						this.entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);
					}

					if (grassEaterEntity instanceof EntityAnimaniaHorse) {
						EntityAnimaniaHorse ech = (EntityAnimaniaHorse)grassEaterEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} 
					this.grassEaterEntity.eatGrassBonus();
				}
			}
		}
	}
}