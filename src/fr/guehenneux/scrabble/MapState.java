package fr.guehenneux.scrabble;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Jonathan Guéhenneux
 */
public class MapState implements State {

	private static int nextIdentifier = 0;

	private int identifier;
	private Map<Character, State> successors;
	private boolean word;
	private char mostRecentCharacter;
	private State mostRecentSuccessor;

	/**
	 *
	 */
	public MapState() {

		identifier = nextIdentifier++;
		successors = new LinkedHashMap<>();
		word = false;
	}

	@Override
	public int getIdentifier() {
		return identifier;
	}

	@Override
	public void forEach(BiConsumer<Character, State> consumer) {
		successors.forEach(consumer);
	}

	@Override
	public State getSuccessor(char character) {
		return successors.get(character);
	}

	@Override
	public boolean hasSuccessors() {
		return !successors.isEmpty();
	}

	@Override
	public void setSuccessor(char character, State successor) {

		successors.put(character, successor);

		mostRecentCharacter = character;
		mostRecentSuccessor = successor;
	}

	@Override
	public int hashCode() {
		return identifier;
	}

	@Override
	public boolean equals(Object object) {

		boolean equals;

		if (this == object) {

			equals = true;

		} else if (object == null || getClass() != object.getClass()) {

			MapState state = (MapState) object;
			equals = identifier == state.getIdentifier();

		} else {

			equals = false;
		}

		return equals;
	}

	@Override
	public char getMostRecentCharacter() {
		return mostRecentCharacter;
	}

	@Override
	public State getMostRecentSuccessor() {
		return mostRecentSuccessor;
	}

	@Override
	public boolean isWord() {
		return word;
	}

	@Override
	public void setWord(boolean word) {
		this.word = word;
	}
}