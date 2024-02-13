package com.wulian.chatimpressiveanimation.neoforge.loader.impl;

import com.wulian.chatimpressiveanimation.neoforge.loader.api.ForgifiedLoader;
import com.wulian.chatimpressiveanimation.neoforge.loader.api.ObjectShare;

public final class ForgifiedLoaderImpl implements ForgifiedLoader {
	public static final ForgifiedLoaderImpl INSTANCE = InitHelper.get();

	private final ObjectShare objectShare = new ObjectShareImpl();

	@Override
	public ObjectShare getObjectShare() {
		return objectShare;
	}

	/**
	 * Provides singleton for static init assignment regardless of load order.
	 */
	public static class InitHelper {
		private static ForgifiedLoaderImpl instance;

		public static ForgifiedLoaderImpl get() {
			if (instance == null) instance = new ForgifiedLoaderImpl();

			return instance;
		}
	}
}
