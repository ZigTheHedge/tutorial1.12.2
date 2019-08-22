package ru.zigthehedge.tutorial.guis.lockedchest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.zigthehedge.tutorial.Tutorial;
import ru.zigthehedge.tutorial.tileentities.LockedChestTE;

public class LockedChestGUI extends GuiContainer {
    public static final int WIDTH = 174;
    public static final int HEIGHT = 154;

    private LockedChestTE te;

    private static ResourceLocation background = new ResourceLocation(Tutorial.MODID, "textures/gui/lockedchest.png");

    public LockedChestGUI(LockedChestTE tileEntity, LockedChestServerContainer container) {
        super(container);
        te = tileEntity;
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
