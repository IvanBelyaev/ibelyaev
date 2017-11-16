package ru.job4j.chess;

/**
 * Bishop.
 * @author Ivan Belyaev
 * @since 16.11.2017
 * @version 1.0
 */
public class Bishop extends Figure {
    /**
     * The constructor creates the object Bishop.
     * @param position - the cell in which the figure.
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * The method returns the path of movement of the figure.
     * If you cannot go to the specified cell throws an exception ImpossibleMoveException.
     * @param dist - the cell in which you want to go.
     * @return returns the path of movement of the figure.
     * @throws ImpossibleMoveException
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        int x = getPosition().getX();
        int y = getPosition().getY();
        int diffX = dist.getX() - x;
        int diffY = dist.getY() - y;

        if (Math.abs(diffX) != Math.abs(diffY)) {
            throw new ImpossibleMoveException("The bishop can not go there.");
        }

        Cell[] wayOfFigure = new Cell[Math.abs(diffX)];
        int incrementX = diffX / Math.abs(diffX);
        int incrementY = diffY / Math.abs(diffY);

        x += incrementX;
        y += incrementY;

        for (int i = 0; i < wayOfFigure.length; i++, x += incrementX, y += incrementY) {
            wayOfFigure[i] = new Cell(x, y);
        }

        return wayOfFigure;
    }
}
