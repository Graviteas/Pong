package voiceity.pong;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;

import java.awt.event.KeyEvent;

import java.util.Random;

public class Content extends JPanel implements Runnable {
    private final int GAME_WIDTH = 1000;

    public final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.555));

    private final Dimension SCREEN_SIZE = new Dimensionn(GAME_WIDTH, GAME_HEIGHT);

    private final int BALL_DIAMETER = 20;

    private final int PADDLE_WIDTH = 25;

    private final int PADDLE_HEIGHT = (PADDLE_WIDTH * 4);
    private Image image;

    private Grapics graphics;

    private Random random;

    private Thread displayThread;

    protected Paddle paddle1, paddle2;

    protected Ball ball;

    protected Score score;

    public Content() {
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        newPaddles();
        newBall();

        displayThread = new Thread(this);
        displayThread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt((GAME_HEIGHT - BALL_DIAMETER)), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle((GAME_WIDTH - PADDLE_WIDTH), (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolKit().sync();
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollison() {
        if (paddle1.y <= 0) {
            paddle1.y = 0;

        } else if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }

        if (paddle2.y <= 0) {
            paddle2.y = 0;

        } else if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
        }

        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);

        } else if (ball.y >= (GAME_HEIGHT - BALL_DIAMETER)) {
            ball.setYDirection(-ball.yVelocity);
        }

        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            if (ball.yVelocity > 0) {
                ball.yVelocity++;

            } else {
                ball.yVelocity--;
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(-ball.yVelocity);

        } else if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;

            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                ball.yVelocity--;
            }

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Log score plr2: " + score.player2);
        } else if (ball.x >= (GAME_WIDTH - BALL_DIAMETER)) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Log score plr1: " + score.player1);
        }
    }

    public void run() {
        long lastNanoSecond = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastNanoSecond) / nanoSeconds;
            lastNanoSecond = currentTime;

            if (delta >= 1) {
                move();
                checkCollison();
                repaint();
                delta--;
            }
        }
    }

    public class ActionListener extends KeyAdapter {
        @Overirde
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
    }
}