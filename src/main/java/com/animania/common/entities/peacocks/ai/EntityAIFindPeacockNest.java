package com.animania.common.entities.peacocks.ai;

import java.util.List;

import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.peacocks.EntityPeafowlBase;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.tileentities.TileEntityNest;
import com.animania.common.tileentities.TileEntityNest.NestContent;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIFindPeacockNest extends EntityAIBase
{
	private final EntityAnimaniaPeacock temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindPeacockNest(EntityAnimaniaPeacock temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	public boolean shouldExecute()
	{

		delayTemptCounter++;
		if (this.delayTemptCounter <= AnimaniaConfig.gameRules.ticksBetweenAIFirings) 
		{
			return false;
		}
		else if (delayTemptCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) 
		{

			if (!temptedEntity.world.isDaytime() || temptedEntity.getSleeping()) {
				this.delayTemptCounter = 0;
				return false;
			}

			if (temptedEntity instanceof EntityPeafowlBase)
			{
				EntityPeafowlBase entity = (EntityPeafowlBase) temptedEntity;
				if (!entity.getWatered() || !entity.getFed())
				{
					this.delayTemptCounter = 0;
					return false;
				}
			}
			
			if (this.temptedEntity.getRNG().nextInt(100) == 0)
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.temptedEntity, 20, 4);
				if (vec3d != null) {
					this.delayTemptCounter = 0;
					this.resetTask();
					this.temptedEntity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
				}
				return false;
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

			if (poschk == BlockHandler.blockNest)
			{
				TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(currentpos);

				if (te.itemHandler.getStackInSlot(0).getCount() >= 3)
				{
					return false;
				}

				if (temptedEntity instanceof EntityPeafowlBlue)
				{
					EntityPeafowlBlue entity = (EntityPeafowlBlue) temptedEntity;
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.PEACOCK_BLUE) && !entity.getLaid())
					{
						if (te.getNestContent() == NestContent.PEACOCK_BLUE ? entity.type == te.birdType : true)
							if (te.insertItem(new ItemStack(ItemHandler.peacockEggBlue)))
							{
								entity.setLaid(true);
								te.birdType = entity.type;
								this.delayTemptCounter = 0;
								te.markDirty();
							}
					}
				}
				else if (temptedEntity instanceof EntityPeafowlBase)
				{
					EntityPeafowlBase entity = (EntityPeafowlBase) temptedEntity;
					if (te != null && (te.getNestContent() == NestContent.EMPTY || te.getNestContent() == NestContent.PEACOCK_WHITE) && !entity.getLaid())
					{
						if (te.getNestContent() == NestContent.PEACOCK_WHITE ? entity.type == te.birdType : true)
							if (te.insertItem(new ItemStack(ItemHandler.peacockEggWhite)))
							{
								entity.setLaid(true);
								te.birdType = entity.type;
								this.delayTemptCounter = 0;
								te.markDirty();
							}
					}
				}
				this.delayTemptCounter = 0;
				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean nestFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest)
						{

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.PEACOCK_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityPeafowlBlue && (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
								else if (temptedEntity instanceof EntityPeafowlBase && (nestType == NestContent.PEACOCK_WHITE ? ((EntityPeafowlBase) temptedEntity).type == te.birdType : nestType == NestContent.EMPTY))
								{
									nestFound = true;
									return true;
								}
							}
						}
					}
				}
			}

			if (!nestFound)
			{
				this.delayTemptCounter = 0;
				return false;
			}
		}
		
		return false;
	}

	public boolean shouldContinueExecuting()
	{
		return !this.temptedEntity.getNavigator().noPath();
	}


	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPath();
		this.isRunning = false;
	}

	public void startExecuting()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
		if (poschk != BlockHandler.blockNest)
		{

			boolean nestFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos nestPos = new BlockPos(x, y, z);

			for (int i = -10; i < 10; i++)
			{
				for (int j = -3; j < 3; j++)
				{
					for (int k = -10; k < 10; k++)
					{

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == BlockHandler.blockNest && !temptedEntity.hasPath())
						{

							TileEntityNest te = (TileEntityNest) temptedEntity.world.getTileEntity(pos);
							NestContent nestType = te.getNestContent();

							if (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.PEACOCK_WHITE || nestType == NestContent.EMPTY)
							{
								if (temptedEntity instanceof EntityPeafowlBlue && (nestType == NestContent.PEACOCK_BLUE || nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
								else if (temptedEntity instanceof EntityPeafowlBase && (nestType == NestContent.PEACOCK_WHITE ? ((EntityPeafowlBase) temptedEntity).type == te.birdType : nestType == NestContent.EMPTY))
								{
									nestFound = true;
								}
							}

							if (nestFound == true)
							{

								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc)
								{

									loc = newloc;

									if (temptedEntity.posX < nestPos.getX())
									{
										BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
											i = i + 1;
										}
									}

									if (temptedEntity.posZ < nestPos.getZ())
									{
										BlockPos nestPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block nestBlockchk = temptedEntity.world.getBlockState(nestPoschk).getBlock();
										if (nestBlockchk == BlockHandler.blockNest)
										{
											k = k + 1;
										}
									}

									nestPos = new BlockPos(x + i, y + j, z + k);

								}
							}
						}
					}
				}
			}

			if (nestFound)
			{

				Block nestBlockchk = temptedEntity.world.getBlockState(nestPos).getBlock();
				List<Entity> nestClear = temptedEntity.world.getEntitiesWithinAABBExcludingEntity(temptedEntity, temptedEntity.getEntityBoundingBox().expand(1,1,1));

				if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty())
				{
					this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
				}
				else
				{
					// this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX(),
					// nestPos.getY(), nestPos.getZ(), this.speed);

				}
			}
		}
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}