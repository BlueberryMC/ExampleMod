package com.example.exampleMod;

import com.example.exampleMod.items.ExampleItem;
import net.blueberrymc.common.bml.BlueberryMod;
import net.blueberrymc.registry.BlueberryRegistries;
import net.minecraft.server.MinecraftServer;

public class ExampleMod extends BlueberryMod {
    @Override
    public void onLoad() {
        MinecraftServer.class.getClassLoader(); // If your setup is working, this line will not show error
        getLogger().info("Hello world!");
    }

    @Override
    public void onPreInit() {
        // /give @s examplemod:example_item{Power:5f} 1
        BlueberryRegistries.ITEM.register("example_item", new ExampleItem(this));
    }

    @Override
    public void onPostInit() {
        getLogger().info("Hello world again!");
    }
}
