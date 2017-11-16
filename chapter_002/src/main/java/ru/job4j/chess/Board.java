package ru.job4j.chess;

/**
 * Board.
 * @author Ivan Belyaev
 * @since 16.11.2017
 * @version 1.0
 */
public class Board {
    /** Array of figures mimics a chess Board. */
    private Figure[] figures = new Figure[64];



    /**
     * The method moves the figure.
     * @param source - the cell from which you want to move the figure.
     * @param dist - the cell in which you want to move the figure.
     * @return returns true if move succeeded.
     * @throws ImpossibleMoveException - throws when a figure cannot go to the specified cell.
     * @throws OccupiedWayException - throws when the path is busy figures.
     * @throws FigureNotFoundException - throws when the specified cell has no figure.
     */
    public boolean move(Cell source, Cell dist)
            throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int start = source.getX() + source.getY() * 8;
        int finish = dist.getX() + dist.getY() * 8;

        if (figures[start] == null) {
            throw new FigureNotFoundException("In a given cell figure not found.");
        }

        Cell[] wayOfFigure = figures[start].way(dist);

        for (Cell cell : wayOfFigure) {
            if (figures[cell.getX() + cell.getY() * 8] != null) {
                throw new OccupiedWayException("Way of figure is busy.");
            }
        }

        figures[finish] = figures[start];
        figures[start] = null;

        return true;
    }

    /**
     * The method puts a figure on the chessboard.
     * @param figure - chess figure.
     */
    public void setFigure(Figure figure) {
        figures[figure.getPosition().getX() + figure.getPosition().getX() * 8] = figure;
    }
}
