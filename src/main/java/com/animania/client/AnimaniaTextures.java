package com.animania.client;

import javax.annotation.Nonnull;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.common.blocks.IMetaBlockName;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemEntityEggAnimated;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class AnimaniaTextures
{

	public static void registerTextures()
	{

		// Items
		register(ItemHandler.hamsterFood);
		register(ItemHandler.truffle);
		register(ItemHandler.brownEgg);
		register(ItemHandler.peacockEggBlue);
		register(ItemHandler.peacockEggWhite);
		register(ItemHandler.carvingKnife);
		register(ItemHandler.salt);
		register(ItemHandler.cheeseMold);
		register(ItemHandler.cheeseWedgeFriesian);
		register(ItemHandler.cheeseWedgeHolstein);
		register(ItemHandler.cheeseWedgeJersey);
		register(ItemHandler.cheeseWedgeGoat);
		register(ItemHandler.cheeseWedgeSheep);
		register(ItemHandler.truffleSoup);
		register(ItemHandler.chocolateTruffle);
		register(ItemHandler.plainOmelette);
		register(ItemHandler.cheeseOmelette);
		register(ItemHandler.baconOmelette);
		register(ItemHandler.truffleOmelette);
		register(ItemHandler.ultimateOmelette);
		register(ItemHandler.peacockFeatherBlue);
		register(ItemHandler.peacockFeatherWhite);
		register(ItemHandler.peacockFeatherCharcoal);
		register(ItemHandler.peacockFeatherOpal);
		register(ItemHandler.peacockFeatherPeach);
		register(ItemHandler.peacockFeatherPurple);
		register(ItemHandler.peacockFeatherTaupe);
		register(ItemHandler.ridingCrop);
		register(ItemHandler.hamsterBallClear);
		register(ItemHandler.wheel);
		register(ItemHandler.milkBottle);
		register(ItemHandler.honeyJar);
		register(ItemHandler.animaniaManual);

		registerColored(ItemHandler.hamsterBallColored, "hamster_ball");


		// Horse
		register(ItemHandler.rawHorse);
		register(ItemHandler.cookedHorse);

		// Beef Generics
		register(ItemHandler.rawPrimeBeef);
		register(ItemHandler.cookedPrimeBeef);
		register(ItemHandler.rawPrimeSteak);
		register(ItemHandler.cookedPrimeSteak);


		// Pork Generics
		register(ItemHandler.rawPrimePork);
		register(ItemHandler.cookedPrimePork);
		register(ItemHandler.rawPrimeBacon);
		register(ItemHandler.cookedPrimeBacon);


		// Chicken Generics
		register(ItemHandler.rawPrimeChicken);
		register(ItemHandler.cookedPrimeChicken);

		// Frogs
		register(ItemHandler.rawFrogLegs);
		register(ItemHandler.cookedFrogLegs);

		// Goats
		register(ItemHandler.rawChevon);
		register(ItemHandler.cookedChevon);
		register(ItemHandler.rawPrimeChevon);
		register(ItemHandler.cookedPrimeChevon);

		// Goats
		register(ItemHandler.rawPeacock);
		register(ItemHandler.cookedPeacock);
		register(ItemHandler.rawPrimePeacock);
		register(ItemHandler.cookedPrimePeacock);

		// Sheep
		register(ItemHandler.rawPrimeMutton);
		register(ItemHandler.cookedPrimeMutton);

		// Rabbit
		register(ItemHandler.rawPrimeRabbit);
		register(ItemHandler.cookedPrimeRabbit);

		// EGGS
		registerEntityEggs();
		register(ItemHandler.entityeggrandomcow);
		register(ItemHandler.entityeggrandomchicken);
		register(ItemHandler.entityeggrandompig);
		register(ItemHandler.entityeggrandomgoat);
		register(ItemHandler.entityeggrandompeacock);
		register(ItemHandler.entityeggdartfrog);
		register(ItemHandler.entityeggrandomanimal);
		register(ItemHandler.entityeggrandomrabbit);
		register(ItemHandler.entityeggrandomsheep);

		// Blocks
		register(Item.getItemFromBlock(BlockHandler.blockMud));
		register(Item.getItemFromBlock(BlockHandler.blockTrough));
		register(Item.getItemFromBlock(BlockHandler.blockNest));
		register(Item.getItemFromBlock(BlockHandler.blockStraw));
		register(Item.getItemFromBlock(BlockHandler.blockHamsterWheel));
		register(Item.getItemFromBlock(BlockHandler.blockCheeseFriesian));
		register(Item.getItemFromBlock(BlockHandler.blockCheeseHolstein));
		register(Item.getItemFromBlock(BlockHandler.blockCheeseJersey));
		register(Item.getItemFromBlock(BlockHandler.blockCheeseGoat));
		register(Item.getItemFromBlock(BlockHandler.blockCheeseSheep));
		register(Item.getItemFromBlock(BlockHandler.blockSaltLick));
		register(Item.getItemFromBlock(BlockHandler.blockHive));
		register(Item.getItemFromBlock(BlockHandler.blockWildHive));
		regSpecial(BlockHandler.blockAnimaniaWool);
		if (!AnimaniaConfig.gameRules.disableRollingVehicles)
		{
			register(ItemHandler.cart);
			register(ItemHandler.wagon);
			register(ItemHandler.tiller);
		}

		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockSlop, "slop");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkFriesian, "milk_friesian");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkHolstein, "milk_holstein");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkJersey, "milk_jersey");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkGoat, "milk_goat");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockMilkSheep, "milk_sheep");
		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockHoney, "honey");

	}

	/**
	 * Registers Render for an Item
	 *
	 * @param item
	 */
	public static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	public static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	public static void regSpecial(Block block)
	{
		NonNullList<ItemStack> list = NonNullList.create();

		block.getSubBlocks(Animania.TabAnimaniaResources, list);

		for (int i = 0; i < list.size(); i++)
		{
			ItemStack stack = list.get(i);

			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), stack.getMetadata(), new ModelResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack), "inventory"));
			ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack)));

		}

	}

	public static void registerEntityEggs()
	{
		for (Item item : ItemHandler.entityEggList)
		{
			if (item instanceof ItemEntityEgg && !(item instanceof ItemEntityEggAnimated))
			{
				AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
				EntityGender gender = animal.getGender();

				if (gender != EntityGender.RANDOM)
				{

					switch (gender)
					{
					case MALE:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_male", "inventory"));
						break;
					case FEMALE:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_female", "inventory"));
						break;
					default:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_genderless", "inventory"));
						break;
					}
				}

			}
		}
	}

}