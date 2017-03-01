package snake;

// code tutorial: https://www.youtube.com/watch?v=S_n3lryyGZM
// make jar file: https://www.youtube.com/watch?v=3Xo6zSBgdgk
// make exe file with launch4j : https://www.youtube.com/watch?v=NRptmWyrvvo

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snake implements ActionListener, KeyListener {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                snake = new Snake();
            }
        });
    }


    public static Snake snake;

    public JFrame jframe;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20, this);

    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10; // you usually set final thing in ALL CAPS

    public int ticks = 0, direction = DOWN, score, taillength = 10, time ;

    public Point head, cherry;

    public Random random;

    public boolean over = false, paused;

    public Dimension dim;

    private URL iconURL = getClass().getResource("Snake.png");
    // iconURL is null when not found
    private ImageIcon icon = new ImageIcon(iconURL);

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("snake");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.setTitle("Snake");
        jframe.setIconImage(icon.getImage());
        jframe.setResizable((false));
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame(){
        over = false;
        paused = false;
        score = 0;
        taillength = 5;
        ticks = 0;
        time = 0;
        direction = DOWN;
        head = new Point (0,-1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    @Override
    public void actionPerformed (ActionEvent arg0){
        //System.out.println(head.x + ", " + head.y);
        //System.out.println(cherry.x + ", " + cherry.y);
        renderPanel.repaint();
        ticks++;
        // snelheid
        if (ticks % 2 == 0 && head != null && !over && !paused){
            time++;
            snakeParts.add(new Point(head.x, head.y));


            if (direction == UP)
               if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                   head = new Point(head.x, head.y - 1);
               else
                   over = true;

            if (direction == DOWN)
                if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
                    head = new Point(head.x, head.y + 1);
                else
                    over = true;

            if (direction == LEFT)
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                    head = new Point(head.x - 1, head.y);
                else
                    over = true;

            if (direction == RIGHT)
                if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y))
                    head = new Point(head.x + 1, head.y);
                else
                    over = true;


            if (snakeParts.size() > taillength)
                snakeParts.remove(0);

            if (cherry != null) {
                //if (head.x == cherry.x && head.y == cherry.y)
                if (head.equals(cherry)){
                    score += 10;
                    taillength ++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                //System.out.println("collided");
                return false;
            }
        }
        return true;
    }




    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_LEFT && direction != RIGHT)
            direction = LEFT;
        if (i == KeyEvent.VK_RIGHT && direction != LEFT)
            direction = RIGHT;
        if (i == KeyEvent.VK_UP && direction != DOWN)
            direction = UP;
        if (i == KeyEvent.VK_DOWN && direction != UP)
            direction = DOWN;
        if (i == KeyEvent.VK_SPACE)
            if (over)
                startGame();
            else
                paused = !paused;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
