package fr.guehenneux.scrabble;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Guéhenneux
 */
public class Scrabble {

	private Grid grid;
	private Bag bag;
	private Dictionary dictionary;
	private List<Player> players;

	/**
	 *
	 */
	public Scrabble() throws URISyntaxException, IOException {

		grid = new Grid();
		bag = Bag.createFrenchBag();
		players = new ArrayList<>();

		URL dictionaryURL = ClassLoader.getSystemResource("ods6.txt");
		Path dictionaryPath = Paths.get(dictionaryURL.toURI());
		dictionary = new Dictionary(dictionaryPath);
	}

	/**
	 * @param player
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}

	/**
	 * @param index
	 * @return
	 */
	public Player getPlayer(int index) {
		return players.get(index);
	}

	/**
	 *
	 */
	public void reset() {

		grid.empty(bag);

		for (Player player : players) {
			player.empty(bag);
		}

		bag.shuffle();
	}

	/**
	 * @return
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @return
	 */
	public Bag getBag() {
		return bag;
	}

	/**
	 * @return
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}
}