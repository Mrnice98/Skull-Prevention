package com.skullPrevention;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("skullPrevention")
public interface SkullPreventionConfig extends Config
{
	@ConfigItem(
		keyName = "ImageSize",
		name = "Image Size",
		description = "The size of the image, turn plugin on/off to update"
	)
	default Dimension imageSize()
	{
		return new Dimension(50,50);
	}

	@ConfigItem(
			keyName = "disableClicking",
			name = "Remove disable option",
			description = "Remove Ability to turn off prevention"
	)
	default boolean disableClicking()
	{
		return false;
	}
}
