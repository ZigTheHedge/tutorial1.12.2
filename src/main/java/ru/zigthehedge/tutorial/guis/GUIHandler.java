package ru.zigthehedge.tutorial.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.zigthehedge.tutorial.guis.lockedchest.LockedChestGUI;
import ru.zigthehedge.tutorial.guis.lockedchest.LockedChestServerContainer;
import ru.zigthehedge.tutorial.tileentities.LockedChestTE;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == TutorialGUIs.LOCKEDCHESTGUI) {
            LockedChestTE te = (LockedChestTE) world.getTileEntity(pos);
            return new LockedChestServerContainer(player.inventory, te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if(ID == TutorialGUIs.LOCKEDCHESTGUI) {
            LockedChestTE te = (LockedChestTE) world.getTileEntity(pos);
            return new LockedChestGUI(te, new LockedChestServerContainer(player.inventory, te));
        }
        return null;
    }
}