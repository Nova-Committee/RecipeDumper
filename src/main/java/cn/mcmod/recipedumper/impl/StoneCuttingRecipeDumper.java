package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import net.minecraft.world.item.crafting.StonecutterRecipe;

public class StoneCuttingRecipeDumper implements IRecipeDumper<StonecutterRecipe> {
    @Override
    public void setInputs(StonecutterRecipe recipe, IRecipeInputs inputs) {
        inputs.addInput(1, recipe.getIngredients().get(0));
    }
}