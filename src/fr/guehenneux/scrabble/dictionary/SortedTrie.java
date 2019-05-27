package fr.guehenneux.scrabble.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Jonathan Guéhenneux
 */
public class SortedTrie {

	private int depth;
	private Set<String> words;
	private Map<Character, SortedTrie> children;

	/**
	 * @param words
	 */
	public SortedTrie(List<String> words) {

		this(0);

		words.forEach(this::add);
	}

	/**
	 * @param depth
	 */
	private SortedTrie(int depth) {

		this.depth = depth;

		words = new HashSet<>();
		children = new HashMap<>();
	}

	/**
	 * @param word
	 */
	public void add(String word) {

		char[] letters = word.toCharArray();
		Arrays.sort(letters);

		add(letters, word);
	}

	/**
	 * @param sortedLetters alphabetically sorted letters of the word
	 * @param word          word to add
	 */
	private void add(char[] sortedLetters, String word) {

		if (depth == sortedLetters.length) {

			words.add(word);

		} else {

			char letter = sortedLetters[depth];

			SortedTrie child = children.get(letter);

			if (child == null) {

				child = new SortedTrie(depth + 1);
				children.put(letter, child);
			}

			child.add(sortedLetters, word);
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

				SortedTrie child = children.get(letter);

				if (child != null) {
					possibleWords.addAll(child.getPossibleWords(sortedLetters, index + 1, blankCount));
				}
			}
		}

		if (blankCount > 0) {

			for (SortedTrie child : children.values()) {
				possibleWords.addAll(child.getPossibleWords(sortedLetters, offset, blankCount - 1));
			}
		}

		return possibleWords;
	}
}