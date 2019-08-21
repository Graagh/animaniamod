package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityTomAsiatic extends EntityTomBase
{
	public EntityTomAsiatic(World worldIn)
	{
		super(worldIn);
		this.type = CatType.ASIATIC;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0x7C6450;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0x383838;
	}
}
