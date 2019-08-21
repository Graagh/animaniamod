package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBullLonghorn extends EntityBullBase
{

	public EntityBullLonghorn(World world)
	{
		super(world);
		this.cowType = CowType.LONGHORN;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16763795;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 11227168;
	}

}