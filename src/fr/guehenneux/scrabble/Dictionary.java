package fr.guehenneux.scrabble;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Jonathan Guéhenneux
 */
public class Dictionary {

	private SortedTrie tree;

	/**
	 * @param path
	 * @throws IOException
	 */
	public Dictionary(Path path) throws IOException {

		tree = new SortedTrie(0);

		try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(this::addWord);
		}
	}

	/**
	 * @param word
	 */
	private void addWord(String word) {

		char[] letters = word.toCharArray();
		Arrays.sort(letters);

		tree.addWord(letters, word);
	}

	/**
	 * @param rack
	 * @return
	 */
	public Set<String> getPossibleWords(Rack rack) {

		char[] letters = rack.getLetters();
		Arrays.sort(letters);

		int blankCount = rack.getBlankCount();

		return tree.getPossibleWords(letters, 0, blankCount);
	}
}