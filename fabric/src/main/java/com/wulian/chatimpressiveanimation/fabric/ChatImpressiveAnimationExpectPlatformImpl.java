package com.wulian.chatimpressiveanimation.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ChatImpressiveAnimationExpectPlatformImpl {
	public static Object getObjectShareItem(String key) {
		return FabricLoader.getInstance().getObjectShare().get(key);
	}
}
