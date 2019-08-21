package com.animania.common.entities.pigs;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityHogLargeBlack extends EntityHogBase
{

	public EntityHogLargeBlack(World world)
	{
		super(world);
		this.pigType = PigType.LARGE_BLACK;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8417906;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 5326149;
	}


}