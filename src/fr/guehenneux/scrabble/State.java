package fr.guehenneux.scrabble;

import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author Jonathan Guéhenneux
 */
public interface State {

	int getIdentifier();

	/**
	 * @return state key
	 */
	default String getKey() {

		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(isWord());

		forEach((character, successor) -> {

			keyBuilder.append(character);
			keyBuilder.append(successor.getIdentifier());
		});

		return keyBuilder.toString();
	}

	/**
	 * forEach over state successors with given consumer
	 *
	 * @param consumer consumer to use
	 */
	void forEach(BiConsumer<Character, State> consumer);

	/**
	 * @param character a character
	 * @return successor associated with given character if it exists, {@code null} otherwise
	 */
	State getSuccessor(char character);

	/**
	 * @return whether this state has at least 1 successor
	 */
	boolean hasSuccessors();

	/**
	 * associate a successor with a character
	 *
	 * @param character a character
	 * @param successor successor to associate with given character
	 */
	void setSuccessor(char character, State successor);

	/**
	 * @return most recently set character
	 */
	char getMostRecentCharacter();

	/**
	 * @return most recently set successor
	 */
	State getMostRecentSuccessor();

	/**
	 * @return whether this state is a word
	 */
	boolean isWord();

	/**
	 * @param word whether this state is a word
	 */
	void setWord(boolean word);

	/**
	 * collect states into given set
	 *
	 * @param states set to collect states
	 */
	default void collectStates(Set<State> states) {

		states.add(this);
		forEach(((character, state) -> state.collectStates(states)));
	}
}