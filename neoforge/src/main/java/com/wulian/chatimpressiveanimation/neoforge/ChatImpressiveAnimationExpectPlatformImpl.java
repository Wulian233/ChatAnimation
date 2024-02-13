package com.wulian.chatimpressiveanimation.neoforge;

import com.wulian.chatimpressiveanimation.neoforge.loader.api.ForgifiedLoader;

public class ChatImpressiveAnimationExpectPlatformImpl {
    public static Object getObjectShareItem(String key) {
		return ForgifiedLoader.getInstance().getObjectShare().get(key);
	}
}
