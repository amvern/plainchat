package github.amvern.plainchat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.ChatFormatting;
import static github.amvern.plainchat.util.ChatFormattingUtils.handlePrefixedMessage;
import static github.amvern.plainchat.util.ChatFormattingUtils.recolorEntireMessage;

public class PlainChatClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientReceiveMessageEvents.MODIFY_GAME.register((msg, overlay) -> {
			if(overlay) return msg;

			boolean probableAfkMessage = msg.getString().contains("**");
			if (probableAfkMessage) {
				return recolorEntireMessage(msg, ChatFormatting.DARK_GRAY);
			}

			if(msg.getString().contains("[")) {
				return handlePrefixedMessage(msg);
			}

			return msg;
		});
	}

}