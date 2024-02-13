package com.wulian.chatimpressiveanimation;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ChatImpressiveAnimationExpectPlatform {
	@ExpectPlatform
	public static Object getObjectShareItem(String key) {
		// Just throw an error, the content should get replaced at runtime.
		throw new AssertionError();
	}
}
