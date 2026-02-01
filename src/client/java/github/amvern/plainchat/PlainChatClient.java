package github.amvern.plainchat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.network.chat.Component;

public class PlainChatClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientReceiveMessageEvents.MODIFY_GAME.register((msg, overlay) -> {
			String raw = msg.getString();
			String cleaned = raw.replaceFirst("^(\\[[^\\]]+\\]\\s*)+", "");
			System.out.println("[MODIFY_GAME] raw=" + raw + " cleaned=" + cleaned);

			return Component.literal(cleaned);
		});
	}
}