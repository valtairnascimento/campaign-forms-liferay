package io.github.valtairnascimento.campaignforms.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PositionUtilTest {
	@Test
	public void testMoveClampsPosition() {
		List<Item> items = _items(10, 20, 30);

		PositionUtil.move(
			items, 10, 99, Item::getId, Item::getPosition, Item::setPosition);

		Assert.assertEquals(Arrays.asList(20L, 30L, 10L), _orderedIds(items));

		PositionUtil.move(
			items, 10, -5, Item::getId, Item::getPosition, Item::setPosition);

		Assert.assertEquals(Arrays.asList(10L, 20L, 30L), _orderedIds(items));
	}

	@Test
	public void testMoveDown() {
		List<Item> items = _items(10, 20, 30, 40);

		List<Item> changedItems = PositionUtil.move(
			items, 10, 2, Item::getId, Item::getPosition, Item::setPosition);

		Assert.assertEquals(
			Arrays.asList(20L, 30L, 10L, 40L), _orderedIds(items));

		Assert.assertEquals(3, changedItems.size());
	}

	@Test
	public void testReorder() {
		List<Item> items = _items(10, 20, 30);

		PositionUtil.reorder(
			items, new long[] {30, 10, 20}, Item::getId, Item::getPosition,
			Item::setPosition);

		Assert.assertEquals(Arrays.asList(30L, 10L, 20L), _orderedIds(items));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReorderWithForeignId() {
		PositionUtil.reorder(
			_items(10, 20), new long[] {10, 99}, Item::getId,
			Item::getPosition, Item::setPosition);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReorderWithMissingId() {
		PositionUtil.reorder(
			_items(10, 20, 30), new long[] {10, 20}, Item::getId,
			Item::getPosition, Item::setPosition);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReorderWithRepeatedId() {
		PositionUtil.reorder(
			_items(10, 20), new long[] {10, 10}, Item::getId, Item::getPosition,
			Item::setPosition);
	}

	private List<Item> _items(long... ids) {
		List<Item> items = new ArrayList<>();

		for (int i = 0; i < ids.length; i++) {
			items.add(new Item(ids[i], i));
		}

		return items;
	}

	private List<Long> _orderedIds(List<Item> items) {
		List<Item> sortedItems = new ArrayList<>(items);

		sortedItems.sort(Comparator.comparingInt(Item::getPosition));

		List<Long> ids = new ArrayList<>();

		for (Item item : sortedItems) {
			ids.add(item.getId());
		}

		return ids;
	}

	private static class Item {
		public long getId() {
			return _id;
		}

		public int getPosition() {
			return _position;
		}

		public void setPosition(int position) {
			_position = position;
		}

		private Item(long id, int position) {
			_id = id;
			_position = position;
		}

		private final long _id;
		private int _position;
	}

}
