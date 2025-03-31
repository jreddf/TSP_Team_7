package net.tsp7.rogue.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.tsp7.rogue.Rogue;

public class ModScreenHandlers {
    public static final ScreenHandlerType<UpgradeTableScreenHandler> UPGRADE_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Rogue.MOD_ID, "upgrade_table_screen"),
            new ExtendedScreenHandlerType<>(UpgradeTableScreenHandler::new));

    public static void registerScreenHandlers() {
        Rogue.LOGGER.info("Registering Screen Handlers for " + Rogue.MOD_ID);
    }
}
