package Snake;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SnakeFrame extends JFrame
{

    public SnakeFrame()
    {
        System.out.printf("Making Snake Frame.");
        SnakeGame game = new SnakeGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(game, BorderLayout.CENTER);

        this.setLayout(new CardLayout(10,10));

        this.setSize(450,475);
        this.setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        this.setVisible(true);

        //Initialize game and begin running.
        game.init();
    }

    public static void main(String[] args)
    {
        new SnakeFrame();
    }
}
