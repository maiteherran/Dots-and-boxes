package TPE.Timbiriche.Model;

import TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Board {
    private int sideSize;
    private Box[][] boxes;

    public Board(int sideSize){
        this.sideSize = sideSize;
        this.boxes = new Box[sideSize][sideSize];
        initializeBoxes();
    }

    public Board(Box[][] boxes){
        this.boxes = boxes;
        this.sideSize = boxes.length;
    }

    public void initializeBoxes(){
        for(int i =0 ; i< sideSize ; i++){
            for(int j=0; j<sideSize;j++){
                boxes[i][j] = new Box();
            }
        }
    }

    /*Agrega en la caja ij de la matriz "boxes" la línea en la dirección dir.
    * Actualiza, también, el cuadrado contiguo al ij que comparte la misma línea (si es que hay).
    * Devuelve 0 si no se completó ningún cuadrado, 1 si se completó uno y 2 si se
    * completaron 2 en la jugada.*/
    public int addEdge(int i, int j, Direction dir) throws edgeAlreadyTaken {
        int completedBoxes=0;
        if(i<0 || i>= sideSize || j<0 || j>= sideSize){
            System.out.print(i);
            System.out.print(j);
            throw new IllegalArgumentException();
        }
        switch(dir){
            case NORTH:
                if(boxes[i][j].hasNorth()){
                    throw new edgeAlreadyTaken();
                }
                boxes[i][j].addNorthEdge();
                if (i > 0) {
                    boxes[i-1][j].addSouthEdge();
                    if(boxes[i-1][j].allEdgesTaken()){
                        completedBoxes++;
                    }
                }
            break;

            case SOUTH:
                if(boxes[i][j].hasSouth()){
                    throw new edgeAlreadyTaken();
                }
                boxes[i][j].addSouthEdge();
                if (i < sideSize - 1) {
                    boxes[i+1][j].addNorthEdge();
                    if(boxes[i+1][j].allEdgesTaken()){
                        completedBoxes++;
                    }
                }
            break;

            case EAST:
                if(boxes[i][j].hasEast()){
                    throw new edgeAlreadyTaken();
                }
                boxes[i][j].addEastEdge();
                if (j < sideSize -1) {
                    boxes[i][j+1].addWestEdge();
                    if(boxes[i][j+1].allEdgesTaken()){
                        completedBoxes++;
                    }
                }
            break;

            case WEST:
                if(boxes[i][j].hasWest()){
                    throw new edgeAlreadyTaken();
                }
                boxes[i][j].addWestEdge();
                if (j >0) {
                    boxes[i][j-1].addEastEdge();
                    if(boxes[i][j-1].allEdgesTaken()){
                        completedBoxes++;
                    }
                }
            break;
        }
        if(boxes[i][j].allEdgesTaken()){
            completedBoxes++;
        }

    return completedBoxes;
    }

    public Board clone(){
      //  Box[][] b = boxes.clone();
        Box[][] b = new Box[sideSize][sideSize];
        for(int i=0; i<sideSize ; i++){
            for(int j=0; j< sideSize ; j++){
                b[i][j] = boxes[i][j].clone();
            }
        }
        return new Board(b);
    }

    public void printBoard(){
        for(int i =0 ; i< sideSize ; i++){
            for(int j=0; j<sideSize;j++){
                System.out.println(i+" "+j+"): ");
                System.out.println("NORTH: "+ boxes[i][j].hasNorth() );
                System.out.println("SOUTH: "+ boxes[i][j].hasSouth() );
                System.out.println("EAST: "+ boxes[i][j].hasEast() );
                System.out.println("WEST: "+ boxes[i][j].hasWest() );

            }
        }
    }


    public boolean isLineTaken(int i, int j, Direction dir){
        if(dir.equals(Direction.NORTH)){
            return boxes[i][j].hasNorth();
        }
        else if(dir.equals(Direction.SOUTH)){
            return boxes[i][j].hasSouth();
        }
        else if(dir.equals(Direction.WEST)){
            return boxes[i][j].hasWest();
        }
        else if(dir.equals(Direction.EAST)){
            return boxes[i][j].hasEast();
        }

        return false;
    }


    public List<Move> getPossibleMoves(){
        List<Move> l = new ArrayList<Move>();
        for(int i = 0 ; i< sideSize ; i++){
            for(int j=0; j<sideSize;j++){
                if(!boxes[i][j].hasSouth()) {
                    l.add(new Move(i, j, Direction.SOUTH));

                }
                if(!boxes[i][j].hasEast()) {
                    l.add(new Move(i, j, Direction.EAST));
                }
                if(i == 0 && !boxes[i][j].hasNorth()){
                    l.add(new Move(i,j,Direction.NORTH));
                }
                if(j==0 && !boxes[i][j].hasWest()){
                    l.add(new Move(i,j,Direction.WEST));
                }

            }
        }
    return l;
    }

    /*retorna la cantidad de cajas en este tablero con 3 líneas tomadas*/
    public int nThreeLineBoxes(){
        int a = 0;
        for(int i = 0 ; i< boxes.length ; i++){
            for(int j=0; j< boxes.length ; j++){
                if(boxes[i][j].threeEdgesTaken()){
                    a++;
                }
            }
        }
        return a;
    }


}
