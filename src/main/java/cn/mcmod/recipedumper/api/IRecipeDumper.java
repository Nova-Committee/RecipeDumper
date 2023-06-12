package cn.mcmod.recipedumper.api;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;


public interface IRecipeDumper<T extends net.minecraft.world.item.crafting.Recipe<?>> {
    void setInputs(T paramT, IRecipeInputs paramIRecipeInputs);

    default void setOutputs(T recipe, IRecipeOutputs outputs, RegistryAccess access) {
        outputs.addOutput(1, recipe.getResultItem(access));
    }


    default void writeExtraInformation(T recipe, JsonObject jsonObject) {
    }


    default String getRecipeTypeName(T recipe) {
        return recipe.getType().toString();
    }
}