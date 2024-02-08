package com.wulian.chatanimation.fabric;

import com.wulian.chatanimation.ChatAnimation;
import net.fabricmc.api.ClientModInitializer;

public class ChatAnimationClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChatAnimation.init();
    }
}