package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityBuckNigerianDwarf extends EntityBuckBase
{

	public EntityBuckNigerianDwarf(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.NIGERIAN_DWARF;
		this.setSize(1.2F, 1.2F); 
		this.width = 1.2F;
		this.height = 1.2F;
		this.width = 1.2F;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 2697513;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 8343350;
	}

}
