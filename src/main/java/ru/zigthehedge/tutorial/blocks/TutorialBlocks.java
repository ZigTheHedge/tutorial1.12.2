package ru.zigthehedge.tutorial.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.zigthehedge.tutorial.Tutorial;

public class TutorialBlocks {
    @GameRegistry.ObjectHolder(Tutorial.MODID + ":bathroomtile")
    public static BathroomTile bathroomTile;

    @GameRegistry.ObjectHolder(Tutorial.MODID + ":redoniteore")
    public static RedoniteOre redoniteOre;

    @GameRegistry.ObjectHolder(Tutorial.MODID + ":keyhole")
    public static KeyHole keyHole;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        bathroomTile.initModel();
        redoniteOre.initModel();
        keyHole.initModel();
    }

    @SideOnly(Side.CLIENT)
    public static void initBlockItemModels() {
        bathroomTile.initItemModel();
        redoniteOre.initItemModel();
        keyHole.initItemModel();
    }

    public static void registerOreDictionary()
    {

    }
}
