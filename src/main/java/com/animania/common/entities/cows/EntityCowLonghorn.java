package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowLonghorn extends EntityCowBase
{

	public EntityCowLonghorn(World world)
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