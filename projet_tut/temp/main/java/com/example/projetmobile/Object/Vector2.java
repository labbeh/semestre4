package com.example.projetmobile.Object;

public class Vector2
{
    float x,y;
    public static final Vector2 ZERO = new Vector2(0,0);

    public Vector2(float x, float y)
    {
        this.x =x;
        this.y =y;
    }

    public static float distance(Vector2 a, Vector2 b)
    {
       return a.distance(b);
    }

    public boolean isEqual(Vector2 other)
    {
        return other.x == this.x && other.y == this.y;
    }

    public float distance(Vector2 other)
    {
        return (float)Math.sqrt((this.x - other.x)*(this.x - other.x)+(this.y - other.y)*(this.y - other.y));
    }

    public static Vector2 add(Vector2 a,Vector2 b)
    {
        return new Vector2(a.x+b.x,a.y+b.y);
    }

    public static Vector2 minus(Vector2 a,Vector2 b)
    {
        return new Vector2(a.x-b.x,a.y-b.y);
    }

    public void add(Vector2 other)
    {
        Vector2 newVector2 = add(this, other);
        this.x = newVector2.x;
        this.y = newVector2.y;

    }

    public void minus(Vector2 other)
    {
        Vector2 newVector2 = minus(this, other);
        this.x = newVector2.x;
        this.y = newVector2.y;
    }

    @Override
    public String toString()
    {
        return "X : " + this.x + "      Y : "+this.y;
    }
}
