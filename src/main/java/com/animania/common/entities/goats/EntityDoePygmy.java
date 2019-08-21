package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoePygmy extends EntityDoeBase
{

	public EntityDoePygmy(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.PYGMY;

	}

	@Override
	public int getPrimaryEggColor()
	{
		return 9475221;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 4145731;
	}
}
