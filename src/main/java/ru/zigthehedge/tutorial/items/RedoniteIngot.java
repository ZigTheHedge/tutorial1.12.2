package ru.zigthehedge.tutorial.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.zigthehedge.tutorial.Tutorial;

public class RedoniteIngot extends Item {
    public RedoniteIngot ()
    {
        setRegistryName("redonite_ingot");
        setUnlocalizedName(Tutorial.MODID + ".redonite_ingot");
        setCreativeTab(CreativeTabs.MISC);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
