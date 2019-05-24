package fr.guehenneux.scrabble;

/**
 * @author Jonathan Guéhenneux
 */
public class Square {

  private int column;
  private int row;
  private Award award;
  private boolean starting;
  private Tile tile;


  /**
   * @param column
   * @param row
   */
  public Square(int column, int row) {

    this.column = column;
    this.row = row;

    award = Award.NONE;
    starting = false;
    tile = null;
  }

  /**
   * @return
   */
  public int getColumn() {
    return column;
  }

  /**
   * @return
   */
  public int getRow() {
    return row;
  }

  /**
   * @return whether this square contains a tile
   */
  public boolean containsTile() {
    return tile != null;
  }

  /**
   * @return
   */
  public Tile getTile() {
    return tile;
  }

  /**
   * @param tile
   */
  public void setTile(Tile tile) {
    this.tile = tile;
  }

  /**
   * @return
   */
  public Tile removeTile() {

    Tile removedTile = tile;
    tile = null;
    return tile;
  }

  /**
   * @return whether this square is a starting square
   */
  public boolean isStarting() {
    return starting;
  }

  /**
   * @param starting whether this square is a starting square
   */
  public void setStarting(boolean starting) {
    this.starting = starting;
  }

  /**
   * @return award
   */
  public Award getAward() {
    return award;
  }

  /**
   * @param award
   */
  public void setAward(Award award) {
    this.award = award;
  }
}