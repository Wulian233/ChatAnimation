package com.wulian.chatimpressiveanimation.config;

import com.wulian.chatimpressiveanimation.ChatImpressiveAnimation;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

@Config(name = ChatImpressiveAnimation.MOD_ID)
public class ModConfigs implements ConfigData {
	public boolean enableOpenChatAnimation = true;
	public boolean enableChatSendingAnimation = true;
	public boolean removeMessageIndicator = true;

	public static ModConfigs createConfig() {
		return AutoConfig.register(ModConfigs.class, GsonConfigSerializer::new).getConfig();
	}

	public static Screen createConfigScreen(Screen parent) {
		return AutoConfig.getConfigScreen(ModConfigs.class, parent).get();
	}
}
