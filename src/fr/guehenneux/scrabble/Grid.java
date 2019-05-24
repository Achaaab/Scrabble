package fr.guehenneux.scrabble;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author Jonathan Guéhenneux
 */
public class Grid implements Iterable<Square> {

  private static final int CLASSICAL_WIDTH = 15;
  private static final int CLASSICAL_HEIGHT = 15;

  private int width;
  private int height;

  private Square[][] squares;

  /**
   * Create a classical grid.
   */
  public Grid() {

    this(CLASSICAL_WIDTH, CLASSICAL_HEIGHT);

    squares[3][0].setAward(Award.DOUBLE_LETTER);
    squares[11][0].setAward(Award.DOUBLE_LETTER);
    squares[6][2].setAward(Award.DOUBLE_LETTER);
    squares[8][2].setAward(Award.DOUBLE_LETTER);
    squares[0][3].setAward(Award.DOUBLE_LETTER);
    squares[7][3].setAward(Award.DOUBLE_LETTER);
    squares[14][3].setAward(Award.DOUBLE_LETTER);
    squares[2][6].setAward(Award.DOUBLE_LETTER);
    squares[6][6].setAward(Award.DOUBLE_LETTER);
    squares[8][6].setAward(Award.DOUBLE_LETTER);
    squares[12][6].setAward(Award.DOUBLE_LETTER);
    squares[3][7].setAward(Award.DOUBLE_LETTER);
    squares[11][7].setAward(Award.DOUBLE_LETTER);
    squares[2][8].setAward(Award.DOUBLE_LETTER);
    squares[6][8].setAward(Award.DOUBLE_LETTER);
    squares[8][8].setAward(Award.DOUBLE_LETTER);
    squares[12][8].setAward(Award.DOUBLE_LETTER);
    squares[0][11].setAward(Award.DOUBLE_LETTER);
    squares[7][11].setAward(Award.DOUBLE_LETTER);
    squares[14][11].setAward(Award.DOUBLE_LETTER);
    squares[6][12].setAward(Award.DOUBLE_LETTER);
    squares[8][12].setAward(Award.DOUBLE_LETTER);
    squares[3][14].setAward(Award.DOUBLE_LETTER);
    squares[11][14].setAward(Award.DOUBLE_LETTER);

    squares[5][1].setAward(Award.TRIPLE_LETTER);
    squares[9][1].setAward(Award.TRIPLE_LETTER);
    squares[1][5].setAward(Award.TRIPLE_LETTER);
    squares[5][5].setAward(Award.TRIPLE_LETTER);
    squares[9][5].setAward(Award.TRIPLE_LETTER);
    squares[13][5].setAward(Award.TRIPLE_LETTER);
    squares[1][9].setAward(Award.TRIPLE_LETTER);
    squares[5][9].setAward(Award.TRIPLE_LETTER);
    squares[9][9].setAward(Award.TRIPLE_LETTER);
    squares[13][9].setAward(Award.TRIPLE_LETTER);
    squares[5][13].setAward(Award.TRIPLE_LETTER);
    squares[9][13].setAward(Award.TRIPLE_LETTER);

    squares[1][1].setAward(Award.DOUBLE_WORD);
    squares[13][1].setAward(Award.DOUBLE_WORD);
    squares[2][2].setAward(Award.DOUBLE_WORD);
    squares[12][2].setAward(Award.DOUBLE_WORD);
    squares[3][3].setAward(Award.DOUBLE_WORD);
    squares[11][3].setAward(Award.DOUBLE_WORD);
    squares[4][4].setAward(Award.DOUBLE_WORD);
    squares[10][4].setAward(Award.DOUBLE_WORD);
    squares[5][5].setAward(Award.DOUBLE_WORD);
    squares[9][5].setAward(Award.DOUBLE_WORD);
    squares[6][6].setAward(Award.DOUBLE_WORD);
    squares[8][6].setAward(Award.DOUBLE_WORD);
    squares[7][7].setAward(Award.DOUBLE_WORD);
    squares[6][8].setAward(Award.DOUBLE_WORD);
    squares[8][8].setAward(Award.DOUBLE_WORD);
    squares[5][9].setAward(Award.DOUBLE_WORD);
    squares[9][9].setAward(Award.DOUBLE_WORD);
    squares[4][10].setAward(Award.DOUBLE_WORD);
    squares[10][10].setAward(Award.DOUBLE_WORD);
    squares[3][11].setAward(Award.DOUBLE_WORD);
    squares[11][11].setAward(Award.DOUBLE_WORD);
    squares[2][12].setAward(Award.DOUBLE_WORD);
    squares[12][12].setAward(Award.DOUBLE_WORD);
    squares[1][13].setAward(Award.DOUBLE_WORD);
    squares[13][13].setAward(Award.DOUBLE_WORD);

    squares[0][0].setAward(Award.TRIPLE_WORD);
    squares[7][0].setAward(Award.TRIPLE_WORD);
    squares[14][0].setAward(Award.TRIPLE_WORD);
    squares[0][7].setAward(Award.TRIPLE_WORD);
    squares[14][7].setAward(Award.TRIPLE_WORD);
    squares[0][14].setAward(Award.TRIPLE_WORD);
    squares[7][14].setAward(Award.TRIPLE_WORD);
    squares[14][14].setAward(Award.TRIPLE_WORD);

    squares[7][7].setStarting(true);
  }

  /**
   * @param width
   * @param height
   */
  public Grid(int width, int height) {

    this.width = width;
    this.height = height;

    squares = new Square[width][height];

    for (int column = 0; column < width; column++) {
      for (int row = 0; row < height; row++) {
        squares[column][row] = new Square(column, row);
      }
    }
  }

  /**
   * @param bag
   */
  public void empty(Bag bag) {

    for (Square square : this) {

      if (square.containsTile()) {
        bag.add(square.removeTile());
      }
    }
  }

  /**
   * @return number of columns
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return number of rows
   */
  public int getHeight() {
    return height;
  }

  /**
   * @param column
   * @param row
   */
  public Square getSquare(int column, int row) {
    return squares[column][row];
  }

  /**
   * @param square
   * @param orientation
   * @return
   */
  public Square getNextSquare(Square square, Orientation orientation) {

    int column = square.getColumn();
    int row = square.getRow();

    Square nextSquare;

    switch (orientation) {

    case HORIZONTAL:
      nextSquare = column + 1 < width ? squares[column + 1][row] : null;
      break;

    case VERTICAL:
      nextSquare = row + 1 < height ? squares[column][row + 1] : null;
      break;

    default:
      nextSquare = null;
      break;
    }

    return nextSquare;
  }

  /**
   * @param square
   * @param orientation
   * @return
   */
  public Square getPreviousSquare(Square square, Orientation orientation) {

    int column = square.getColumn();
    int row = square.getRow();

    Square previousSquare;

    switch (orientation) {

    case HORIZONTAL:
      previousSquare = column > 0 ? squares[column - 1][row] : null;
      break;

    case VERTICAL:
      previousSquare = row > 0 ? squares[column][row - 1] : null;
      break;

    default:
      previousSquare = null;
      break;
    }

    return previousSquare;
  }

  /**
   * @param square
   * @param orientation
   * @return
   */
  public Square getNextEmptySquare(Square square, Orientation orientation) {

    Square nextSquare = getNextSquare(square, orientation);

    while (nextSquare != null && nextSquare.containsTile()) {
      nextSquare = getNextSquare(nextSquare, orientation);
    }

    return nextSquare;
  }

  /**
   * @param square
   * @param orientation
   * @return
   */
  public Square getPreviousEmptySquare(Square square, Orientation orientation) {

    Square previousSquare = getPreviousSquare(square, orientation);

    while (previousSquare != null && previousSquare.containsTile()) {
      previousSquare = getPreviousSquare(previousSquare, orientation);
    }

    return previousSquare;
  }

  /**
   * @param tiles tiles to play
   * @param firstSquare
   * @param orientation
   * @return
   */
  public void play(List<Tile> tiles, Square firstSquare, Orientation orientation) {

    int score = 0;

    int mainWordScore = 0;
    int letterMultiplier;
    int wordMultiplier;
    int mainWordMultiplier = 1;

    Orientation perpendicular = orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    Square perpendicularSquare;
    StringBuilder perpendicularWord;
    int perpendicularWordScore;
    int perpendicularWordMultiplier;

    StringBuilder mainWord = new StringBuilder();

    // prefix

    Square square = getPreviousSquare(firstSquare, orientation);
    Award award;
    Tile tile;
    char letter;
    int value;

    while (square != null && square.containsTile()) {

      tile = square.getTile();
      value = tile.getValue();
      letter = tile.getLetter();

      mainWord.insert(0, letter);
      mainWordScore += value;

      square = getPreviousSquare(square, orientation);
    }

    // played tiles

    Iterator<Tile> tileIterator = tiles.iterator();
    square = firstSquare;

    while (tileIterator.hasNext()) {

      tile = tileIterator.next();
      letter = tile.getLetter();
      mainWord.append(letter);

      award = square.getAward();
      letterMultiplier = award.getLetterMultiplier();
      wordMultiplier = award.getWordMultiplier();
      mainWordMultiplier *= wordMultiplier;

      value = tile.getValue();
      mainWordScore += value * letterMultiplier;

      // perpendicular word

      perpendicularWord = new StringBuilder();
      perpendicularWord.append(letter);
      perpendicularWordScore = value * letterMultiplier;
      perpendicularWordMultiplier = wordMultiplier;

      perpendicularSquare = getPreviousSquare(square, perpendicular);
      while (perpendicularSquare != null && perpendicularSquare.containsTile()) {

        tile = perpendicularSquare.getTile();
        letter = tile.getLetter();
        value = tile.getValue();

        perpendicularWord.insert(0, letter);
        perpendicularWordScore += value;

        perpendicularSquare = getPreviousSquare(perpendicularSquare, perpendicular);
      }

      perpendicularSquare = getNextSquare(square, perpendicular);
      while (perpendicularSquare != null && perpendicularSquare.containsTile()) {

        tile = perpendicularSquare.getTile();
        letter = tile.getLetter();
        value = tile.getValue();

        perpendicularWord.append(letter);
        perpendicularWordScore += value;

        perpendicularSquare = getNextSquare(perpendicularSquare, perpendicular);
      }

      if (perpendicularWord.length() > 1) {

        perpendicularWordScore *= perpendicularWordMultiplier;
        //System.out.println("perpendicular word: " + perpendicularWord + "(" + perpendicularWordScore + ")");
      }

      square = getNextSquare(square, orientation);
      while (square != null && square.containsTile()) {

        tile = square.getTile();
        value = tile.getValue();
        letter = tile.getLetter();

        mainWord.append(letter);
        mainWordScore += value;

        square = getNextSquare(square, orientation);
      }
    }

    // suffix

    while (square != null && square.containsTile()) {

      tile = square.getTile();
      value = tile.getValue();
      letter = tile.getLetter();

      mainWord.append(letter);
      mainWordScore += value;

      square = getNextSquare(square, orientation);
    }

    mainWordScore *= mainWordMultiplier;

    //System.out.println("main word: " + mainWord + "(" + mainWordScore + ")");
  }

  @Override
  public Iterator<Square> iterator() {
    return new GridIterator(this);
  }
}