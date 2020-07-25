package Snake;

import java.awt.*;
import java.awt.event.*;

public class SnakeFrame extends Frame implements WindowListener
{
    public SnakeFrame()
    {
        System.out.printf("Making Snake Frame.");
        addWindowListener(this);
        SnakeGame game = new SnakeGame();
        Frame myFrame = new Frame("Snake Game");

        myFrame.setSize(450,450);

        myFrame.add(game, BorderLayout.CENTER);

        myFrame.setVisible(true);

        game.init();

    }

    public static void main(String[] args)
    {
        new SnakeFrame();
    }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e)
    {
        System.out.println("closing");
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }
}
