package voiceity.pong;

import java.awt.Color;
import java.awt.Font;

public class Score {
    static int GAME_WIDTH, GAME_HEIGHT;

    int player1, player2;

    public Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine((GAME_WIDTH / 2), 0, (GAME_WIDTH / 2), GAME_HEIGHT);
        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), ((GAME_WIDTH / 2) - 90), 50);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), ((GAME_WIDTH / 2) + 20), 50);
    }
}