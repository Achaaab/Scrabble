package fr.guehenneux.scrabble;

import fr.guehenneux.scrabble.dictionary.Dawg;
import fr.guehenneux.scrabble.dictionary.Dictionary;
import fr.guehenneux.scrabble.dictionary.State;
import fr.guehenneux.scrabble.model.Bag;
import fr.guehenneux.scrabble.model.Grid;
import fr.guehenneux.scrabble.model.Orientation;
import fr.guehenneux.scrabble.model.Player;
import fr.guehenneux.scrabble.model.Rack;
import fr.guehenneux.scrabble.model.Scrabble;
import fr.guehenneux.scrabble.model.Square;
import fr.guehenneux.scrabble.tool.LengthComparator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan Guéhenneux
 */
public class Test {

	private Scrabble scrabble;

	/**
	 * @param arguments
	 */
	public static void main(String... arguments) throws IOException, URISyntaxException {

		Test test = new Test();
		test.benchDawg();
	}

	/**
	 *
	 */
	public Test() throws IOException, URISyntaxException {

		scrabble = new Scrabble();
		Player toto = new Player("Toto");
		scrabble.addPlayer(toto);
	}

	public void benchDawg() throws URISyntaxException, IOException {

		URL dictionaryUrl = Test.class.getClassLoader().getResource("ods6.txt");
		Path dictionaryPath = Paths.get(dictionaryUrl.toURI());
		List<String> words = Files.readAllLines(dictionaryPath, StandardCharsets.UTF_8);

		System.out.println("words: " + words.size());

		long beginTime = System.currentTimeMillis();
		Dawg dawg = new Dawg(words);

		for (int i = 0; i < 50; i++) {
			dawg = new Dawg(words);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Dawg construction: " + (endTime - beginTime) + "ms");

		Set<State> states = dawg.getStates();
		System.out.println("states: " + states.size());

		beginTime = System.currentTimeMillis();
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			dawg.prefixSearch(Character.toString(letter));
		}
		endTime = System.currentTimeMillis();
		System.out.println("prefix search: " + (endTime - beginTime) + "ms");

		String prefix = "JAMA";
		List<String> prefixWords = dawg.prefixSearch(prefix);
		System.out.println("prefix " + prefix + ": " + prefixWords);
	}

	/**
	 *
	 */
	public void playWholeRack() {

		Grid grid = scrabble.getGrid();
		Bag bag = scrabble.getBag();
		Dictionary dictionary = scrabble.getDictionary();
		Player toto = scrabble.getPlayer(0);
		Rack rack = toto.getRack();
		toto.draw(scrabble.getBag());

		System.out.println(rack);
		List<String> possibleWords = new ArrayList<>(dictionary.getPossibleWords(rack));
		Collections.sort(possibleWords, LengthComparator.INSTANCE);

		System.out.println(possibleWords);

		Square firstSquare = grid.getSquare(5, 7);
		grid.play(rack, firstSquare, Orientation.HORIZONTAL);
	}

	/**
	 *
	 */
	public void bench() {

		long startTime = System.currentTimeMillis();

		for (int index = 0; index < 10; index++) {

			playWholeRack();
			scrabble.reset();
		}

		long endTime = System.currentTimeMillis();
		System.out.println((endTime - startTime) + "ms");
	}
}