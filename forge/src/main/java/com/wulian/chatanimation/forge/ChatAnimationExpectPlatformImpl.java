package com.wulian.chatanimation.forge;

import com.wulian.chatanimation.forge.loader.api.ForgifiedLoader;

public class ChatAnimationExpectPlatformImpl {
	public static Object getObjectShareItem(String key) {
		return ForgifiedLoader.getInstance().getObjectShare().get(key);
	}
}
