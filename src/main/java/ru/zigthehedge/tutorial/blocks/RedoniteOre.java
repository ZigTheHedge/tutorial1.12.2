package ru.zigthehedge.tutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.zigthehedge.tutorial.Tutorial;

public class RedoniteOre extends Block {

    public RedoniteOre() {
        super(Material.ROCK);
        setRegistryName("redoniteore");
        setUnlocalizedName(Tutorial.MODID + ".redoniteore");
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(.5F);
        setHarvestLevel("pickaxe", 3);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public void initItemModel() {

        Item itemBlock = Item.REGISTRY.getObject(new ResourceLocation(Tutorial.MODID, "redoniteore"));
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(getRegistryName(), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlock, 0, itemModelResourceLocation);
    }
}
