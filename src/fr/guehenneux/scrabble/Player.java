package fr.guehenneux.scrabble;

/**
 * @author Jonathan Guéhenneux
 */
public class Player {

	private String name;
	private Rack rack;
	private int score;

	/**
	 * @param name
	 */
	public Player(String name) {

		this.name = name;

		rack = new Rack();
		score = 0;
	}

	/**
	 * @param bag
	 */
	public void draw(Bag bag) {

		while (!rack.isFull() && !bag.isEmpty()) {
			rack.add(bag.pick());
		}
	}

	/**
	 * @param bag
	 */
	public void empty(Bag bag) {

		bag.addAll(rack);
		rack.clear();
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Rack getRack() {
		return rack;
	}

	/**
	 * @return
	 */
	public int getScore() {
		return score;
	}
}