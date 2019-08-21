package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.blocks.BlockAnimaniaWool;
import com.animania.common.blocks.BlockCheese;
import com.animania.common.blocks.BlockCheeseMold;
import com.animania.common.blocks.BlockHamsterWheel;
import com.animania.common.blocks.BlockHive;
import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockMud;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockStraw;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.blocks.BlockWildHive;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.common.blocks.fluids.BlockFluidHoney;
import com.animania.common.blocks.fluids.BlockFluidMilk;
import com.animania.common.blocks.fluids.BlockFluidSlop;
import com.animania.common.blocks.fluids.FluidBase;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler
{

	// Blocks
	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;
	public static Block blockStraw;
	public static BlockFluidBase blockSlop;
	public static BlockFluidBase blockMilkHolstein;
	public static BlockFluidBase blockMilkFriesian;
	public static BlockFluidBase blockMilkJersey;
	public static BlockFluidBase blockMilkGoat;
	public static BlockFluidBase blockMilkSheep;
	public static BlockFluidBase blockHoney;
	public static Block blockCheeseHolstein;
	public static Block blockCheeseFriesian;
	public static Block blockCheeseJersey;
	public static Block blockCheeseGoat;
	public static Block blockCheeseSheep;
	public static Block blockAnimaniaWool;
	public static Block blockHive;
	public static Block blockWildHive;

	// TileEntity blocks
	public static Block blockTrough;
	public static Block blockNest;
	public static Block blockCheeseMold;
	public static Block blockHamsterWheel;
	public static Block blockSaltLick;

	// Fluids
	public static Fluid fluidSlop;
	public static Fluid fluidMilkHolstein;
	public static Fluid fluidMilkFriesian;
	public static Fluid fluidMilkJersey;
	public static Fluid fluidMilkGoat;
	public static Fluid fluidMilkSheep;
	public static Fluid fluidHoney;

	// TODO Add own buckets

	// Item Blocks
	public static ItemBlock itemBlockMud;
	public static ItemBlock itemBlockSeeds;
	public static ItemBlock itemBlockTrough;
	public static ItemBlock itemBlockNest;
	public static ItemBlock itemBlockStraw;
	public static ItemBlock itemInvisiblock;

	public static List<Block> blocks = new ArrayList<Block>();

	public static void preInit()
	{

		// BLOCKS
		BlockHandler.blockMud = new BlockMud().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockTrough = new BlockTrough().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockInvisiblock = new BlockInvisiblock().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockNest = new BlockNest().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockSeeds = new BlockSeeds();
		BlockHandler.blockStraw = new BlockStraw();
		BlockHandler.blockHamsterWheel = new BlockHamsterWheel();
		BlockHandler.blockCheeseFriesian = new BlockCheese("cheese_friesian");
		BlockHandler.blockCheeseHolstein = new BlockCheese("cheese_holstein");
		BlockHandler.blockCheeseJersey = new BlockCheese("cheese_jersey");
		BlockHandler.blockCheeseGoat = new BlockCheese("cheese_goat");
		BlockHandler.blockCheeseSheep = new BlockCheese("cheese_sheep");
		ItemHandler.cheeseWheelFriesian = Item.getItemFromBlock(blockCheeseFriesian);
		ItemHandler.cheeseWheelHolstein = Item.getItemFromBlock(blockCheeseHolstein);
		ItemHandler.cheeseWheelJersey = Item.getItemFromBlock(blockCheeseJersey);
		ItemHandler.cheeseWheelGoat = Item.getItemFromBlock(blockCheeseGoat);
		ItemHandler.cheeseWheelSheep = Item.getItemFromBlock(blockCheeseSheep);
		BlockHandler.blockCheeseMold = new BlockCheeseMold();
		ItemHandler.cheeseMold = Item.getItemFromBlock(blockCheeseMold);
		BlockHandler.blockSaltLick = new BlockSaltLick();
		BlockHandler.blockAnimaniaWool = new BlockAnimaniaWool();
		BlockHandler.blockHive = new BlockHive();
		BlockHandler.blockWildHive = new BlockWildHive();

		// Fluids
		BlockHandler.fluidSlop = new FluidBase("slop").setViscosity(7000).setDensity(3000).setEmptySound(SoundEvents.BLOCK_SLIME_PLACE).setFillSound(SoundEvents.BLOCK_SLIME_FALL);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidSlop);
		BlockHandler.blockSlop = new BlockFluidSlop();

		BlockHandler.fluidMilkHolstein = new FluidBase("milk_holstein").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkHolstein);
		BlockHandler.blockMilkHolstein = new BlockFluidMilk(BlockHandler.fluidMilkHolstein, "milk_holstein");

		BlockHandler.fluidMilkFriesian = new FluidBase("milk_friesian").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkFriesian);
		BlockHandler.blockMilkFriesian = new BlockFluidMilk(BlockHandler.fluidMilkFriesian, "milk_friesian");

		BlockHandler.fluidMilkJersey = new FluidBase("milk_jersey").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkJersey);
		BlockHandler.blockMilkJersey = new BlockFluidMilk(BlockHandler.fluidMilkJersey, "milk_jersey");

		BlockHandler.fluidMilkGoat = new FluidBase("milk_goat").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkGoat);
		BlockHandler.blockMilkGoat = new BlockFluidMilk(BlockHandler.fluidMilkGoat, "milk_goat");

		BlockHandler.fluidMilkSheep = new FluidBase("milk_sheep").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkSheep);
		BlockHandler.blockMilkSheep = new BlockFluidMilk(BlockHandler.fluidMilkSheep, "milk_sheep");

		BlockHandler.fluidHoney = new FluidBase("animania_honey").setViscosity(7000).setDensity(3000);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidHoney);
		BlockHandler.blockHoney = new BlockFluidHoney();
		
		// Itemblocks
		BlockHandler.itemBlockMud = new ItemBlock(BlockHandler.blockMud);
		BlockHandler.itemBlockMud.setRegistryName(BlockHandler.blockMud.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlockMud);

		BlockHandler.itemBlockTrough = new ItemBlock(BlockHandler.blockTrough);
		BlockHandler.itemBlockTrough.setRegistryName(BlockHandler.blockTrough.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.itemBlockTrough);

		BlockHandler.itemBlockNest = new ItemBlock(BlockHandler.blockNest);
		BlockHandler.itemBlockNest.setRegistryName(BlockHandler.blockNest.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.itemBlockNest);

		Item item = new ItemBlock(BlockHandler.blockHamsterWheel);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "block_hamster_wheel"));
		ForgeRegistries.ITEMS.register(item);
	}

	@SubscribeEvent
	public void onRegister(RegistryEvent.Register<Block> event)
	{
		for (Block b : blocks)
				event.getRegistry().register(b);
	}
}
