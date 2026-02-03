package github.amvern.plainchat.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

public class ChatFormattingUtils {

    private static int findArrowIndex(Component msg) {
        for(Component msgPart : msg.toFlatList()) {
            if(msgPart.getString().contains(">")) {
                return msg.copy().toFlatList().indexOf(msgPart);
            }
        };

        return  -1;
    }

    public static Component handlePrefixedMessage(Component msg) {
        boolean isModernBeta = msg.getString().contains("[ModernBeta]");

        if (isModernBeta) {
            return recolorEntireMessage(msg, ChatFormatting.DARK_GRAY);
        }

        return  recolorNameAndMessage(msg);
    }

    public static Component recolorEntireMessage(Component msg, ChatFormatting formatting) {
        List<Component> flat = msg.toFlatList();
        MutableComponent result = Component.empty();

        for (Component comp : flat) {
            result.append(comp.copy().withStyle(comp.getStyle().withColor(formatting)));
        }

        return result;
    }

    private static Component recolorNameAndMessage(Component msg) {
        List<Component> flat = msg.toFlatList();
        MutableComponent result = Component.empty();
        int arrowIndex = findArrowIndex(msg);

        for(int i = 0; i < flat.size(); i++) {
            Component part = flat.get(i);
            if(i < arrowIndex - 2) {
                continue;
            }

            if(i > arrowIndex) {
                result.append(part.copy().withStyle(part.getStyle().withColor(ChatFormatting.GRAY)));
            } else if(i < arrowIndex) {
                result.append(part.copy().withStyle(part.getStyle().withColor(ChatFormatting.WHITE)));
            } else {
                result.append(part);
            }
        }

        return result;
    }
}
