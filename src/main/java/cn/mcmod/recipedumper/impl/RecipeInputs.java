package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeInputs;
import cn.mcmod.recipedumper.api.RecipeDumpException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.Validate;

public class RecipeInputs
        implements IRecipeInputs {
    final Int2ObjectMap<Ingredient> inputs = new Int2ObjectArrayMap<>();
    final Int2IntMap counts = new Int2IntArrayMap();

    @Override
    public void addInput(int slot, Ingredient ingredient, int count) {
        if (ingredient.isEmpty())
            return;
        this.inputs.put(slot, ingredient);
        this.counts.put(slot, count);
    }

    @Override
    public JsonObject serialize() throws RecipeDumpException {
        JsonObject json = new JsonObject();
        if (this.inputs.size() != this.counts.size()) {
            throw new RecipeDumpException();
        }
        try {
            for (Int2ObjectMap.Entry<Ingredient> entry : this.inputs.int2ObjectEntrySet()) {
                Validate.isTrue(entry.getValue().isVanilla());
                JsonElement ingredientJson = entry.getValue().toJson();
                ingredientJson.getAsJsonObject().addProperty("count", this.counts.get(entry.getIntKey()));
                json.add(String.valueOf(entry.getIntKey()), ingredientJson);
            }

        } catch (Throwable throwable) {

            throw new RecipeDumpException();
        }
        return json;
    }
}