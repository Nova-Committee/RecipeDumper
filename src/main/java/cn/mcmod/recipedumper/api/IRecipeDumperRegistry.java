package cn.mcmod.recipedumper.api;

import net.minecraft.world.item.crafting.Recipe;

public interface IRecipeDumperRegistry {
    <T extends Recipe<?>> void addRecipeDumper(Class<? extends T> paramClass, IRecipeDumperFactory<T> paramIRecipeDumperFactory);

    IRecipeDumper<Recipe<?>> getDumper(Class<?> paramClass);

    <T extends Recipe<?>> boolean hasDumper(Class<T> paramClass);
}