package io.github.valtairnascimento.campaignforms.internal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class PositionUtil {
	public static <T> List<T> reorder(
		List<T> items, long[] orderedIds, ToLongFunction<T> idFunction,
		ToIntFunction<T> positionFunction, ObjIntConsumer<T> positionSetter) {
		if (orderedIds.length != items.size()) {
			throw new IllegalArgumentException(
				"Expected " + items.size() + " IDs, got " + orderedIds.length);
		}

		Map<Long, T> itemsMap = new HashMap<>();

		for (T item : items) {
			itemsMap.put(idFunction.applyAsLong(item), item);
		}

		List<T> changedItems = new ArrayList<>();

		for (int position = 0; position < orderedIds.length; position++) {
			T item = itemsMap.remove(orderedIds[position]);

			if (item == null) {
				throw new IllegalArgumentException(
					"Unknown or repeated ID " + orderedIds[position]);
			}

			if (positionFunction.applyAsInt(item) != position) {
				positionSetter.accept(item, position);

				changedItems.add(item);
			}
		}

		return changedItems;
	}

	public static <T> List<T> move(
		List<T> items, long itemId, int newPosition,
		ToLongFunction<T> idFunction, ToIntFunction<T> positionFunction,
		ObjIntConsumer<T> positionSetter) {
		List<T> orderedItems = new ArrayList<>(items);

		T movedItem = null;

		for (T item : orderedItems) {
			if (idFunction.applyAsLong(item) == itemId) {
				movedItem = item;

				break;
			}
		}

		if (movedItem == null) {
			throw new IllegalArgumentException("Unknown ID " + itemId);
		}

		orderedItems.remove(movedItem);

		newPosition = Math.max(0, Math.min(newPosition, orderedItems.size()));

		orderedItems.add(newPosition, movedItem);

		long[] orderedIds = new long[orderedItems.size()];

		for (int i = 0; i < orderedIds.length; i++) {
			orderedIds[i] = idFunction.applyAsLong(orderedItems.get(i));
		}

		return reorder(
			items, orderedIds, idFunction, positionFunction, positionSetter);
	}

	private PositionUtil() {
	}

}
