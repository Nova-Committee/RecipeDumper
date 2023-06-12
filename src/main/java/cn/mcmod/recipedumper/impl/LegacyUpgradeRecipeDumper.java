package cn.mcmod.recipedumper.impl;

import cn.mcmod.recipedumper.api.IRecipeDumper;
import cn.mcmod.recipedumper.api.IRecipeInputs;
import cn.mcmod.recipedumper.mixins.ILegacyUpgradeRecipeAccessor;
import net.minecraft.world.item.crafting.LegacyUpgradeRecipe;

public class LegacyUpgradeRecipeDumper implements IRecipeDumper<LegacyUpgradeRecipe> {
    public void setInputs(LegacyUpgradeRecipe recipe, IRecipeInputs inputs) {
        ILegacyUpgradeRecipeAccessor accessor = (ILegacyUpgradeRecipeAccessor) recipe;
        inputs.addInput(1, accessor.getBase());
        inputs.addInput(2, accessor.getAddition());
    }
}