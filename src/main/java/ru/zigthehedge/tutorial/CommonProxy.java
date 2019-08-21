package ru.zigthehedge.tutorial;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.zigthehedge.tutorial.blocks.BathroomTile;
import ru.zigthehedge.tutorial.blocks.KeyHole;
import ru.zigthehedge.tutorial.blocks.RedoniteOre;
import ru.zigthehedge.tutorial.blocks.TutorialBlocks;
import ru.zigthehedge.tutorial.items.Key;
import ru.zigthehedge.tutorial.items.RedoniteIngot;
import ru.zigthehedge.tutorial.items.TutorialItems;
import ru.zigthehedge.tutorial.worldgen.RedoniteGenerator;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e)
    {

    }
    public void init(FMLInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new RedoniteGenerator(), 0);
        GameRegistry.addSmelting(new ItemStack(TutorialBlocks.redoniteOre), new ItemStack(TutorialItems.redoniteIngot), 100);

        TutorialItems.registerOreDictionary();
        TutorialBlocks.registerOreDictionary();
    }
    public void postInit(FMLPostInitializationEvent e)
    {

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Key());
        event.getRegistry().register(new RedoniteIngot());

        event.getRegistry().register(new ItemBlock(TutorialBlocks.bathroomTile).setRegistryName(TutorialBlocks.bathroomTile.getRegistryName()));
        event.getRegistry().register(new ItemBlock(TutorialBlocks.redoniteOre).setRegistryName(TutorialBlocks.redoniteOre.getRegistryName()));
        event.getRegistry().register(new ItemBlock(TutorialBlocks.keyHole).setRegistryName(TutorialBlocks.keyHole.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BathroomTile());
        event.getRegistry().register(new RedoniteOre());
        event.getRegistry().register(new KeyHole());
    }
}
