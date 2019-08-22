package ru.zigthehedge.tutorial.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class LockedChestTE extends TileEntity {
    public ItemStackHandler inventory = new ItemStackHandler(27);

    public LockedChestTE()
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("inventory"))inventory.deserializeNBT((NBTTagCompound)compound.getTag("inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setTag("inventory", inventory.serializeNBT());
        return compound;
    }
}
