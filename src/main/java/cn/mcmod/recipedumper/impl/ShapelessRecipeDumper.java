package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class ShapelessRecipeDumper implements IRecipeDumper<ShapelessRecipe> {
    public void setInputs(ShapelessRecipe recipe, IRecipeInputs inputs) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            inputs.addInput(i + 1, ingredients.get(i));
        }
    }

    public String getRecipeTypeName(ShapelessRecipe recipe) {
        return "crafting_shapeless";
    }
}