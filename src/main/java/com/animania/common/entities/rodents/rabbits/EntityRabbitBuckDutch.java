package com.animania.common.entities.rodents.rabbits;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityRabbitBuckDutch extends EntityRabbitBuckBase
{

	public EntityRabbitBuckDutch(World worldIn)
	{
		super(worldIn);
		this.rabbitType = RabbitType.DUTCH;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 16777215;
	}
}