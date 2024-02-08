package com.wulian.chatanimation.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import com.wulian.chatanimation.ChatAnimation;

@Mod(ChatAnimation.MOD_ID)
public class ChatAnimationClientNeoForge {
    public ChatAnimationClientNeoForge() {
        if (FMLLoader.getDist().isClient()) {
			ChatAnimation.LOGGER.info("Chat Animation is loaded!");
        }
    }
}
