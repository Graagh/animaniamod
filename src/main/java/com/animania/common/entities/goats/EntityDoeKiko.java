package com.animania.common.entities.goats;

import com.animania.common.handler.ItemHandler;

import net.minecraft.world.World;

public class EntityDoeKiko extends EntityDoeBase
{

	public EntityDoeKiko(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.KIKO;
		this.setSize(1.3F, 1.0F); 
		this.width = 1.3F;
		this.height = 1.0F;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8802872;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3549475;
	}

}
