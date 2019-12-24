package TPE.Timbiriche.View;

import TPE.Timbiriche.Controller.Controller;

public class BoxView {
    Line east, north, west, south;
    boolean eastTaken, northTaken, westTaken, southTaken;


    public BoxView(Line east, Line north, Line west, Line south){
        this.east=east;
        this.north=north;
        this.west=west;
        this.south=south;
        this.eastTaken = false;
        this.southTaken = false;
        this.westTaken =false;
        this.southTaken = false;

    }

    public void setEast(Line east) {
        this.east = east;
    }

    public void setNorth(Line north) {
        this.north = north;
    }

    public void setSouth(Line south) {
        this.south = south;
    }

    public void setWest(Line west) {
        this.west = west;
    }


    public boolean isEastTaken() {
        return eastTaken;
    }

    public boolean isNorthTaken() {
        return northTaken;
    }

    public boolean isSouthTaken() {
        return southTaken;
    }

    public boolean isWestTaken() {
        return westTaken;
    }

    public Line getEast() {
        return east;
    }

    public Line getNorth() {
        return north;
    }

    public Line getSouth() {
        return south;
    }

    public Line getWest() {
        return west;
    }


}
