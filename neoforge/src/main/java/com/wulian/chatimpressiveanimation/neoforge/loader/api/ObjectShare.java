package com.wulian.chatimpressiveanimation.neoforge.loader.api;

import java.util.function.BiConsumer;

public interface ObjectShare {
	/**
	 * Get the value for a specific key.
	 *
	 * <p>Consider using {@link #whenAvailable} instead if the value may not be available yet. The mod load order is
	 * undefined, so entries that are added during the same load phase should be queried in a later phase or be handled
	 * through {@link whenAvailable}.
	 *
	 * @param key key to query, format {@code modid:subkey}
	 * @return value associated with the key or null if none
	 */
	Object get(String key);

	/**
	 * Request being notified when a key/value becomes available.
	 *
	 * <p>This is primarily intended to resolve load order issues, when there is no good time to call {@link get}.
	 *
	 * <p>If there is already a value associated with the {@code key}, the consumer will be invoked directly, otherwise
	 * when one of the {@code put} methods adds a value for the key. The invocation happens on the thread calling
	 * {@link #whenAvailable} or on whichever thread calls {@code put} with the same {@code key}.
	 *
	 * <p>The request will only act once, not if the value changes again.
	 *
	 * @param key key to react upon, format {@code modid:subkey}
	 * @paran consumer consumer receiving the key/value pair: key first, value second
	 */
	void whenAvailable(String key, BiConsumer<String, Object> consumer);

	/**
	 * Set the value for a specific key.
	 *
	 * @param key key to add a value for, format {@code modid:subkey}
	 * @param value value to add, must not be null
	 * @return previous value associated with the key, null if none
	 */
	Object put(String key, Object value);

	/**
	 * Set the value for a specific key if there isn't one yet.
	 *
	 * <p>This is an atomic operation, thus thread safe contrary to using get+put.
	 *
	 * @param key key to add a value for, format {@code modid:subkey}
	 * @param value value to add, must not be null
	 * @return previous value associated with the key, null if none and thus the entry changed
	 */
	Object putIfAbsent(String key, Object value);

	/**
	 * Remove the value for a specific key.
	 *
	 * @param key key to remove the value for, format {@code modid:subkey}
	 * @return previous value associated with the key, null if none
	 */
	Object remove(String key);
}
