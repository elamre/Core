package com.deeep.core.entity.types;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.core.entity.Direction;
import com.deeep.core.entity.abstraction.Entity;
import com.deeep.core.entity.abstraction.Manager;
import com.deeep.core.util.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 11/10/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Snake {
    public Manager manager;
    /** The head of the snake */
    private Head head;
    /** An array list containing all the tail parts */
    private ArrayList<Tail> tails = new ArrayList<Tail>();
    /** The last tail piece */
    private Tail tail;
    private int x, y;
    /** The new direction of the snake */
    private Direction newDir = Direction.EAST;
    /** The direction the snake is heading in */
    private Direction curDir = Direction.EAST;
    /** The previous direction of the snake, used for checking */
    private Direction prevDir = Direction.NORTH;

    /** If the snake has moved since previous check */

    public Snake(Manager manager) {
        this.manager = manager;
        Head head = new Head();
        tail = new Tail();
        tail.setEnd(true);
        tails.add(new Tail());
        tails.add(new Tail());
        tails.add(new Tail());
        manager.addEntity(head);
        for (Tail tail : tails) {
            manager.addEntity(tail);
        }
        manager.addEntity(tail);
        setHead(head);
    }

    public void addTail(Tail tail) {
        tails.add(tail);
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    /** Moves the snake and with the tail */
    public void move() {
        int i = tails.size() - 1;
        tail.setX(tails.get(i).getX());
        tail.setY(tails.get(i).getY());
        while (i > 0) {
            tails.get(i).setX(tails.get(i - 1).getX());
            tails.get(i).setY(tails.get(i - 1).getY());
            tails.get(i).setDirection(tails.get(i - 1).getDirection());
            i--;
        }
        if (tails.size() > 0) {
            tails.get(0).setX(x);
            tails.get(0).setY(y);

        }

        calculateDirection();

        x += curDir.getVector().x;
        y += curDir.getVector().y;

        head.setX(x);
        head.setY(y);

        //tailAngleCalculate();
        // moved = true;
        prevDir = curDir;
        // tail.setDirection(tails.get(tails.size() - 1).getDirection().getOpposite());
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * new direction of the snake
     *
     * @param dir new direction
     */
    public void setDirection(Direction dir) {
        this.newDir = dir;
    }

    /**
     * Calculates the new x and y position for the client. This will make sure that the head wont move to the previous
     * position resulting in an instant death.
     */
    public void calculateDirection() {
        if (newDir != curDir) {
            switch (newDir) {
                case NORTH:
                    if (curDir != Direction.SOUTH)
                        curDir = Direction.NORTH;
                    break;
                case EAST:
                    if (curDir != Direction.WEST)
                        curDir = Direction.EAST;
                    break;
                case SOUTH:
                    if (curDir != Direction.NORTH)
                        curDir = Direction.SOUTH;
                    break;
                case WEST:
                    if (curDir != Direction.EAST)
                        curDir = Direction.WEST;
                    break;
            }
        }
        head.setDirection(curDir.getValue());
    }

    public Direction getDir() {
        return curDir;
    }
}
