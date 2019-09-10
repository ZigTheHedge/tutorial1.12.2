package ru.zigthehedge.tutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.zigthehedge.tutorial.Tutorial;
import ru.zigthehedge.tutorial.guis.TutorialGUIs;
import ru.zigthehedge.tutorial.items.TutorialItems;
import ru.zigthehedge.tutorial.tileentities.LockedChestTE;

import javax.annotation.Nullable;
import java.util.Random;

public class LockedChest extends Block {
    public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static PropertyBool ISLOCKED = PropertyBool.create("islocked");

    public LockedChest(){
        super(Material.ROCK);
        setRegistryName("lockedchest");
        setUnlocalizedName(Tutorial.MODID + ".lockedchest");
        setHardness(2F);
        setHarvestLevel("axe", 1);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ISLOCKED, false));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public void initItemModel() {

        Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(Tutorial.MODID, "lockedchest"));
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, 0, itemModelResourceLocation);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if(stack.hasTagCompound())
        {
            LockedChestTE te = (LockedChestTE)worldIn.getTileEntity(pos);
            if(te != null)
            {
                te.readFromNBT(stack.getTagCompound());
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(ISLOCKED, true), 3);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal() + (state.getValue(ISLOCKED) ? 8: 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 7)).withProperty(ISLOCKED, ((meta & 8) > 0));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ISLOCKED);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
        {
            if(!playerIn.isSneaking())
            {
                LockedChestTE te = (LockedChestTE)worldIn.getTileEntity(pos);
                if(te != null) {
                    if (playerIn.getHeldItem(hand).getItem() == TutorialItems.key)
                    {
                        if(!state.getValue(ISLOCKED))
                        {
                            if (playerIn.getHeldItem(hand).hasTagCompound())
                            {
                                NBTTagCompound nbt = playerIn.getHeldItem(hand).getTagCompound();
                                if(!nbt.hasKey("pattern")) return true;
                                te.pattern = nbt.getInteger("pattern");
                                te.isLocked = true;
                                te.markDirty();
                                worldIn.setBlockState(pos, state.withProperty(ISLOCKED, true), 3);
                            } else
                                return true;
                        } else
                        {
                            if (playerIn.getHeldItem(hand).hasTagCompound())
                            {
                                NBTTagCompound nbt = playerIn.getHeldItem(hand).getTagCompound();
                                if(!nbt.hasKey("pattern")) return true;
                                if(nbt.getInteger("pattern") == te.pattern) {
                                    te.isLocked = false;
                                    te.markDirty();
                                    worldIn.setBlockState(pos, state.withProperty(ISLOCKED, false), 3);
                                }
                            } else
                                return true;
                        }
                    } else
                    {
                        if(state.getValue(ISLOCKED))return true;
                        playerIn.openGui(Tutorial.instance, TutorialGUIs.LOCKEDCHESTGUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new LockedChestTE();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        LockedChestTE te = (LockedChestTE)worldIn.getTileEntity(pos);

        if (te != null)
        {
            if(!te.isLocked) {
                for (int slot = 0; slot < te.inventory.getSlots(); slot++)
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.inventory.getStackInSlot(slot));
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Item.getItemFromBlock(this)));
            } else
            {
                ItemStack itemBlock = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound storedTE = new NBTTagCompound();
                storedTE = te.writeToNBT(storedTE);
                itemBlock.setTagCompound(storedTE);
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemBlock);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
}
