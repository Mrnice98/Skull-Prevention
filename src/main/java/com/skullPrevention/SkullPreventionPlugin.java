package com.skullPrevention;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Skull Prevention (but cool)",
	description = "The skull prevention plugin for clogers"
)
public class SkullPreventionPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SkullPreventionConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private SkullPreventionOverlay skullPreventionOverlay;

	public BufferedImage icon;


	@Override
	protected void startUp() throws Exception
	{
		icon = resizeImage(ImageUtil.loadImageResource(SkullPreventionPlugin.class, "/weaki.png"),config.imageSize().width,config.imageSize().height);

		overlayManager.add(skullPreventionOverlay);
	}


	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(skullPreventionOverlay);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuEntry().getParam1() == 7602181 && client.getVarbitValue(13131) == 1 && config.disableClicking())
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE,"","Skull Prevention Plugin, stops you from disabling skull prevention.","");
			event.consume();
		}
	}



	private BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight)
	{
		BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = scaledImage.createGraphics();
		g.drawImage(image, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return scaledImage;
	}


	@Provides
	SkullPreventionConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkullPreventionConfig.class);
	}





}
