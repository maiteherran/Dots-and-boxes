package TPE.Timbiriche.Model;

public class Move {
    private int i;
    private int j;
    private Direction dir;
    private int weight;


    public Move(int i, int j, Direction dir, int weight){
        this.i = i;
        this.j = j;
        this.dir = dir;
        this.weight = weight;
    }
    public Move(int i, int j, Direction dir){
        this.i = i;
        this.j = j;
        this.dir = dir;
    }

    public int getJ() {
        return j;
    }

    public int getI() {
        return i;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("(");
        s.append("box: "+ this.i + this.j);
        s.append(", direction: "+dir.toString());
        s.append(")");
        return s.toString();
    }
}
