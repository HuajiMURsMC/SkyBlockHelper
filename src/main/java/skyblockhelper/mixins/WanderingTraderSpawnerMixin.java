package skyblockhelper.mixins;

import carpet.CarpetServer;
import skyblockhelper.SkyBlockHelper;
import skyblockhelper.SkyBlockHelperSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Objects;
import java.util.Optional;

@Mixin(WanderingTraderSpawner.class)
public class WanderingTraderSpawnerMixin {
    private final String message = """
        [{
        	"text": "<流浪商人来了！！！>",
        	"color": "gold",
        	"hoverEvent": {
        		"action": "show_text",
        		"contents": [{
        			"text": "坐标："
        		}, {
        			"text": "[%d, %d, %d]",
        			"color": "green"
        		}]
        	},
        	"clickEvent": {
                "action": "run_command",
                "value": "/highlightWaypoint %d %d %d"
            }
        }]
        """;

    @Inject(method = "spawn", at = @At(value = "RETURN", ordinal = 3), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onSpawn(
        ServerLevel serverLevel,
        CallbackInfoReturnable<Boolean> cir,
        Player player,
        BlockPos blockPos,
        int i,
        PoiManager poiManager,
        Optional<BlockPos> optional,
        BlockPos blockPos2,
        BlockPos blockPos3,
        WanderingTrader wanderingTrader
    ) {
        int x = (int) wanderingTrader.getX();
        int y = (int) wanderingTrader.getY();
        int z = (int) wanderingTrader.getZ();
        if (SkyBlockHelperSettings.wanderingTraderAlert) {
            CarpetServer.minecraft_server.getPlayerList().getPlayers().forEach(
                serverPlayer -> serverPlayer.displayClientMessage(Objects.requireNonNull(Component.Serializer.fromJson(message.formatted(x, y, z, x, y, z))), false)
            );
        }
        if (SkyBlockHelperSettings.wanderingTraderLog) {
            SkyBlockHelper.LOGGER.info("A Wandering Trader is spawning, position: [%d, %d, %d]".formatted(x, y, z));
        }
    }
}
