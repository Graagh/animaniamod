package com.animania.render.cows;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.entities.chickens.EntityChickLeghorn;
import com.animania.entities.cows.EntityCalfAngus;
import com.animania.entities.cows.EntityCowHereford;
import com.animania.models.ModelCalf;

@SideOnly(Side.CLIENT)
public class RenderCalfAngus extends RenderLiving<EntityCalfAngus>
{
    private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/calf_angus.png");
    private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/calf_angus_blink.png");
    Random rand = new Random();
    
    public RenderCalfAngus(RenderManager rm)
   	{
   		super(rm, new ModelCalf(), 0.5F);
   	}

    protected ResourceLocation getCowTextures(EntityCalfAngus par1EntityCow)
    {
        return cowTextures;
    }
    
    protected ResourceLocation getCowTexturesBlink(EntityCalfAngus par1EntityCow)
    {
        return cowTexturesBlink;
    }
    
    @Override
	protected void preRenderCallback(EntityCalfAngus entityliving, float f)
	{
		preRenderScale((EntityCalfAngus)entityliving, f);
	}

	protected void preRenderScale(EntityCalfAngus entity, float f)
	{
		
		float age = entity.getEntityAge();
		
		GL11.glScalef(1.0F + age, 1.0F + age, 1.0F + age); 
		

	}

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	protected ResourceLocation getEntityTexture(EntityCalfAngus par1Entity)
	{
		EntityCalfAngus entity = (EntityCalfAngus)par1Entity;

		int blinkTimer = entity.blinkTimer;

		if (blinkTimer < 7 && blinkTimer >= 0) {
			return this.getCowTexturesBlink(entity);
		} else {
			return this.getCowTextures(entity);
		}

	}
}