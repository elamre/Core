package com.deeep.core.network.server;

import com.deeep.core.entity.Direction;
import com.deeep.core.entity.abstraction.Entity;
import com.deeep.core.entity.abstraction.Manager;
import com.deeep.core.entity.types.Snake;
import com.deeep.core.entity.types.Tail;
import com.deeep.core.entity.types.TestEntity;
import com.deeep.core.network.mutual.ControlsKeyId;
import com.esotericsoftware.kryonet.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 11/1/13
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private PlayerControlHandler playerControlHandler;
    private float moveTimer = 0;
    private float speed = 0.2f;
    private boolean previousAction = false;
    private Snake snake;
    private Connection connection;
    private Manager manager;

    public Player(Connection connection, Manager manager) {
        playerControlHandler = new PlayerControlHandler();
        this.connection = connection;
        this.manager = manager;
        snake = new Snake(manager);
    }

    public void update(float deltaT) {
            if (playerControlHandler.isPressed()) {
                if (playerControlHandler.isKeyPressed(ControlsKeyId.UP)) {
                    snake.setDirection(Direction.NORTH);
                }
                if (playerControlHandler.isKeyPressed(ControlsKeyId.DOWN)) {
                    snake.setDirection(Direction.SOUTH);
                }
                if (playerControlHandler.isKeyPressed(ControlsKeyId.LEFT)) {
                    snake.setDirection(Direction.WEST);
                }
                if (playerControlHandler.isKeyPressed(ControlsKeyId.RIGHT)) {
                    snake.setDirection(Direction.EAST);
                }
            }
            moveTimer += deltaT;
            if (moveTimer >= speed) {
                snake.move();
                moveTimer -= speed;
            }
        if (!previousAction && playerControlHandler.isKeyPressed(ControlsKeyId.ACTION)) {
            snake.setPosition(5, 5);
        }
        if(snake.getHead().getMapX() > 20)
            snake.setPosition(1, snake.getHead().getMapY());
        previousAction = playerControlHandler.isKeyPressed(ControlsKeyId.ACTION);
    }

    public PlayerControlHandler getPlayerControlHandler() {
        return playerControlHandler;
    }
}
