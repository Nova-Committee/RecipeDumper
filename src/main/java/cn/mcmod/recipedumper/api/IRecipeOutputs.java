package cn.mcmod.recipedumper.api;

import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;

public interface IRecipeOutputs {
    void addOutput(int paramInt, ItemStack paramItemStack);

    JsonObject serialize() throws RecipeDumpException;
}