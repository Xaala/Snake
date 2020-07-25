package Snake;

import java.awt.*;
import java.util.List;

import static Snake.SnakeGame.WINDOW_HEIGHT;
import static Snake.SnakeGame.WINDOW_WIDTH;


public class Token {
    private int x;
    private int y;
    private List<SnakeInstance> snakeList;
    private static int SIZE = 6;

    public Token(List<SnakeInstance> snakeList)
    {
        x = (int)(Math.random() * WINDOW_WIDTH - 5);
        y = (int)(Math.random() * WINDOW_HEIGHT - 5);

        this.snakeList = snakeList;
    }

    public void changePosition()
    {
        //Moves our token object somewhere where a snake isn't
        boolean isValid = false;
        while (isValid == false)
        {
            int tempX = (int)(Math.random() * WINDOW_WIDTH - 5);
            int tempY = (int)(Math.random() * WINDOW_HEIGHT - 5);
            //check if valid (not inside another snake

            boolean validSpot = true;
            //check if this is already inside a snake, if so, it's not a valid place for a Token.
            for (int i = 0; i < snakeList.size(); i++)
            {
                for (int j = 0; j < snakeList.get(i).snakePoints.size(); j++)
                {
                    if (tempX == snakeList.get(i).snakePoints.get(j).getX() || tempY == snakeList.get(i).snakePoints.get(j).getY())
                        validSpot = false;
                }
            }

            //If it's valid, use this x,y coordinate.
            if (validSpot == true)
            {
                x = tempX;
                y = tempY;

                isValid = true;
            }
        }
    }

    //check if some snake got the Token
    public boolean snakeCollision(SnakeInstance snk)
    {
        int snakeX = snk.getX() + 2; //center of rect
        int snakeY = snk.getY() + 2;

        if (snakeX >= x-1 && snakeX <= (x + 7))
        {
            if (snakeY >= y-1 && snakeY <= (y +7))
            {
                changePosition();
                snk.setElongate(true);
                return true;
            }
        }

        return false;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.YELLOW);

        g.fillRect(x, y, SIZE, SIZE);


    }
}
