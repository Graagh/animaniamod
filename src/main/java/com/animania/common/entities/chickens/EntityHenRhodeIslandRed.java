package com.animania.common.entities.chickens;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHenRhodeIslandRed extends EntityHenBase
{

	public EntityHenRhodeIslandRed(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.RHODE_ISLAND_RED;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_red.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
		this.lidCol = 0x9F4931;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 13668724;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12480342;
	}
}