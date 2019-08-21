package com.animania.addons.catsdogs.client.render.dogs;

import com.animania.addons.catsdogs.client.models.dogs.ModelFox;
import com.animania.addons.catsdogs.common.entity.dogs.EntityAnimaniaDog;
import com.animania.api.interfaces.IChild;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFox<T extends EntityAnimaniaDog> extends RenderLiving<T>
{
	private final ResourceLocation texture = new ResourceLocation("animania:textures/entity/dogs/fox.png");
	private final ResourceLocation texture_razz = new ResourceLocation("animania:textures/entity/dogs/razz_fox.png");
	private final ResourceLocation blink = new ResourceLocation("animania:textures/entity/dogs/blink_fox.png");
	private final LayerBlinking blinking;

	public RenderFox(RenderManager rm)
	{
		super(rm, new ModelFox(), 0.5F);

		this.addLayer(blinking = new LayerBlinking(this, blink, -5415620, true));
	}

	protected void preRenderScale(EntityAnimaniaDog entity, float f)
	{
		if (entity instanceof IChild)
		{
			float age = ((IChild) entity).getEntityAge();
			GlStateManager.scale(1 + age, 1 + age, 1 + age);
		}
		else
			GlStateManager.scale(1, 1, 1);

		EntityAnimaniaDog entityCat = (EntityAnimaniaDog) entity;
		if (entityCat.getSleeping())
		{
			this.shadowSize = 0;
			float sleepTimer = entityCat.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.45F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			this.shadowSize = 0.5F;
			entityCat.setSleeping(false);
			entityCat.setSleepTimer(0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if(entity.getName().equalsIgnoreCase("razz"))
		{
			return texture_razz;
		}
		
		return texture;
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{	
		if(entityliving.getName().equalsIgnoreCase("razz"))
		{
			blinking.setColors(0xA81348, 0xA81348);
		}
		
		this.preRenderScale(entityliving, f);
	}

	public static class Factory<T extends EntityAnimaniaDog> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderFox(manager);
		}

	}
}
