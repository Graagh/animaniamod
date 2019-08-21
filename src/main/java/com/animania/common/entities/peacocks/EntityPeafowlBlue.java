package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeafowlBlue extends EntityPeafowlBase
{

	public EntityPeafowlBlue(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.BLUE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_blue.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_blue_blink.png");
		this.lidCol = 0x846F75;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 2446225;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 4361491;
	}
}