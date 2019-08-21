package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockTaupe extends EntityPeacockBase
{

	public EntityPeacockTaupe(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.TAUPE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe_blink.png");
		this.lidCol = 0xA7988E;

	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12427148;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 7102038;
	}
}