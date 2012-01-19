package com.github.doughsay.Dynmap2CraftIRC3;

import java.util.List;
import java.util.logging.Logger;

import org.dynmap.DynmapAPI;
import org.dynmap.DynmapCommonAPI;

import com.ensifera.animosity.craftirc.EndPoint;
import com.ensifera.animosity.craftirc.RelayedMessage;
import com.ensifera.animosity.craftirc.SecuredEndPoint.Security;

public class DynmapPoint implements EndPoint {
	
	private Logger log = Logger.getLogger("Minecraft");
	private DynmapAPI dynmap;

	DynmapPoint(DynmapAPI dynmap) {
		this.dynmap = dynmap;
    }
	
	public Type getType() {
        return EndPoint.Type.PLAIN;
    }
    
	public Security getSecurity() {
		return Security.UNSECURED;
	}
	
    public void messageIn(RelayedMessage msg) {
    	((DynmapCommonAPI)dynmap).sendBroadcastToWeb("IRC", msg.getMessage(this));
    }

    public boolean userMessageIn(String username, RelayedMessage msg) {
    	log.info("[Dynmap2CraftIRC3] DEBUG: userMessageIn called.");
        return false;
    }

    public boolean adminMessageIn(RelayedMessage msg) {
    	log.info("[Dynmap2CraftIRC3] DEBUG: adminMessageIn called.");
        return false;
    }

    public List<String> listUsers() {
    	log.info("[Dynmap2CraftIRC3] DEBUG: listUsers called.");
        return null;
    }

    public List<String> listDisplayUsers() {
    	log.info("[Dynmap2CraftIRC3] DEBUG: listDisplayUsers called.");
        return null;
    }
}
