package com.github.doughsay.Dynmap2CraftIRC3;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.dynmap.DynmapWebChatEvent;

import com.ensifera.animosity.craftirc.CraftIRC;
import com.ensifera.animosity.craftirc.RelayedMessage;

public class Dynmap2CraftIRC3 extends JavaPlugin implements Listener {

	private Logger log = Logger.getLogger("Minecraft");
	private String logName;
	private String version;
	private CraftIRC craftIrc;
	private DynmapPoint dynmapPoint;

	public void onEnable() {

		logName = getDescription().getName();
		version = getDescription().getVersion();
		PluginManager pm = this.getServer().getPluginManager();
		this.craftIrc = (CraftIRC)getServer().getPluginManager().getPlugin("CraftIRC");
		DynmapAPI dynmap = (DynmapAPI)getServer().getPluginManager().getPlugin("dynmap");

		if(craftIrc == null || !craftIrc.isEnabled()) {
			log.info("["+logName+"] CraftIRC not loaded, disabling plugin.");
			pm.disablePlugin(((org.bukkit.plugin.Plugin) (this)));
			return;
		}

		if(dynmap == null || !((org.bukkit.plugin.Plugin)dynmap).isEnabled()) {
			log.info("["+logName+"] dynmap not loaded, disabling plugin.");
			pm.disablePlugin(((org.bukkit.plugin.Plugin) (this)));
			return;
		}

		dynmapPoint = new DynmapPoint(dynmap);
		craftIrc.registerEndPoint("dynmap", dynmapPoint);

		pm.registerEvents(this, this);

		log.info("["+logName+"] version "+version+" enabled.");
	}

	public void onDisable(){
		if(craftIrc != null) {
			craftIrc.unregisterEndPoint("dynmap");
		}

		log.info("["+logName+"] disabled.");
	}

	@EventHandler
	public void webChat(DynmapWebChatEvent webevt) {
		String msg = webevt.getMessage();
		String sender = webevt.getName();

		RelayedMessage rMsg = craftIrc.newMsg(dynmapPoint, null, "dynmap");
		rMsg.setField("message", msg);
		rMsg.setField("sender", sender);
		rMsg.post();
	}
}
