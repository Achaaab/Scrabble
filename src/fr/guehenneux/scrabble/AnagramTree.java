package fr.guehenneux.scrabble;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jonathan Guéhenneux
 */
public class AnagramTree {

	private int depth;
	private Set<String> words;
	private Map<Character, AnagramTree> children;

	/**
	 * @param depth
	 */
	public AnagramTree(int depth) {

		this.depth = depth;

		words = new HashSet<>();
		children = new HashMap<>();
	}

	/**
	 * @param sortedLetters alphabetically sorted letters of the word
	 * @param word          word to add
	 */
	public void addWord(char[] sortedLetters, String word) {

		if (depth == sortedLetters.length) {

			words.add(word);

		} else {

			char letter = sortedLetters[depth];

			AnagramTree child = children.get(letter);

			if (child == null) {

				child = new AnagramTree(depth + 1);
				children.put(letter, child);
			}

			child.addWord(sortedLetters, word);
		}
	}

	/**
	 * @param sortedLetters alphabetically sorted letters
	 * @param offset        tile offset
	 * @param blankCount
	 * @return possible words with given tiles and blank count
	 */
	public Set<String> getPossibleWords(char[] sortedLetters, int offset, int blankCount) {

		Set<String> possibleWords = new HashSet<>();
		possibleWords.addAll(words);

		char previousLetter = 0;
		char letter;

		for (int index = offset; index < sortedLetters.length; index++) {

			letter = sortedLetters[index];

			if (letter != previousLetter) {

				AnagramTree child = children.get(letter);

				if (child != null) {
					possibleWords.addAll(child.getPossibleWords(sortedLetters, index + 1, blankCount));
				}
			}
		}

		if (blankCount > 0) {

			for (AnagramTree child : children.values()) {
				possibleWords.addAll(child.getPossibleWords(sortedLetters, offset, blankCount - 1));
			}
		}

		return possibleWords;
	}
}