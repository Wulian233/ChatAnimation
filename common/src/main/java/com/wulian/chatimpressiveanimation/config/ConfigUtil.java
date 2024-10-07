package com.wulian.chatimpressiveanimation.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class ConfigUtil {
	private static ModConfigs modConfigs;

	public static ModConfigs getConfig() {
		if (modConfigs == null) {
			AutoConfig.register(ModConfigs.class, GsonConfigSerializer::new);
			modConfigs = AutoConfig.getConfigHolder(ModConfigs.class).getConfig();
		}
		return modConfigs;
	}
}
