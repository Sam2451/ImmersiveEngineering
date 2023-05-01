/*
 * BluSunrize
 * Copyright (c) 2023
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.client.render.entity;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.common.entities.illager.Commando;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractIllager.IllagerArmPose;

public class CommandoRenderer extends IllagerRenderer<Commando>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(ImmersiveEngineering.MODID, "textures/entity/illager/commando.png");

	public CommandoRenderer(EntityRendererProvider.Context p_174354_)
	{
		super(p_174354_, new IllagerModel<>(p_174354_.bakeLayer(ModelLayers.PILLAGER))
		{
			@Override
			public void setupAnim(Commando entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
			{
				super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				if(entity.isBlocking())
				{
					ModelPart leftArm = this.root().getChild("left_arm");
					leftArm.xRot = leftArm.xRot*0.5F-0.9424779F;
					leftArm.yRot = ((float)Math.PI/6F);

				}
				if(entity.getArmPose()==IllagerArmPose.NEUTRAL&&entity.isAiming())
				{
					ModelPart rightArm = this.root().getChild("right_arm");
					ModelPart head = this.root().getChild("head");
					rightArm.xRot = -1.39626f+head.xRot;
					rightArm.yRot = -.08726f+head.yRot;
				}
			}
		}, 0.5F);
		this.model.getHat().visible = true;
		this.addLayer(new ItemInHandLayer<>(this, p_174354_.getItemInHandRenderer()));
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getTextureLocation(Commando entity)
	{
		return TEXTURE;
	}
}
