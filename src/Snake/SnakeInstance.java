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
        for (Point p: snakePoints)
        {
            g.fillRect(p.getX(), p.getY(), 4,4);
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

    public int getXDir()
    {
        return xDir;
    }

    public int getYDir()
    {
        return yDir;
    }

    public int getX()
    {
        return snakePoints.get(0).getX();
    }

    public int getY()
    {
        return snakePoints.get(0).getY();
    }

    public void setXDir(int xDir)
    {
        this.xDir = xDir;
    }

    public void setYDir(int yDir)
    {
        this.yDir = yDir;
    }

    public void setSnakeColor(Color snakeColor)
    {
        this.snakeColor = snakeColor;
    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public void setMoving(boolean moving)
    {
        isMoving = moving;
    }

    public boolean isElongate()
    {
        return elongate;
    }

    public void setElongate(boolean elongate)
    {
        this.elongate = elongate;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
