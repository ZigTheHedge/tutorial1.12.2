package ru.zigthehedge.tutorial.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.zigthehedge.tutorial.blocks.LockedChest;

import javax.annotation.Nullable;

public class LockedChestTE extends TileEntity {
    public ItemStackHandler inventory = new ItemStackHandler(27);
    public int pattern = 0;
    public boolean isLocked = false;

    public LockedChestTE()
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("inventory"))inventory.deserializeNBT((NBTTagCompound)compound.getTag("inventory"));
        if(compound.hasKey("pattern"))pattern = compound.getInteger("pattern");
        if(compound.hasKey("isLocked"))isLocked = compound.getBoolean("isLocked");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("pattern", pattern);
        compound.setBoolean("isLocked", isLocked);
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getCapability(capability, facing) != null;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(world.getBlockState(pos).getValue(LockedChest.ISLOCKED))
                return null;
            else
                return (T) inventory;

        } else
            return super.getCapability(capability, facing);
    }
}
