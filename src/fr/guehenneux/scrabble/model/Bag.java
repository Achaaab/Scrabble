package fr.guehenneux.scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Bag of tiles.
 *
 * @author Jonathan Guéhenneux
 */
public class Bag extends ArrayList<Tile> {

	/**
	 * @return a bag from French edition initially full of tiles
	 */
	public static Bag createFrenchBag() {

		Bag bag = new Bag();

		bag.add(15, 'E', 1);
		bag.add(9, 'A', 1);
		bag.add(8, 'I', 1);
		bag.add(6, 'N', 1);
		bag.add(6, 'O', 1);
		bag.add(6, 'R', 1);
		bag.add(6, 'S', 1);
		bag.add(6, 'T', 1);
		bag.add(6, 'U', 1);
		bag.add(5, 'L', 1);

		bag.add(3, 'D', 2);
		bag.add(3, 'M', 2);
		bag.add(2, 'G', 2);

		bag.add(2, 'B', 3);
		bag.add(2, 'C', 3);
		bag.add(2, 'P', 3);

		bag.add(2, 'F', 4);
		bag.add(2, 'H', 4);
		bag.add(2, 'V', 4);

		bag.add(1, 'J', 8);
		bag.add(1, 'Q', 8);

		bag.add(1, 'K', 10);
		bag.add(1, 'W', 10);
		bag.add(1, 'X', 10);
		bag.add(1, 'Y', 10);
		bag.add(1, 'Z', 10);

		bag.addBlank(2);

		bag.shuffle();

		return bag;
	}

	/**
	 *
	 */
	private Bag() {

	}

	/**
	 * @param count
	 * @param letter
	 * @param value
	 */
	private void add(int count, char letter, int value) {
		Stream.generate(() -> new Tile(letter, value)).limit(count).forEach(this::add);
	}

	/**
	 * @param count
	 */
	private void addBlank(int count) {
		Stream.generate(() -> new Tile()).limit(count).forEach(this::add);
	}

	/**
	 *
	 */
	public void shuffle() {
		Collections.shuffle(this);
	}

	/**
	 * @return a random tile from the bag
	 */
	public Tile pick() {
		return remove(size() - 1);
	}
}