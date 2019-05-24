package fr.guehenneux.scrabble;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Jonathan Guéhenneux
 */
public class State {

	private static int nextIdentifier = 0;

	private int identifier;
	private Map<Character, State> successors;
	private boolean word;
	private char mostRecentCharacter;
	private State mostRecentSuccessor;

	/**
	 *
	 */
	public State() {

		identifier = nextIdentifier++;
		successors = new LinkedHashMap<>();
		word = false;
	}

	/**
	 * iterate over state successors with given consumer
	 *
	 * @param consumer consumer to use
	 */
	public void forEach(BiConsumer<Character, State> consumer) {
		successors.forEach(consumer);
	}

	/**
	 * @param character a character
	 * @return successor associated with given character if it exists, {@code null} otherwise
	 */
	public State getSuccessor(char character) {
		return successors.get(character);
	}

	/**
	 * @return whether this state has at least 1 successor
	 */
	public boolean hasSuccessors() {
		return !successors.isEmpty();
	}

	/**
	 * associate a successor with a character
	 *
	 * @param character a character
	 * @param successor successor to associate with given character
	 */
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

			State state = (State) object;
			equals = identifier == state.identifier;

		} else {

			equals = false;
		}

		return equals;
	}

	/**
	 * @return most recently set character
	 */
	public char getMostRecentCharacter() {
		return mostRecentCharacter;
	}

	/**
	 * @return most recently set successor
	 */
	public State getMostRecentSuccessor() {
		return mostRecentSuccessor;
	}

	/**
	 * @return whether this state is a word
	 */
	public boolean isWord() {
		return word;
	}

	/**
	 * @param word whether this state is a word
	 */
	public void setWord(boolean word) {
		this.word = word;
	}

	/**
	 * @return state key
	 */
	public String getKey() {

		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(isWord());

		forEach((character, successor) -> {

			keyBuilder.append(character);
			keyBuilder.append(successor.identifier);
		});

		return keyBuilder.toString();
	}

	/**
	 * collect states into given set
	 *
	 * @param states set to collect states
	 */
	public void collectStates(Set<State> states) {

		states.add(this);
		forEach(((character, state) -> state.collectStates(states)));
	}
}