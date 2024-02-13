package com.wulian.chatimpressiveanimation.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;

@Mod(ChatImpressiveAnimation.MOD_ID)
public class ChatImpressiveAnimationClientNeoForge {
    public ChatImpressiveAnimationClientNeoForge() {
        if (FMLLoader.getDist().isClient()) {
			ChatImpressiveAnimation.LOGGER.info("Chat Animation is loaded!");
        }
    }
}
