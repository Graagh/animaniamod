package com.animania.client.render.pigs;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelHog;
import com.animania.common.entities.pigs.EntityHogLargeBlack;

@SideOnly(Side.CLIENT)
public class LayerMudHogLargeBlack implements LayerRenderer<EntityHogLargeBlack>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("animania:textures/entity/pigs/pig_muddy.png");
	private final RenderHogLargeBlack pigRenderer;
	private final ModelHog pigModel = new ModelHog(0.5F);

	public LayerMudHogLargeBlack(RenderHogLargeBlack pigRendererIn)
	{
		this.pigRenderer = pigRendererIn;
	}

	@Override
	public void doRenderLayer(EntityHogLargeBlack entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		if (entitylivingbaseIn.getMuddy()) {

			float splashTimer = entitylivingbaseIn.getSplashTimer();

			if (splashTimer <= 0) {
				GL11.glScalef(1.01F, 1.01F, 1.01F);
				this.pigRenderer.bindTexture(TEXTURE);
				GlStateManager.enableBlend();
				this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				GlStateManager.depthMask(true);
				GlStateManager.disableBlend();
			}
		} else if (entitylivingbaseIn.getMudTimer() > 0 ) {

			float mudTimer = entitylivingbaseIn.getMudTimer();
			GL11.glScalef(1.01F, 1.01F, 1.01F);
			this.pigRenderer.bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
			this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.disableBlend();

		}
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return true;
	}
}