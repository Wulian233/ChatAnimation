package com.wulian.chatanimation.neoforge;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import com.wulian.chatanimation.ChatAnimation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ChatAnimation.MOD_ID)
public class ChatAnimationClientNeoForge {
    public static final Logger LOGGER = LogManager.getLogger("ChatAnimation");
    public ChatAnimationClientNeoForge() {
        if (FMLLoader.getDist().isClient()) {
            LOGGER.info("Chat Animation is loaded!");
        }
    }
}