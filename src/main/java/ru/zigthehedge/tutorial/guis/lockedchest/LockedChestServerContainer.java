package ru.zigthehedge.tutorial.guis.lockedchest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import ru.zigthehedge.tutorial.tileentities.LockedChestTE;

import javax.annotation.Nullable;

public class LockedChestServerContainer extends Container {
    public LockedChestTE tileEntity;

    public LockedChestServerContainer(IInventory playerInventory, LockedChestTE te) {
        this.tileEntity = te;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addOwnSlots(){
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 7 + col * 18;
                int y = row * 18 + 7;
                this.addSlotToContainer(new SlotItemHandler(tileEntity.inventory, col + row * 9, x, y));
            }
        }
    }



    private void addPlayerSlots(IInventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 7 + col * 18;
                int y = row * 18 + 73;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10 - 1, x, y));
            }
        }

        for (int row = 0; row < 9; ++row) {
            int x = 7 + row * 18;
            int y = 58 + 73;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 27) {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }


}
