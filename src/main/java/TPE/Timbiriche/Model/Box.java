package TPE.Timbiriche.Model;

public class Box {
    private boolean north, south, east, west;

    /*Una box es una estructura que consta de 4 fields booleanos,
    * cada uno representando a alguno de los lados que unen a los vertices
    * propios de dicha caja*/
    public Box(){
        this.east = false;
        this.north = false;
        this.west = false;
        this.south = false;

    }

    public Box(boolean w, boolean n, boolean e, boolean s){
        this.south = s;
        this.north = n;
        this.east = e;
        this.west = w;
    }

    public void addWestEdge() {
        this.west = true;
    }

    public void addSouthEdge() {
        this.south = true;
    }

    public void addNorthEdge() {
        this.north = true;
    }

    public void addEastEdge() {
        this.east = true;
    }

    public boolean hasEast() {
        return east;
    }

    public boolean hasNorth() {
        return north;
    }

    public boolean hasSouth() {
        return south;
    }

    public boolean hasWest() {
        return west;
    }

    public boolean allEdgesTaken(){
        return this.south && this.north && this.east && this.west;
    }

    public Box clone(){
        return new Box(this.west, this.north, this.east, this.south);
    }


    public boolean threeEdgesTaken(){
        return (north && south && east && !west) || (north && south && !east && west) || (north && !south && east && west) || (!north && south && east && west);
    }


}
