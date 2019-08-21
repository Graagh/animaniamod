package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityRabbitDoeNewZealand extends EntityRabbitDoeBase
{

	public EntityRabbitDoeNewZealand(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.NEW_ZEALAND;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16513529;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14211031;
	}
	
}