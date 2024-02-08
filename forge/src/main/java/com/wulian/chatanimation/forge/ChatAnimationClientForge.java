package com.wulian.chatanimation.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import com.wulian.chatanimation.ChatAnimation;

@Mod(ChatAnimation.MOD_ID)
public class ChatAnimationClientForge {
    public ChatAnimationClientForge() {
        if (FMLLoader.getDist().isClient()) {
			ChatAnimation.LOGGER.info("Chat Animation is loaded!");
        }
    }
}
