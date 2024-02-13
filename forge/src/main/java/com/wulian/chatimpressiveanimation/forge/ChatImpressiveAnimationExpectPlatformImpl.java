package com.wulian.chatimpressiveanimation.forge;

import com.wulian.chatimpressiveanimation.forge.loader.api.ForgifiedLoader;

public class ChatImpressiveAnimationExpectPlatformImpl {
	public static Object getObjectShareItem(String key) {
		return ForgifiedLoader.getInstance().getObjectShare().get(key);
	}
}
