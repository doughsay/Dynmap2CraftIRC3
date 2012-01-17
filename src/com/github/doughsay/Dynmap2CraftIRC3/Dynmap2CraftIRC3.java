package com.github.doughsay.Dynmap2CraftIRC3;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;

import com.ensifera.animosity.craftirc.CraftIRC;

import static org.bukkit.event.Event.Type.CUSTOM_EVENT;

public class Dynmap2CraftIRC3 extends JavaPlugin {
	
	private Logger log = Logger.getLogger("Minecraft");
	private String logName;
	private String version;
	private CraftIRC craftIrc;
	
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
		
		DynmapPoint dynmapPoint = new DynmapPoint(dynmap);
		craftIrc.registerEndPoint("dynmap", dynmapPoint);
		
		Dynmap2CraftIRCWebChatListener webChatListener = new Dynmap2CraftIRCWebChatListener(craftIrc, dynmapPoint);
		pm.registerEvent(CUSTOM_EVENT, webChatListener, Priority.Normal, this);
		
		log.info("["+logName+"] version "+version+" enabled.");
	}
	 
	public void onDisable(){
		if(craftIrc != null) {
			craftIrc.unregisterEndPoint("dynmap");
		}
		
		log.info("["+logName+"] disabled.");
	}
}
