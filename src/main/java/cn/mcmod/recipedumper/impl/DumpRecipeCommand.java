package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.RecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import cn.mcmod.recipedumper.api.IRecipeOutputs;
import cn.mcmod.recipedumper.api.RecipeDumpException;
import com.google.common.collect.Iterators;
import com.google.gson.*;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public final class DumpRecipeCommand {
    private static final Set<ResourceLocation> ERROR_RECIPES = new HashSet<>();

    public static int executeCommand(CommandContext<CommandSourceStack> context) {
        String modId = context.getArgument("mod", String.class);
        CommandSourceStack source = context.getSource();
        RecipeManager recipeManager = source.getLevel().getRecipeManager();
        JsonArray recipesArray = dumpAllRecipes(recipeManager, modId, source.getLevel().registryAccess());
        JsonObject result = new JsonObject();
        result.add("recipes", recipesArray);
        JsonArray errorArray = new JsonArray();
        ERROR_RECIPES.forEach(id -> errorArray.add(id.toString()));
        result.add("error", errorArray);
        outputJson(new File(String.format("exports/dump_recipes_%s.json", modId)), result);
        int recipesCount = Iterators.size(recipesArray.iterator());
        source.sendSuccess(() -> Component.literal("Dump recipes successfully! See exports Directory."), false);
        source.sendSuccess(() -> Component.literal(String.format("%s recipes dumped, %s recipes skipped", recipesCount, ERROR_RECIPES.size())), false);
        ERROR_RECIPES.clear();
        return recipesCount;
    }

    public static JsonArray dumpAllRecipes(RecipeManager recipeManager, String modFilter, RegistryAccess access) {
        JsonArray array = new JsonArray();
        for (Recipe<?> recipe : recipeManager.getRecipes()) {
            if (recipe.getId().getNamespace().equals(modFilter) && RecipeDumper.getDumperRegistry().hasDumper(recipe.getClass())) {
                try {
                    array.add(dumpRecipe(recipe, access));
                } catch (RecipeDumpException e) {
                    ERROR_RECIPES.add(recipe.getId());
                }
            }
        }
        return array;
    }

    private static void outputJson(File file, JsonElement element) {
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        try {
            FileUtils.write(file, gson.toJson(element), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject dumpRecipe(Recipe<?> recipe, RegistryAccess access) throws RecipeDumpException {
        JsonObject jsonObject = new JsonObject();
        IRecipeDumper<Recipe<?>> recipeDumper = RecipeDumper.getDumperRegistry().getDumper(recipe.getClass());
        jsonObject.addProperty("type", recipeDumper.getRecipeTypeName(recipe));
        jsonObject.addProperty("name", recipe.getId().toString());
        IRecipeInputs inputs = new RecipeInputs();
        IRecipeOutputs outputs = new RecipeOutputs();
        recipeDumper.setInputs(recipe, inputs);
        recipeDumper.setOutputs(recipe, outputs, access);
        jsonObject.add("input", inputs.serialize());
        jsonObject.add("output", outputs.serialize());
        recipeDumper.writeExtraInformation(recipe, jsonObject);
        return jsonObject;
    }
}