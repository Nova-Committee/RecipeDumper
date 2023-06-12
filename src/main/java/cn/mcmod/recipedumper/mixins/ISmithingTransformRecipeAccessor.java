package cn.mcmod.recipedumper.mixins;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SmithingTransformRecipe.class)
public interface ISmithingTransformRecipeAccessor {
    @Accessor
    Ingredient getTemplate();

    @Accessor
    Ingredient getBase();

    @Accessor
    Ingredient getAddition();
}