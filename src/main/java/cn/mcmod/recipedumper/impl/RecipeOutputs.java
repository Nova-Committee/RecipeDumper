package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeOutputs;
import cn.mcmod.recipedumper.api.RecipeDumpException;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeOutputs
        implements IRecipeOutputs {
    final Int2ObjectMap<ItemStack> outputs = new Int2ObjectArrayMap<>();


    public void addOutput(int slot, ItemStack stack) {
        this.outputs.put(slot, stack);
    }


    public JsonObject serialize() throws RecipeDumpException {
        JsonObject json = new JsonObject();
        try {
            for (Int2ObjectMap.Entry<ItemStack> entry : this.outputs.int2ObjectEntrySet()) {
                JsonObject stackJson = new JsonObject();
                ItemStack stack = entry.getValue();
                stackJson.addProperty("item", ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
                stackJson.addProperty("count", stack.getCount());
                if (stack.hasTag()) {
                    stackJson.addProperty("nbt", stack.getTag().toString());
                }
                json.add(String.valueOf(entry.getIntKey()), stackJson);
            }

        } catch (Throwable throwable) {
            throw new RecipeDumpException();
        }
        return json;
    }
}