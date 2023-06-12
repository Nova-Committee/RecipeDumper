package cn.mcmod.recipedumper.api;

import com.google.gson.JsonObject;
import net.minecraft.world.item.crafting.Ingredient;


public interface IRecipeInputs {
    void addInput(int paramInt1, Ingredient paramIngredient, int paramInt2);

    default void addInput(int slot, Ingredient ingredient) {
        addInput(slot, ingredient, 1);
    }

    JsonObject serialize() throws RecipeDumpException;
}