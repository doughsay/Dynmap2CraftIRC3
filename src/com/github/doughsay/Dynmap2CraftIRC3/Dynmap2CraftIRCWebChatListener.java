package com.github.doughsay.Dynmap2CraftIRC3;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.dynmap.DynmapWebChatEvent;

import com.ensifera.animosity.craftirc.CraftIRC;
import com.ensifera.animosity.craftirc.RelayedMessage;

public class Dynmap2CraftIRCWebChatListener extends CustomEventListener {

	private DynmapPoint dynmapPoint;
	private CraftIRC craftIrc;
	
	public Dynmap2CraftIRCWebChatListener(CraftIRC craftIrc, DynmapPoint dynmapPoint) {
		this.craftIrc = craftIrc;
		this.dynmapPoint = dynmapPoint;
	}

	public void onCustomEvent(Event evt) {
		if(!(evt instanceof DynmapWebChatEvent)) return;
		DynmapWebChatEvent webevt = (DynmapWebChatEvent) evt;
		String msg = webevt.getMessage();
		String sender = webevt.getName();

		RelayedMessage rMsg = craftIrc.newMsg(dynmapPoint, null, "dynmap");
		rMsg.setField("message", msg);
		rMsg.setField("sender", sender);
		rMsg.post();
	}
}
