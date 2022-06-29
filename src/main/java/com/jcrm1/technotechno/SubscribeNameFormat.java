package com.jcrm1.technotechno;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SubscribeNameFormat {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onNameFormat(PlayerEvent.NameFormat event) {
		event.displayname = TechnoTechno.displayName;
	}
}
