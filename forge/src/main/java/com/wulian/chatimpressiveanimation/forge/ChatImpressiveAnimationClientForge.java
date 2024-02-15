package com.wulian.chatimpressiveanimation.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;

@Mod(ChatImpressiveAnimation.MOD_ID)
public class ChatImpressiveAnimationClientForge {
    public ChatImpressiveAnimationClientForge() {
        if (FMLLoader.getDist().isClient()) {
			ChatImpressiveAnimation.LOGGER.info("Chat Impressive Animation is loaded!");
        }
    }
}
