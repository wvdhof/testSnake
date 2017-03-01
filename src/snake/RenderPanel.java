package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;




@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    // ANIMATED:
    // public static int curColor = 0;

    // FIXED colorpicker > rgb value > calculator > view > programmer > ()hex > paste rgb > ()dec :
    public static Color green = new Color(1666073);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // FIXED self selected:
        g.setColor(green);

        // FIXED from color library:
        //g.setColor(Color.BLACK);

        // ANIMATED:
        // g.setColor(new Color(curColor));

        g.fillRect(0, 0, 800, 700);

        // ANIMATED:
        // curColor ++;

        Snake snake = Snake.snake;
        g.setColor(Color.BLUE);
        for (Point point : snake.snakeParts) {
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);

        g.setColor(Color.RED);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        String string = "Score: " + snake.score + ", length:" + snake.taillength + ", Time: " + snake.time / 20;
        g.setColor(Color.white);
        g.drawString(string, (int) (getWidth() / 2 - string.length() * 1.5f), 10);
    }

}


