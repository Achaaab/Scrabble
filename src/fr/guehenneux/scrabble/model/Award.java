package fr.guehenneux.scrabble.model;

/**
 * @author Jonathan Guéhenneux
 */
public enum Award {

	NONE(1, 1),
	DOUBLE_LETTER(2, 1),
	TRIPLE_LETTER(3, 1),
	DOUBLE_WORD(1, 2),
	TRIPLE_WORD(1, 3);

	private int letterMultiplier;
	private int wordMultiplier;

	/**
	 * @param letterMultiplier
	 * @param wordMultiplier
	 */
	Award(int letterMultiplier, int wordMultiplier) {

		this.letterMultiplier = letterMultiplier;
		this.wordMultiplier = wordMultiplier;
	}

	/**
	 * @return
	 */
	public int getLetterMultiplier() {
		return letterMultiplier;
	}

	/**
	 * @return
	 */
	public int getWordMultiplier() {
		return wordMultiplier;
	}
}