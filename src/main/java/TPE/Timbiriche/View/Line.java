package TPE.Timbiriche.View;

import java.awt.*;

public class Line {
    private int x1, x2, y1, y2;
    private int taken;
    private Color color;
    private boolean humanCatch;


    public Line(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.taken=0;
        this.color = Color.black;
        this.humanCatch =false;
    }

    public void setHumanCatch() {
        this.humanCatch = true;
    }

    public void nonHumanCatch() {
        this.humanCatch = false;
    }

    public boolean humanCatch() {
        return humanCatch;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public int getTaken() {
        return taken;
    }

    public String toString(){
        String s = "["+x1+", "+y1+", "+x2+", "+y2+"]";
        return s;
    }
}
