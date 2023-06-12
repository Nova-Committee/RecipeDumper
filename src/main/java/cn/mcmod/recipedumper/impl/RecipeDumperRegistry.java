package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeDumperFactory;
import cn.mcmod.recipedumper.api.IRecipeDumperRegistry;
import net.minecraft.world.item.crafting.Recipe;

import java.util.HashMap;
import java.util.Map;


public class RecipeDumperRegistry implements IRecipeDumperRegistry {
    private final Map<Class<? extends Recipe<?>>, IRecipeDumper<Recipe<?>>> dumpers = new HashMap<>();

    @Override
    public <T extends Recipe<?>> void addRecipeDumper(Class<? extends T> recipeClass, IRecipeDumperFactory<T> recipeDumper) {
        this.dumpers.put(recipeClass, (IRecipeDumper<Recipe<?>>) recipeDumper.create());
    }

    @Override
    public IRecipeDumper<Recipe<?>> getDumper(Class<?> recipeClass) {
        return this.dumpers.get(recipeClass);
    }

    @Override
    public <T extends Recipe<?>> boolean hasDumper(Class<T> recipeClass) {
        return this.dumpers.containsKey(recipeClass);
    }
}