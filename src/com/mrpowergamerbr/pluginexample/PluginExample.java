package com.mrpowergamerbr.pluginexample;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.paninicms.Panini;
import com.paninicms.plugin.PaniniPlugin;
import com.paninicms.plugin.event.Listener;
import com.paninicms.plugin.event.SubscribeEvent;
import com.paninicms.plugin.event.blog.PostRenderEvent;
import com.paninicms.plugin.event.blog.PreRenderEvent;
import com.paninicms.utils.PaniniUtils;

public class PluginExample extends PaniniPlugin implements Listener {
	@Override
	public void onEnable() {
		System.out.println("Hello World!");
		this.registerListener(this);
	}
	
	@SubscribeEvent
	public void onPreRender(PreRenderEvent ev) {
		ev.getRenderContext().contextVars().put("helloWorld", "Shantae <3"); // We are going to store "Shantae <3" on the variable "helloWorld"
		// We can access it by using {{ helloWorld }} in your template
	}
	
	@SubscribeEvent
	public void onPostRender(PostRenderEvent ev) {
		if (ev.getTemplate() == null) {
			// If null, that means that Panini didn't process anything, so we are going to add our own fancy easter egg page!
			
			if (PaniniUtils.is(ev.getRenderContext().arguments(), 0, "easteregg")) { // If the link is /easteregg
				// Pro tip: If you use Lombok, you can use @ExtensionMethod(PaniniUtils.class) ;)
				try {
					// Pro tip²: You can also setTemplate(String)
					PebbleTemplate template = Panini.getEngine().getTemplate("easteregg.html");
					ev.setTemplate(template);
				} catch (PebbleException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
