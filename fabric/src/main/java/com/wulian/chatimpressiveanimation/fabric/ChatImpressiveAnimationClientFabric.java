package com.wulian.chatimpressiveanimation.fabric;

import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;
import net.fabricmc.api.ClientModInitializer;

public class ChatImpressiveAnimationClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		ChatImpressiveAnimation.LOGGER.info("Chat Animation is loaded!");
    }
}
