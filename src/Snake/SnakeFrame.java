package Snake;

import java.awt.*;
import java.awt.event.*;

public class SnakeFrame extends Frame
{
    public SnakeFrame()
    {
        System.out.printf("Making Snake Frame.");
        SnakeGame game = new SnakeGame();

        //Handle window close signal so we can actually shutdown on 'X' click.
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        this.setSize(450,450);
        this.add(game, BorderLayout.CENTER);
        this.setVisible(true);

        //Initialize game and begin running.
        game.init();
    }

    public static void main(String[] args)
    {
        new SnakeFrame();
    }
}
