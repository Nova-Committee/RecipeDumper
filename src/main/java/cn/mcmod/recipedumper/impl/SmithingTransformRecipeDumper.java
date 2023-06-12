package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import cn.mcmod.recipedumper.mixins.ISmithingTransformRecipeAccessor;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

public class SmithingTransformRecipeDumper implements IRecipeDumper<SmithingTransformRecipe> {
    public void setInputs(SmithingTransformRecipe recipe, IRecipeInputs inputs) {
        ISmithingTransformRecipeAccessor accessor = (ISmithingTransformRecipeAccessor) recipe;
        inputs.addInput(1, accessor.getTemplate());
        inputs.addInput(2, accessor.getBase());
        inputs.addInput(3, accessor.getAddition());
    }
}