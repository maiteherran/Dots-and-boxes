package TPE.Timbiriche.Model;

import java.util.ArrayList;
import java.util.List;

/*Representa un nodo en el algoritmo minimax*/
public class Node{
    private State state;
    private int weight;
    private boolean max;
    private int id;
    private boolean pruned;
    private List<Move> lastPlayMoves;

    public boolean isPruned() {
        return pruned;
    }

    public void setPruned(boolean pruned) {
        this.pruned = pruned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Move> getLastPlayMoves(){
        return lastPlayMoves;
    }


     Node(State state, List<Move> playMoves){
        this.state = state;
        this.lastPlayMoves = playMoves;
    }

    Node(State state){
        this.state = state;
    }

     Node(int weight){
        this.weight = weight;
    }

    public State getState() {
        return state;
    }

    public int getWeight() {
        return weight;
    }

    public boolean max(){
        return max;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMax(boolean max) {
        this.max = max;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        if(lastPlayMoves != null) {
            for (Move move : lastPlayMoves) {
                s.append(move.toString()+"\n");

            }
            if(!pruned) {
                s.append(" " + this.weight);
            }
        }

        return s.toString();
    }



}