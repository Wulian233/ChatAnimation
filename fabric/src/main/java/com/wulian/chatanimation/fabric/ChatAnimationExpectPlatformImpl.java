package com.wulian.chatanimation.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ChatAnimationExpectPlatformImpl {
	public static Object getObjectShareItem(String key) {
		return FabricLoader.getInstance().getObjectShare().get(key);
	}
}
