package fr.guehenneux.scrabble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jonathan Guéhenneux
 */
public class Rack extends ArrayList<Tile> {

	private static final int CLASSICAL_CAPACITY = 7;

	private int capacity;

	/**
	 *
	 */
	public Rack() {
		this(CLASSICAL_CAPACITY);
	}

	/**
	 * @param capacity
	 */
	public Rack(int capacity) {

		super(capacity);

		this.capacity = capacity;
	}

	/**
	 * @return whether this rack reached its capacity
	 */
	public boolean isFull() {
		return size() == capacity;
	}

	/**
	 * @return letters in this rack (blank tiles excluded)
	 */
	public char[] getLetters() {

		List<Character> letterList = stream().filter(Tile::isLetter).map(Tile::getLetter).collect(Collectors.toList());

		int letterCount = letterList.size();
		char[] letters = new char[letterCount];

		for (int letterIndex = 0; letterIndex < letterCount; letterIndex++) {
			letters[letterIndex] = letterList.get(letterIndex);
		}

		return letters;
	}

	/**
	 * @return number of blank tiles in this rack
	 */
	public int getBlankCount() {
		return (int) stream().filter(Tile::isBlank).count();
	}
}