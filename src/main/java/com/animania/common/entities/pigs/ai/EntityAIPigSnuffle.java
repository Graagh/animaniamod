package com.animania.common.entities.pigs.ai;

import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.ItemHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityAIPigSnuffle extends EntityAIBase
{
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityAnimaniaPig grassEaterEntity;
	private final World entityWorld;
	int eatingGrassTimer;

	public EntityAIPigSnuffle(EntityAnimaniaPig grassEaterEntityIn)
	{
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute()
	{

		Block poschk = this.grassEaterEntity.world.getBlockState(this.grassEaterEntity.getPosition().down()).getBlock();
		boolean isMuddy = false;
		if (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud"))
		{
			isMuddy = true;
		}
		if (isMuddy)
		{
			return false;
		}

		if (!this.grassEaterEntity.world.isDaytime() || this.grassEaterEntity.getSleeping())
		{
			return false;
		}

		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
			return false;
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return EntityAIPigSnuffle.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true : this.entityWorld.getBlockState(blockpos.down()).getBlock() != Blocks.WATER && this.entityWorld.getBlockState(blockpos.down()).getBlock() != Blocks.LAVA;
		}
	}

	@Override
	public void startExecuting()
	{
		this.eatingGrassTimer = 160;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigator().clearPath();
	}

	@Override
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (EntityAIPigSnuffle.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)))
			{

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
				{
					this.entityWorld.destroyBlock(blockpos, false);
				}

				this.grassEaterEntity.eatGrassBonus();

				if (grassEaterEntity instanceof EntityAnimaniaPig)
				{
					EntityAnimaniaPig ech = (EntityAnimaniaPig) grassEaterEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				}

			}
			else
			{
				BlockPos blockpos1 = blockpos.down();
				Block chkblock = this.entityWorld.getBlockState(blockpos1).getBlock();

				Biome biomegenbase = this.entityWorld.getBiome(blockpos1);

				if (BiomeDictionary.hasType(biomegenbase, Type.FOREST) && !this.grassEaterEntity.isChild() && this.grassEaterEntity.getLeashed() && this.grassEaterEntity.getLeashHolder() instanceof EntityPlayer)
				{
					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
					ItemHelper.spawnItem(entityWorld, blockpos, ItemHandler.truffle);
				}

				this.grassEaterEntity.eatGrassBonus();

			}
		}
	}
}
