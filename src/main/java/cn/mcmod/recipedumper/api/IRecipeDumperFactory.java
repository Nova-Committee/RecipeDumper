package cn.mcmod.recipedumper.api;

public interface IRecipeDumperFactory<T extends net.minecraft.world.item.crafting.Recipe<?>> {
    IRecipeDumper<T> create();
}