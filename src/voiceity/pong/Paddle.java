package voiceity.pong;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;

public class Paddle extends Rectangle {
    private int id;

    int yVelocity;

    int speed = 10;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(new Color(0, 0, 255));
        } else if (id == 2) {
            g.setColor(new Color(255, 0, 0));
        }

        g.fillRect(x, y, width, height);
    }

    public void move() {
        y += yVelocity;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyRelased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
        }
    }
}