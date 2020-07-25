package Snake;

public class Point
{
    private int x;
    private int y;

    /**
     * Default Point constructor
     */
    public Point()
    {
        x = 0;
        y = 0;
    }

    /**
     *
     * @param x input x coord
     * @param y input y coord
     */
    public Point(int x, int y)
    {
       this.x = x;
       this.y = y;
    }

    /**
     *
     * @return Current X value
     */
    public int getX()
    {
        return x;
    }

    /**
     *
     * @return Current Y value
     */
    public int getY()
    {
        return y;
    }

    /**
     *
     * @param x new X value
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     *
     * @param y new Y value
     */
    public void setY(int y)
    {
        this.y = y;
    }
}
