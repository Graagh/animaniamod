package com.animania.common.entities.chickens;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRoosterOrpington extends EntityRoosterBase
{

	public EntityRoosterOrpington(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.ORPINGTON;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_golden.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
		this.lidCol = 0xCD902F;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15980429;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13270563;
	}
}