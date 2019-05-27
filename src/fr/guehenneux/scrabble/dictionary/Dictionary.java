package fr.guehenneux.scrabble.dictionary;

import fr.guehenneux.scrabble.model.Rack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Jonathan Guéhenneux
 */
public class Dictionary {

	private SortedTrie sortedTrie;
	private Dawg dawg;

	/**
	 * @param path
	 * @throws IOException
	 */
	public Dictionary(Path path) throws IOException {

		List<String> words = Files.readAllLines(path, StandardCharsets.UTF_8);

		sortedTrie = new SortedTrie(words);
		dawg = new Dawg(words);
	}

	/**
	 * @param rack
	 * @return
	 */
	public Set<String> getPossibleWords(Rack rack) {

		char[] letters = rack.getLetters();
		Arrays.sort(letters);

		int blankCount = rack.getBlankCount();

		return sortedTrie.getPossibleWords(letters, 0, blankCount);
	}
}