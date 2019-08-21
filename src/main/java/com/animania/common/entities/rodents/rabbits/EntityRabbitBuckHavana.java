package com.animania.common.entities.rodents.rabbits;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitBuckHavana extends EntityRabbitBuckBase
{

	public EntityRabbitBuckHavana(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.HAVANA;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4079166;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}
}