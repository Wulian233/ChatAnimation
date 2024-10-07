package com.wulian.chatimpressiveanimation.neoforge;

import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

@Mod(ChatImpressiveAnimation.MOD_ID)
public class ChatImpressiveAnimationClientNeoForge {
    public ChatImpressiveAnimationClientNeoForge() {
        if (FMLLoader.getDist().isClient()) {
			ChatImpressiveAnimation.LOGGER.info("Chat Impressive Animation is loaded!");
        }
    }
}
