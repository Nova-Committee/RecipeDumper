package cn.mcmod.recipedumper.mixins;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.LegacyUpgradeRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LegacyUpgradeRecipe.class})
public interface ILegacyUpgradeRecipeAccessor {
    @Accessor
    Ingredient getBase();

    @Accessor
    Ingredient getAddition();
}