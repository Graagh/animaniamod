package com.animania.addons.catsdogs.common.entity.cats;

import net.minecraft.world.World;

public class EntityTomRagdoll extends EntityTomBase
{
	public EntityTomRagdoll(World worldIn)
	{
		super(worldIn);
		this.type = CatType.RAGDOLL;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 13948116;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 8741209;
	}
}
