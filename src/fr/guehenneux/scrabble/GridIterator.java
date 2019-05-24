package fr.guehenneux.scrabble;

import java.util.Iterator;

/**
 * @author Jonathan Guéhenneux
 */
public class GridIterator implements Iterator<Square> {

	private Grid grid;

	private int width;
	private int height;
	private int column;
	private int row;

	/**
	 * @param grid
	 */
	public GridIterator(Grid grid) {

		this.grid = grid;

		width = grid.getWidth();
		height = grid.getHeight();

		column = 0;
		row = 0;
	}

	@Override
	public boolean hasNext() {
		return row < height;
	}

	@Override
	public Square next() {

		Square next = grid.getSquare(column, row);

		column++;

		if (column == width) {

			column = 0;
			row++;
		}

		return next;
	}
}