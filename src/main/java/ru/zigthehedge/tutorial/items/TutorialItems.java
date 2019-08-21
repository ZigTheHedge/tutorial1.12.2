package ru.zigthehedge.tutorial.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import ru.zigthehedge.tutorial.Tutorial;

public class TutorialItems {

    @GameRegistry.ObjectHolder(Tutorial.MODID + ":key")
    public static Key key;

    @GameRegistry.ObjectHolder(Tutorial.MODID + ":redonite_ingot")
    public static RedoniteIngot redoniteIngot;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        key.initModel();
        redoniteIngot.initModel();
    }

    public static void registerOreDictionary()
    {
        OreDictionary.registerOre("ingotIron", new ItemStack(TutorialItems.redoniteIngot));
    }


}
