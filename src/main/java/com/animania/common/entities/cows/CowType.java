package com.animania.common.entities.cows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;

import net.minecraft.world.World;

public enum CowType implements AnimaniaType
{
	ANGUS(EntityBullAngus.class, EntityCowAngus.class, EntityCalfAngus.class, true),
	FRIESIAN(EntityBullFriesian.class, EntityCowFriesian.class, EntityCalfFriesian.class, false),
	HEREFORD(EntityBullHereford.class, EntityCowHereford.class, EntityCalfHereford.class, true),
	HOLSTEIN(EntityBullHolstein.class, EntityCowHolstein.class, EntityCalfHolstein.class, false),
	LONGHORN(EntityBullLonghorn.class, EntityCowLonghorn.class, EntityCalfLonghorn.class, true),
	HIGHLAND(EntityBullHighland.class, EntityCowHighland.class, EntityCalfHighland.class, true),
	JERSEY(EntityBullJersey.class, EntityCowJersey.class, EntityCalfJersey.class, true),
	MOOSHROOM(EntityBullMooshroom.class, EntityCowMooshroom.class, EntityCalfMooshroom.class, false);

	private Class bull;
	private Class cow;
	private Class calf;
	public boolean isPrime;
	
	private CowType(Class bull, Class cow, Class calf, boolean prime)
	{
		this.bull = bull;
		this.cow = cow;
		this.calf = calf;
		this.isPrime = prime;
	}

	@Override
	public EntityBullBase getMale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.bull.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityBullBase bull = null;
		try
		{
			bull = (EntityBullBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return bull;
	}

	@Override
	public EntityCowBase getFemale(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.cow.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityCowBase cow = null;
		try
		{
			cow = (EntityCowBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return cow;	
	}

	@Override
	public EntityCalfBase getChild(World world)
	{
		Constructor<?> constructor = null;
		try
		{
			constructor = this.calf.getConstructor(World.class);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		EntityCalfBase calf = null;
		try
		{
			calf = (EntityCalfBase) constructor.newInstance(world);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
		return calf;	
	}

	public static CowType breed(CowType male, CowType female)
	{
		return Animania.RANDOM.nextBoolean() ? male : female;
	}



}
