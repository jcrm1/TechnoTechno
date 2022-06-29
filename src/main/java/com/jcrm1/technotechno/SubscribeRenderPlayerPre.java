package com.jcrm1.technotechno;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SubscribeRenderPlayerPre {
	private TechnoTechno mod;
	public SubscribeRenderPlayerPre(TechnoTechno mod) {
		this.mod = mod;
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPreRenderPlayer(RenderPlayerEvent.Pre event) {
		if (!mod.isFullyLoaded()) {
			TechnoTechno.makeTechnoblade(Minecraft.getMinecraft().thePlayer);
			mod.markAsFullyLoaded();
		}
		if (event.entityPlayer instanceof AbstractClientPlayer) {
			TechnoTechno.makeTechnoblade((AbstractClientPlayer) event.entityPlayer);
		}
	}
}
