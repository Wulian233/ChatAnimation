package com.wulian.chatanimation.neoforge;

import com.wulian.chatanimation.neoforge.loader.api.ForgifiedLoader;

public class ChatAnimationExpectPlatformImpl {
    public static Object getObjectShareItem(String key) {
		return ForgifiedLoader.getInstance().getObjectShare().get(key);
	}
}
