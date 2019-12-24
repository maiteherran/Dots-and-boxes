package TPE.Timbiriche.Model;

import TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;


public class Game {
    private State currentState;
    private Stack<State> stateHistory;
    private int sideSize;
    private boolean player1turn;
    private boolean player2turn;


    public Game(int n){
        this.sideSize = n;
        this.player1turn = true;
        this.player2turn = false;
        this.currentState = new State(sideSize);
        this.stateHistory = new Stack<State>();

    }


    /*Clona el tablero del estado actual y agrega la linea en el cuadrado correspondiente.
    * Crea un nuevo estado que pasa a ser el actual con el nuevo tablero y los puntajes
    * actualizados.
    * Agrega al stateHistory el estado actual*/
    public int addEdge(int i, int j, Direction dir) throws edgeAlreadyTaken {
        if(!currentState.keepOn()) {
            this.stateHistory.push(currentState);
            State nextState = currentState.clone();

            // System.out.println("TURN: "+ nextState.getTurn());
            int completedSquares = nextState.getBoard().addEdge(i, j, dir);

        /*si el jugador activo completa al menos 1 cuadrado, le toca el próximo turno
        entonces no se modifica el currentPlayer del state clonado.
        */
            if (completedSquares == 0) {
                nextState.setKeepOn(false);
                nextState.switchPlayersTurn();
                player1turn = !player1turn;
                player2turn = !player2turn;
            } else {
                nextState.setKeepOn(true);
                nextState.addScore(completedSquares);

            }
            //System.out.println("nuevo state");
            this.currentState = nextState;
            return completedSquares;
        }

        int completedSquares = currentState.getBoard().addEdge(i,j,dir);
        if (completedSquares == 0) {
            currentState.setKeepOn(false);
            currentState.switchPlayersTurn();
            player1turn = !player1turn;
            player2turn = !player2turn;
        } else {
            currentState.setKeepOn(true);
            currentState.addScore(completedSquares);
        }

        return completedSquares;
    }


    public boolean isGameOver(){
        return currentState.getScorePlayer2() + currentState.getScorePlayer1() == sideSize*sideSize;
    }



    public boolean isLineTaken(int i, int j, Direction dir){
    return currentState.getBoard().isLineTaken(i,j,dir);
    }


    public State getCurrentState() {
        return currentState;
    }


    /*la función undo permite volver a la jugada anterior. No lanza una excepción
    * cuando el jugador aprieta más veces que la cantidad de states que hay en el stack*/
    public void undo(){
        if(stateHistory.size() > 0){
            currentState = stateHistory.pop();
        }

    }

    public int stateHistorySize(){
        return this.stateHistory.size();
    }

    public void setNewCurrentState(State state){
        stateHistory.add(currentState);
        currentState = state;
    }




}