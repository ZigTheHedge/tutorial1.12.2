package ru.zigthehedge.tutorial;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tutorial.MODID, name = Tutorial.NAME, version = Tutorial.VERSION)
public class Tutorial {
    public static final String MODID = "tutorial";
    public static final String NAME = "Tutorial Mod";
    public static final String VERSION = "1.0";

    @Mod.Instance(Tutorial.MODID)
    public static Tutorial instance;

    @SidedProxy(clientSide = "ru.zigthehedge.tutorial.ClientProxy", serverSide = "ru.zigthehedge.tutorial.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }


}
