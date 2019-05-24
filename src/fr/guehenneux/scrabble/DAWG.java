package fr.guehenneux.scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Directed Acyclic Word Graph
 *
 * @author Jonathan Guéhenneux
 */
public class DAWG {

	/**
	 * @param word0
	 * @param word1
	 * @return
	 */
	private static String getCommonPrefix(String word0, String word1) {

		int minLength = Math.min(word0.length(), word1.length());

		int index = 0;
		char char0;
		char char1;
		boolean samePrefix = true;

		while (samePrefix && index < minLength) {

			char0 = word0.charAt(index);
			char1 = word1.charAt(index);

			if (char0 == char1) {
				index++;
			} else {
				samePrefix = false;
			}
		}

		return word0.substring(0, index);
	}

	private State initialState;

	/**
	 * @param words words alphabetically sorted
	 */
	public DAWG(List<String> words) {

		initialState = new State();
		insertWords(words);
	}

	/**
	 * @param string
	 * @return
	 */
	private State getState(String string) {

		State state = initialState;

		int index = 0;
		int length = string.length();

		while (index < length && state != null) {

			char character = string.charAt(index++);
			state = state.getSuccessor(character);
		}

		return state;
	}

	/**
	 * @param word
	 * @return whether this DAWG contains the given word
	 */
	public boolean contains(String word) {

		State state = getState(word);
		return state != null && state.isWord();
	}

	/**
	 * @param prefix
	 * @return whether this DAWG contains at least 1 word starting with given prefix
	 */
	public boolean prefixExists(String prefix) {

		State state = getState(prefix);
		return state != null;
	}

	/**
	 * @param prefix
	 * @return the list of words starting with given prefix
	 */
	public List<String> prefixSearch(String prefix) {

		List<String> words = new ArrayList<>();
		State state = getState(prefix);

		int length = prefix.length();
		char[] characters = Arrays.copyOf(prefix.toCharArray(), 15);

		if (state != null) {
			prefixSearch(characters, length, state, words);
		}

		return words;
	}

	/**
	 * @param characters
	 * @param length
	 * @param state
	 * @param words
	 */
	private void prefixSearch(char[] characters, int length, State state, List<String> words) {

		if (state.isWord()) {
			words.add(new String(characters, 0, length));
		}

		state.forEach((character, successor) -> {

			characters[length] = character;
			prefixSearch(characters, length + 1, successor, words);
		});
	}

	/**
	 * @param words
	 */
	private void insertWords(List<String> words) {

		Map<String, State> register = new HashMap<>();
		String previousWord = "";

		for (String word : words) {

			insertWord(previousWord, word, register);
			previousWord = word;
		}

		replaceOrRegister(initialState, register);
	}

	/**
	 * @param previousWord
	 * @param word
	 * @param register
	 */
	private void insertWord(String previousWord, String word, Map<String, State> register) {

		String prefix = getCommonPrefix(word, previousWord);
		String suffix = word.substring(prefix.length());

		State state = getState(prefix);

		if (state.hasSuccessors()) {
			replaceOrRegister(state, register);
		}

		addSufix(state, suffix);
	}

	/**
	 * @param state
	 * @param suffix
	 */
	private void addSufix(State state, String suffix) {

		int length = suffix.length();

		for (int index = 0; index < length; index++) {

			char character = suffix.charAt(index);

			State successor = new State();
			state.setSuccessor(character, successor);
			state = successor;
		}

		state.setWord(true);
	}

	/**
	 * @param state
	 * @param register
	 */
	private void replaceOrRegister(State state, Map<String, State> register) {

		char mostRecentCharacter = state.getMostRecentCharacter();
		State successor = state.getMostRecentSuccessor();

		if (successor.hasSuccessors()) {
			replaceOrRegister(successor, register);
		}

		String successorKey = successor.getKey();
		State registeredState = register.get(successorKey);

		if (registeredState == null) {
			register.put(successorKey, successor);
		} else {
			state.setSuccessor(mostRecentCharacter, registeredState);
		}
	}

	/**
	 * @return the set of states in the DAWG
	 */
	public Set<State> getStates() {

		Set<State> states = new HashSet<>();
		initialState.collectStates(states);
		return states;
	}
}