package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SnakeInstance
{
    List<Point> snakePoints;
    int xDir, yDir;
    Color snakeColor;

    boolean isMoving, elongate;
    final int STARTSIZE = 20;
    int startX, startY;

    boolean alive;

    public SnakeInstance(int startX, int startY)
    {
        snakePoints = new ArrayList<Point>();
        alive = true;
        xDir = 0;
        yDir = 0;

        isMoving = false;
        elongate = false;

        this.startX = startX;
        this.startY = startY;

        snakePoints.add(new Point(startX, startY));
        for (int i = 1; i < STARTSIZE; i++)
        {
            snakePoints.add(new Point(startX - i * 4, startY)); //put body points out to the left
        }

    }

    public void draw(Graphics g)
    {
        g.setColor(snakeColor);
        boolean large = true;
        for (Point p: snakePoints)
        {
            if (large == true)
            {
                large = false;
                g.fillRect(p.getX()+1, p.getY()+1, 6,6);
            }
            else
            {
                large = true;
                g.fillRect(p.getX(), p.getY(), 4,4);
            }
        }
    }

    public void move()
    {
        if (isMoving)
        {
            Point temp = snakePoints.get(0); //record start of snake
            Point last_temp = snakePoints.get(snakePoints.size() - 1);

            Point newStart = new Point(temp.getX() + xDir * 4, temp.getY() + yDir * 4); //make new head

            //update all other points, start from end
            for (int i = snakePoints.size() - 1; i >= 1; i--)
            {
                snakePoints.set(i, snakePoints.get(i - 1));
            }

            //Actually update head
            snakePoints.set(0, newStart);
            if (elongate)
            {
                snakePoints.add(last_temp); //re-add last point
                elongate = false;
            }
        }
    }

    public boolean snakeCollision(SnakeInstance otherSnake)
    {
        int x = this.getX();
        int y = this.getY();

        for (int i = 1; i < otherSnake.snakePoints.size(); i++)
        {
            if (otherSnake.isAlive() && otherSnake.snakePoints.get(i).getX() == x && otherSnake.snakePoints.get(i).getY() == y)
                return  true;
        }

        return false;
    }

    public boolean snakeHeadCollision(SnakeInstance otherSnake)
    {
        //Special case for if the heads crash into each other, this kills both snakes

        if (otherSnake.isAlive() && this.getX() == otherSnake.snakePoints.get(0).getX() && this.getY() == otherSnake.snakePoints.get(0).getY())
            return true;

        return false;
    }

    /**
     *
     * @return Returns moving direction of snake in X-Axis
     */
    public int getXDir()
    {
        return xDir;
    }

    /**
     *
     * @return Returns moving direction of snake in Y-Axis
     */
    public int getYDir()
    {
        return yDir;
    }

    /**
     *
     * @return Returns X position of snake's head.
     */
    public int getX()
    {
        return snakePoints.get(0).getX();
    }

    /**
     *
     * @return Returns Y position of snake's head.
     */
    public int getY()
    {
        return snakePoints.get(0).getY();
    }

    /**
     *
     * @return Returns boolean representing if the snake has started moving or not.
     */
    public boolean isMoving()
    {
        return isMoving;
    }

    /**
     *
     * @return Boolean representing if the "Elongate" flag is set.
     */
    public boolean isElongate()
    {
        return elongate;
    }

    /**
     *
     * @return Boolean representing if the "Alive" flag is set.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     *
     * @param xDir New X-Axis movement direction of snake.
     */
    public void setXDir(int xDir)
    {
        this.xDir = xDir;
    }

    /**
     *
     * @param yDir New Y-Axis movement direction of snake.
     */
    public void setYDir(int yDir)
    {
        this.yDir = yDir;
    }

    /**
     *
     * @param snakeColor Sets draw color for this snake.
     */
    public void setSnakeColor(Color snakeColor)
    {
        this.snakeColor = snakeColor;
    }

    /**
     *
     * @param moving Sets moving flag, if true, snake begins movement.
     */
    public void setMoving(boolean moving)
    {
        isMoving = moving;
    }

    /**
     *
     * @param elongate Sets the Elongate flag, this causes the snake to grow by one segment.
     */
    public void setElongate(boolean elongate)
    {
        this.elongate = elongate;
    }

    /**
     *
     * @param alive Sets the Alive flag, this flag controls if the snake is drawn and can be interacted with.
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

}
