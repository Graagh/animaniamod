package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.config.AnimaniaConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class WailaEntityAnimalProviderBase implements IWailaEntityProvider
{

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{

		NBTTagCompound comp = accessor.getNBTData();
		boolean fed = accessor.getNBTData().getBoolean("Fed");
		boolean watered = accessor.getNBTData().getBoolean("Watered");
		boolean sleeping = accessor.getNBTData().getBoolean("Sleeping");

		if (entity instanceof EntityAnimaniaChicken)
		{
			EntityAnimaniaChicken tempEnt = (EntityAnimaniaChicken) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaCow)
		{
			EntityAnimaniaCow tempEnt = (EntityAnimaniaCow) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaGoat)
		{
			EntityAnimaniaGoat tempEnt = (EntityAnimaniaGoat) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaHorse)
		{
			EntityAnimaniaHorse tempEnt = (EntityAnimaniaHorse) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaPeacock)
		{
			EntityAnimaniaPeacock tempEnt = (EntityAnimaniaPeacock) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaPig)
		{
			EntityAnimaniaPig tempEnt = (EntityAnimaniaPig) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaRabbit)
		{
			EntityAnimaniaRabbit tempEnt = (EntityAnimaniaRabbit) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityAnimaniaSheep)
		{
			EntityAnimaniaSheep tempEnt = (EntityAnimaniaSheep) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityHedgehogBase)
		{
			EntityHedgehogBase tempEnt = (EntityHedgehogBase) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityFerretBase)
		{
			EntityFerretBase tempEnt = (EntityFerretBase) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		} else if (entity instanceof EntityHamster)
		{
			EntityHamster tempEnt = (EntityHamster) entity;
			fed = tempEnt.getFed();
			watered = tempEnt.getWatered();
			sleeping = tempEnt.getSleeping();
		}

		if (fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.fed"));

		if (fed && !watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.thirsty"));

		if (!fed && watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.hungry"));

		if (!fed && !watered && !AnimaniaConfig.gameRules.ambianceMode)
			currenttip.add(I18n.translateToLocal("text.waila.hungry") + ", " + I18n.translateToLocal("text.waila.thirsty"));

		if (sleeping)
			currenttip.add(I18n.translateToLocal("text.waila.sleeping"));

		if (accessor.getPlayer().isSneaking())
			if (entity instanceof ISterilizable)
			{
				if (((ISterilizable) entity).getSterilized())
					currenttip.add(I18n.translateToLocal("text.waila.sterilized"));
			}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, Entity ent, NBTTagCompound tag, World world)
	{
		NBTTagCompound comp = ent.getEntityData();

		tag.setBoolean("Fed", comp.getBoolean("Fed"));
		tag.setBoolean("Watered", comp.getBoolean("Watered"));

		return tag;
	}

}
