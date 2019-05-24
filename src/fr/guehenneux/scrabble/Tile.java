package fr.guehenneux.scrabble;

/**
 * @author Jonathan Guéhenneux
 */
public class Tile {

	private static final String BLANK_STRING = "*";

	private char letter;
	private int value;

	private String string;

	/**
	 * Constructor without letter, for blank tiles.
	 */
	public Tile() {

		letter = 0;
		value = 0;

		string = BLANK_STRING;
	}

	/**
	 * @param letter
	 * @param value
	 */
	public Tile(char letter, int value) {

		this.letter = letter;
		this.value = value;

		string = Character.toString(letter);
	}

	/**
	 * @return whether this tile is a letter
	 */
	public boolean isLetter() {
		return letter != 0;
	}

	/**
	 * @return whether this tile is blank
	 */
	public boolean isBlank() {
		return letter == 0;
	}

	/**
	 * @return the letter bore by the tile
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * @return the tile value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return string;
	}
}