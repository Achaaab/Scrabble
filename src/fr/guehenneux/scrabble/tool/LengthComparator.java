package fr.guehenneux.scrabble.tool;

import java.util.Comparator;

/**
 * @author Jonathan Guéhenneux
 */
public class LengthComparator implements Comparator<String> {

	public static final LengthComparator INSTANCE = new LengthComparator();

	/**
	 *
	 */
	private LengthComparator() {

	}

	@Override
	public int compare(String word0, String word1) {

		int comparison;

		int length0 = word0.length();
		int length1 = word1.length();

		if (length0 == length1) {
			comparison = word0.compareTo(word1);
		} else {
			comparison = length1 - length0;
		}

		return comparison;
	}
}