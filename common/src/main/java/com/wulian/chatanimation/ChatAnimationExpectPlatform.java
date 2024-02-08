package com.wulian.chatanimation;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ChatAnimationExpectPlatform {
	@ExpectPlatform
	public static Object getObjectShareItem(String key) {
		// Just throw an error, the content should get replaced at runtime.
		throw new AssertionError();
	}
}
