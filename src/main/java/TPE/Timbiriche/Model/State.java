package TPE.Timbiriche.Model;

import TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken;
//import oracle.jvm.hotspot.jfr.ThreadStates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class State {
    private Board board;
    private int scorePlayer1;
    private int scorePlayer2;
    private boolean player1turn;
    private boolean player2turn;
    private int sideSize;
    /*keep on indica que la jugada no ha terminado, que el jugador
    * en cuestión puede realizar otro movimiento más porque llenó al
    * menos una caja*/
    private boolean keepOn;




    public State(int sideSize){
        board = new Board(sideSize);
        this.sideSize = sideSize;
        this.player1turn = true;
        this.player2turn = false;
        this.keepOn = false;
    }

    public State(Board board, int scorePlayer1, int scorePlayer2, boolean player1turn, int sideSize){
        this.board = board;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.player1turn = player1turn;
        this.player2turn = !player1turn;
        this.keepOn = false;
        this.sideSize = sideSize;
    }

    public Board getBoard() {
        return board;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }


    public State clone(){

        return new State(this.board.clone(),scorePlayer1, scorePlayer2, player1turn, sideSize);
    }

    public void switchPlayersTurn(){
        this.player1turn = !this.player1turn;
        this.player2turn = !this.player2turn;
    }

    public void addScore(int n){
        if(player1turn){
            this.scorePlayer1+=n;
        }
        else{
            this.scorePlayer2+=n;
        }
    }

    public int getTurn(){
        if(player1turn){
            return 1;

        }
        return 2;
    }

    /*getWeight devuelve el puntaje del tablero para que será utilizado por el minimax.
    * Retorna la resta entre el puntaje del jugador que acaba de realizar una jugada
    * menos el puntaje del jugador opuesto*/
    public int getWeight(){
        int ret = 0;

        if(!this.player1turn){
           ret = this.scorePlayer1 - this.scorePlayer2;
        }
        else {
           ret = this.scorePlayer2 - this.scorePlayer1;
        }
    return ret;
    }


    /*devuelve true si el juego ha terminado*/
    public boolean isOver(){
        return this.scorePlayer2 + this.scorePlayer1 == this.sideSize * this.sideSize;
    }

    public void setKeepOn(boolean s){
        this.keepOn = s;
    }

    public boolean keepOn(){
        return this.keepOn;
    }


    /*getStateNeighbours devuelve un set con todos las posibles jugadas a partir
    * de el state actual*/
    public Set<Node> getStateNeighbours(){
        Set<Node> l = new HashSet<Node>();
        /*moveAcum acumula los movimientos posibles para que, luego de llamar a la funcion
        * recursiva, contenga a una jugada posible y la agregue al set de neighbours*/
        List<Move> moveAcum = new ArrayList<Move>();
        getStateNeighboursR(l,moveAcum,this ,false);
        return l;
    }



    private void getStateNeighboursR(Set<Node> l, List<Move> moveAcum, State aux, boolean remainingMoves){
        State backup = aux.clone();
        for(Move move : aux.getBoard().getPossibleMoves()){
            int boxesFilled = -1;
            moveAcum.add(move);
            if(!remainingMoves) {
               State childState = new State(aux.getBoard().clone(), this.scorePlayer1, this.scorePlayer2, this.player1turn, sideSize);
                try {
                    boxesFilled = childState.getBoard().addEdge(move.getI(), move.getJ(), move.getDir());
                } catch (TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken edgeAlreadyTaken) {
                    edgeAlreadyTaken.printStackTrace();
                }

                if(childState.player1turn){
                    childState.setScorePlayer1(aux.getScorePlayer1() + boxesFilled);
                }
                else {
                    childState.setScorePlayer2(aux.getScorePlayer2() + boxesFilled);
                }
                /*si el movimiento cerró una caja y no es el final del juego, la jugada continúa, por
                * eso es que se busca un siguiente movimiento*/
                if(boxesFilled>0 && childState.scorePlayer1 + childState.scorePlayer2 < sideSize*sideSize){

                    getStateNeighboursR(l, moveAcum, childState,true);
                }
                else{
                    childState.player2turn = ! childState.player2turn;
                    childState.player1turn = ! childState.player1turn;
                    List<Move> auxList = new ArrayList<Move>();
                    auxList.addAll(moveAcum);
                    l.add(new Node(childState,auxList ));
                    moveAcum.remove(moveAcum.size()-1);

                }

            }
            else {


                try {
                    boxesFilled = aux.getBoard().addEdge(move.getI(), move.getJ(), move.getDir());

                } catch (TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken edgeAlreadyTaken) {

                    edgeAlreadyTaken.printStackTrace();
                }

                if(aux.player1turn){
                    aux.setScorePlayer1(aux.getScorePlayer1() + boxesFilled);
                }
                else {
                    aux.setScorePlayer2(aux.getScorePlayer2() + boxesFilled);

                }

                /*si el movimiento cerró una caja y no es el final del juego, la jugada continúa, por
                 * eso es que se busca un siguiente movimiento*/
                if(boxesFilled>0 && aux.scorePlayer1 + aux.scorePlayer2 < sideSize*sideSize){

                    getStateNeighboursR(l, moveAcum, aux,true);
                }
                else{
                    aux.player2turn = ! aux.player2turn;
                    aux.player1turn = ! aux.player1turn;
                    List<Move> auxList = new ArrayList<Move>();
                    auxList.addAll(moveAcum);
                    l.add(new Node(aux, auxList));
                    moveAcum.remove(moveAcum.size()-1);
                }
                aux = backup.clone();
            }

        }
        moveAcum.clear();
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

}
