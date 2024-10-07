package com.wulian.chatimpressiveanimation.config;

import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ChatImpressiveAnimation.MOD_ID)
public class ModConfigs implements ConfigData {
	public boolean enableChatSendingAnimation = true;
	public boolean enableOpenChatAnimation = true;
	public boolean removeMessageIndicator = true;
}
