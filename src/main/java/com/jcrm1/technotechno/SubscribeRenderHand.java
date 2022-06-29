package com.jcrm1.technotechno;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SubscribeRenderHand {
	@SubscribeEvent(priority = EventPriority.LOWEST) 
	public void onRenderHand(RenderHandEvent event) {
		try {
			TechnoTechno.makeTechnoblade(Minecraft.getMinecraft().thePlayer);
		} catch (NullPointerException e) {
			
		}
	}
}
