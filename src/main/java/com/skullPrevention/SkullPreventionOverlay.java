package com.skullPrevention;

import net.runelite.api.Client;
import net.runelite.api.annotations.Varbit;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

import static net.runelite.client.util.ImageUtil.resizeImage;

class SkullPreventionOverlay extends Overlay
{
    private final Client client;

    private final SkullPreventionConfig config;
    private final SkullPreventionPlugin plugin;
    private final PanelComponent panelComponent = new PanelComponent();


    @Inject
    private SkullPreventionOverlay(Client client, SkullPreventionConfig config, SkullPreventionPlugin plugin)
    {
        super(plugin);
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        setResizable(true);
        this.client = client;
        this.config = config;
        this.plugin = plugin;

    }


    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (client.getVarbitValue(13131) == 1 || client.getVarbitValue(6549) == 0)
        {
            return null;
        }

        panelComponent.getChildren().clear();

        ImageComponent imageComponent = new ImageComponent(plugin.icon);

        panelComponent.getChildren().add(imageComponent);

        return panelComponent.render(graphics);
    }


}
