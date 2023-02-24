package voiceity.pong;

import java.util.Random;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Ball extends Rectangle {
    Random random;

    int xVelocity;

    int yVelocity;

    int initialSpeed = 2;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);

        random = new Random();

        int randomXDirection = random.nextInt(2);
        int randomYDirection = random.nextInt(2);

        if (randomXDirection == 0) {
            randomXDirection--;
        }

        setXDirection(randomXDirection * initialSpeed);

        if (randomYDirection == 0) {
            randomYDirection--;
        }

        setYDirection(randomYDirection * initialSpeed);
    }

    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x, y, width, height);
    }
}