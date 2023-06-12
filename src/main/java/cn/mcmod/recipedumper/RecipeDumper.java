package cn.mcmod.recipedumper;

import cn.mcmod.recipedumper.api.IRecipeDumperRegistry;
import cn.mcmod.recipedumper.impl.*;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.command.ModIdArgument;
import org.slf4j.Logger;

@Mod("recipedumper")
public class RecipeDumper {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "recipedumper";
    private static final IRecipeDumperRegistry REGISTRY = new RecipeDumperRegistry();

    public RecipeDumper() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static IRecipeDumperRegistry getDumperRegistry() {
        return REGISTRY;
    }

    private void setup(FMLCommonSetupEvent event) {
        REGISTRY.addRecipeDumper(ShapedRecipe.class, ShapedRecipeDumper::new);
        REGISTRY.addRecipeDumper(ShapelessRecipe.class, ShapelessRecipeDumper::new);
        REGISTRY.addRecipeDumper(StonecutterRecipe.class, StoneCuttingRecipeDumper::new);
        REGISTRY.addRecipeDumper(SmithingTrimRecipe.class, SmithingTrimRecipeDumper::new);
        REGISTRY.addRecipeDumper(SmithingTransformRecipe.class, SmithingTransformRecipeDumper::new);
        REGISTRY.addRecipeDumper(BlastingRecipe.class, CookingRecipeDumper::new);
        REGISTRY.addRecipeDumper(CampfireCookingRecipe.class, CookingRecipeDumper::new);
        REGISTRY.addRecipeDumper(SmeltingRecipe.class, CookingRecipeDumper::new);
        REGISTRY.addRecipeDumper(SmokingRecipe.class, CookingRecipeDumper::new);
    }

    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("dumprecipe")
                        .then(Commands.argument("mod", ModIdArgument.modIdArgument()).executes(DumpRecipeCommand::executeCommand)));
    }
}