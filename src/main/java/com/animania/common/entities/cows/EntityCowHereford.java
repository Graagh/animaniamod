package com.animania.common.entities.cows;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityCowHereford extends EntityCowBase
{

	public EntityCowHereford(World world)
	{
		super(world);
		this.cowType = CowType.HEREFORD;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 4461056;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 15987699;
	}

}