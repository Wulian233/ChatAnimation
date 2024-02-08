package com.wulian.chatanimation.forge.loader.api;

import com.wulian.chatanimation.forge.loader.impl.ForgifiedLoaderImpl;

public interface ForgifiedLoader {
	/**
	 * Returns the public-facing Fabric Loader instance.
	 */
	static ForgifiedLoader getInstance() {
		ForgifiedLoader ret = ForgifiedLoaderImpl.INSTANCE;

		if (ret == null) {
			throw new RuntimeException("Accessed ForgifiedLoader too early!");
		}

		return ret;
	}

	/**
	 * Get the object share for inter-mod communication.
	 *
	 * <p>The share allows mods to exchange data without directly referencing each other. This makes simple interaction
	 * easier by eliminating any compile- or run-time dependencies if the shared value type is independent of the mod
	 * (only Java/game/Fabric types like collections, primitives, String, Consumer, Function, ...).
	 *
	 * <p>Active interaction is possible as well since the shared values can be arbitrary Java objects. For example
	 * exposing a {@code Runnable} or {@code Function} allows the "API" user to directly invoke some program logic.
	 *
	 * <p>It is required to prefix the share key with the mod id like {@code mymod:someProperty}. Mods should not
	 * modify entries by other mods. The share is thread safe.
	 *
	 * @return the global object share instance
	 * @since 0.12.0
	 */
	ObjectShare getObjectShare();
}
