package com.github.doughsay.Dynmap2CraftIRC3;

import java.util.List;

import org.dynmap.DynmapAPI;
import org.dynmap.DynmapCommonAPI;

import com.ensifera.animosity.craftirc.EndPoint;
import com.ensifera.animosity.craftirc.RelayedMessage;
import com.ensifera.animosity.craftirc.SecuredEndPoint.Security;

public class DynmapPoint implements EndPoint {

	private DynmapCommonAPI dynmap;

	DynmapPoint(DynmapAPI dynmap) {
		this.dynmap = (DynmapCommonAPI)dynmap;
	}

	public Type getType() {
		return EndPoint.Type.PLAIN;
	}

	public Security getSecurity() {
		return Security.UNSECURED;
	}

	public void messageIn(RelayedMessage msg) {
		dynmap.sendBroadcastToWeb(null, msg.getMessage(this));
	}

	public boolean userMessageIn(String username, RelayedMessage msg) {
		return false;
	}

	public boolean adminMessageIn(RelayedMessage msg) {
		return false;
	}

	public List<String> listUsers() {
		return null;
	}

	public List<String> listDisplayUsers() {
		return null;
	}
}
