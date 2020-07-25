package Snake;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class SnakeGame extends Applet implements Runnable, KeyListener
{
    public static int WINDOW_WIDTH = 400;
    public static int WINDOW_HEIGHT = 400;
    Graphics gfx;
    Image img;
    Thread thread;
    SnakeInstance playerOne;
    SnakeInstance playerTwo;

    List<SnakeInstance> players;
    Token token;

    public void init()
    {
        System.out.println("Initializing");
        this.resize(WINDOW_WIDTH,WINDOW_HEIGHT);
        img = createImage(WINDOW_WIDTH,WINDOW_HEIGHT);
        gfx = img.getGraphics();

        this.addKeyListener(this);

        players = new ArrayList<SnakeInstance>();

        //Player 1
        players.add(new SnakeInstance(100,100));
        players.get(0).setSnakeColor(Color.GREEN);

        //Player 2
        players.add(new SnakeInstance(100,200));
        players.get(1).setSnakeColor(Color.BLUE);

        token = new Token(players);

        thread = new Thread(this);
        thread.start();
    }

    public void checkKillSnake()
    {
        //for all snakes
        //Rules that kill snakes:
            //Edges touched
            //self Snake touched
            //other Snake touched

        //Change to loop eventually to add more generic support
        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).isAlive())
            {
                checkKillSnake(players.get(i), i);
            }
        }
    }

    private void checkKillSnake(SnakeInstance snake, int player_index)
    {
        if (snake.isAlive())
        {
            if (snake.getX() < 0 || snake.getX() > (WINDOW_WIDTH - 4))
            {
                //Kill snake 1
                snake.setAlive(false);
            }
            else if (snake.getY() < 0 || snake.getY() > (WINDOW_HEIGHT - 4))
            {
                //Kill snake 1
                snake.setAlive(false);
            }
        }

        //Check collision with all other snakes, including self
        for (int i = 0; i < players.size(); i++)
        {
            if (snake.snakeCollision(players.get(i))) //this WILL check against itself to handle self colisions.
            {
                snake.setAlive(false);
            }
        }

        //Check head collision with all other snakes
        for (int i = 0; i < players.size(); i++)
        {
            //Skip self so we don't "collide" with our own head.
            if (i != player_index)
            {
                if (snake.snakeHeadCollision(players.get(i))) //this WILL check against itself to handle self colisions.
                {
                    snake.setAlive(false);
                    players.get(i).setAlive(false);
                }
            }
        }
    }

    public void paint(Graphics g)
    {
        //Paint background to black always
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,400,400);

        for (int i = 0; i < players.size(); i++)
        {
            if (players.get(i).isAlive())
            {
                players.get(i).draw(gfx);
            }
        }

        token.draw(gfx);

        g.drawImage(img, 0, 0, null);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void repaint(Graphics g)
    {
        paint(g);
    }

    @Override
    public void run()
    {
        //Forever
        for(;;)
        {
            for (int i = 0; i < players.size(); i++)
            {
                if (players.get(i).isAlive())
                {
                    players.get(i).move();
                    token.snakeCollision(players.get(i));
                }
            }

            this.checkKillSnake();

            this.repaint();

            try
            {
                Thread.sleep(40);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {



    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (!players.get(0).isMoving() || !players.get(1).isMoving())
        {
            //hit any key -- default to right move
            players.get(0).setMoving(true);
            players.get(0).setXDir(1);

            players.get(1).setMoving(true);
            players.get(1).setXDir(1);
        }

        //Player 1 controls
        if (players.get(0).isAlive())
        {
            if (e.getKeyCode() == KeyEvent.VK_UP)
            {
                //if not going down, can move
                if (players.get(0).getYDir() != 1) {
                    players.get(0).setYDir(-1);
                    players.get(0).setXDir(0);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                //if not going up, can move
                if (players.get(0).getYDir() != -1) {
                    players.get(0).setYDir(1);
                    players.get(0).setXDir(0);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                //if not going left, can move
                if (players.get(0).getXDir() != 1) {
                    players.get(0).setYDir(0);
                    players.get(0).setXDir(-1);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                //if not going right, can move
                if (players.get(0).getXDir() != -1) {
                    players.get(0).setYDir(0);
                    players.get(0).setXDir(1);
                }
            }
        }


        //Player 2 controls
        if (players.get(1).isAlive())
        {
            if (e.getKeyCode() == KeyEvent.VK_W)
            {
                //if not going down, can move
                if (players.get(1).getYDir() != 1)
                {
                    players.get(1).setYDir(-1);
                    players.get(1).setXDir(0);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_S)
            {
                //if not going up, can move
                if (players.get(1).getYDir() != -1)
                {
                    players.get(1).setYDir(1);
                    players.get(1).setXDir(0);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_A)
            {
                //if not going left, can move
                if (players.get(1).getXDir() != 1)
                {
                    players.get(1).setYDir(0);
                    players.get(1).setXDir(-1);
                }
            }
            else if (e.getKeyCode() == KeyEvent.VK_D)
            {
                //if not going right, can move
                if (players.get(1).getXDir() != -1)
                {
                    players.get(1).setYDir(0);
                    players.get(1).setXDir(1);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
