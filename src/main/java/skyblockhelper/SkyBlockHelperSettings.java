package skyblockhelper;

import carpet.settings.Rule;
import static carpet.settings.RuleCategory.*;

public class SkyBlockHelperSettings {
    public static final String SKYBLOCK_HELPER = "SkyBlock Helper";

    @Rule(
        desc = "Make alert when Wandering Trader is coming",
        category = {SKYBLOCK_HELPER, FEATURE}
    )
    public static boolean wanderingTraderAlert = false;

    @Rule(
        desc = "Make log when Wandering Trader is coming",
        category = {SKYBLOCK_HELPER, FEATURE}
    )
    public static boolean wanderingTraderLog = false;
}
