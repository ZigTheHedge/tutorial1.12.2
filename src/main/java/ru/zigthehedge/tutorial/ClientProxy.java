package ru.zigthehedge.tutorial;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import ru.zigthehedge.tutorial.blocks.TutorialBlocks;
import ru.zigthehedge.tutorial.items.TutorialItems;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        TutorialBlocks.initBlockItemModels();
        super.postInit(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        TutorialItems.initModels();
        TutorialBlocks.initModels();
    }


}
