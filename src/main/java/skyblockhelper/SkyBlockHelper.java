package skyblockhelper;

import carpet.CarpetServer;
import carpet.CarpetExtension;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkyBlockHelper implements CarpetExtension, ModInitializer {
    public static final SkyBlockHelper INSTANCE = new SkyBlockHelper();
    public static final Logger LOGGER = LoggerFactory.getLogger("SkyBlockHelper");

    @Override
    public void onInitialize() {
        LOGGER.info("SkyBlock Helper by Huaji_MUR233 is loading up.");
        CarpetServer.manageExtension(INSTANCE);
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(SkyBlockHelperSettings.class);
    }
}
