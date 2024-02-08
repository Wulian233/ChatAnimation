package com.wulian.chatanimation.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import com.wulian.chatanimation.ChatAnimation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ChatAnimation.MOD_ID)
public class ChatAnimationClientForge {
    public static final Logger LOGGER = LogManager.getLogger("ChatAnimation");
    public ChatAnimationClientForge() {
        if (FMLLoader.getDist().isClient()) {
            LOGGER.info("Chat Animation is loaded!");
        }
    }
}