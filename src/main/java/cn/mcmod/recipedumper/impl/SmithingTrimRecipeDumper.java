package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import cn.mcmod.recipedumper.mixins.ISmithingTrimRecipeAccessor;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;

public class SmithingTrimRecipeDumper implements IRecipeDumper<SmithingTrimRecipe> {
    @Override
    public void setInputs(SmithingTrimRecipe recipe, IRecipeInputs inputs) {
        ISmithingTrimRecipeAccessor accessor = (ISmithingTrimRecipeAccessor) recipe;
        inputs.addInput(1, accessor.getBase());
        inputs.addInput(2, accessor.getAddition());
    }
}
