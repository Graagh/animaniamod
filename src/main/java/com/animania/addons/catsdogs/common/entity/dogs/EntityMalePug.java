package com.animania.addons.catsdogs.common.entity.dogs;

import net.minecraft.world.World;

public class EntityMalePug extends EntityMaleDogBase
{

	public EntityMalePug(World world)
	{
		super(world);
		this.type = DogType.PUG;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return -1514529;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return -13026238;
	}
}
