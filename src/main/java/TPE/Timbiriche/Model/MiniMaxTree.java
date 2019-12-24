package TPE.Timbiriche.Model;

import TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken;

import java.io.*;
import java.util.List;

public class MiniMaxTree {
    private boolean timeOn;
    private boolean depthOn;
    private boolean pruneOn;
    private long data;
    private Node rootNode;
    private State rootState;
    private long startingTime;
    private long check;
    private boolean dotTree;
    private TreeBuilder treeBuilder;
    private PrintWriter writer;
    private File file;
    private TreeBuilder dot;
    private int maxPlayer;


    //a partir de currentState se va a empezar a armar el árbol
    public MiniMaxTree(State currentState, boolean timeOn, boolean depthOn,long data, boolean pruneOn, int maxPlayer){
        this.timeOn = timeOn;
        this.data = data;
        this.depthOn = depthOn;
        this.pruneOn = pruneOn;
        this.rootState = currentState;
        this.rootNode = new Node(currentState);
        this.dotTree = true;
        this.dot = new TreeBuilder(rootNode);
        this.maxPlayer = maxPlayer;
    }



    public State getOptimalState(){
        State state = null;
        Node n= null;
        int currentPlayer = maxPlayer == 1 ? 2: 1;
        if(depthOn){
            n = depthLimitSearch(rootNode, this.data,true, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer);
        }
        else {
            n =  timeSearch(this.rootNode, currentPlayer);
        }

        return n.getState();
    }





    private Node depthLimitSearch(Node currentNode, long depth, boolean maxPlayer, int alpha, int beta, int currentPlayer){
        if(timeOn && System.currentTimeMillis() - startingTime > this.data){
            return null;
        }

        if(depth == 0 || currentNode.getState().isOver()){
            currentNode.setWeight(getWeight(currentNode));
            return currentNode;
        }
        boolean pruned = false;

        int next = currentPlayer == 1 ? 2:1;
        if(maxPlayer){
            Node maxNode = new Node(Integer.MIN_VALUE);
            for(Node childNode : currentNode.getState().getStateNeighbours()){
                if(timeOn && System.currentTimeMillis() - startingTime > this.data){
                    return null;
                }
                if(pruned){
                    childNode.setPruned(true);
                }

                else {
                    Node aux = depthLimitSearch(childNode, depth - 1, false, alpha, beta, next);
                    if(aux == null){
                        return null;
                    }
                    childNode.setWeight(aux.getWeight());
                    if (childNode.getWeight() > maxNode.getWeight()) {
                        maxNode = childNode;
                    }

                    alpha = Math.max(alpha, childNode.getWeight());

                    if (pruneOn && alpha >= beta) {
                        pruned = true;
                    }
                }
                dot.addChild(currentNode, childNode);
                dot.setNodeText(childNode);

            }
            dot.changeNodeColour(maxNode, "red");
            dot.setNodeText(currentNode);
            return maxNode;
        }
        else{
            Node minNode = new Node(Integer.MAX_VALUE);
            for(Node childNode : currentNode.getState().getStateNeighbours()){
                if(timeOn && System.currentTimeMillis() - startingTime > this.data){
                    return null;
                }
                if(pruned){
                    childNode.setPruned(true);
                }
                else {
                    Node aux = depthLimitSearch(childNode, depth - 1, true, alpha, beta, next);
                    if(aux == null){
                        return null;
                    }
                    childNode.setWeight(aux.getWeight());

                    if (childNode.getWeight() < minNode.getWeight()) {
                        minNode = childNode;
                    }
                    beta = Math.min(beta, childNode.getWeight());


                    if (pruneOn && alpha >= beta) {
                        pruned = true;
                    }
                }

                dot.addChild(currentNode, childNode);
                dot.setNodeText(childNode);

            }

            dot.changeNodeColour(minNode, "red");
            dot.setNodeText(currentNode);
            return minNode;
        }

    }


    public Node timeSearch(Node currentNode, int currentPlayer){

        this.startingTime = System.currentTimeMillis();
        boolean running = true;
        int depthAux = 1;
        Node ret = new Node(currentNode.getState());
        Node aux = ret;
        TreeBuilder dotAux = dot;
        while(running){
            aux = depthLimitSearch(rootNode, depthAux,true, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer);
            if(aux == null){
                dot = dotAux;
                running = false;
            }
            else {
                dotAux = dot.clone();
                dot.newDot();
                depthAux++;
                ret = aux;
            }
        }
    return ret;
    }




    public TreeBuilder getTreeBuilder() {
        return dot;
    }


    public int getWeight(Node node){
        State s = node.getState();
        if(maxPlayer == 1){
            if(depthOn && data == 1){
                return s.getScorePlayer1() - s.getScorePlayer2() - s.getBoard().nThreeLineBoxes();
            }
            return s.getScorePlayer1() - s.getScorePlayer2();
        }
        if(depthOn && data == 1){
            return s.getScorePlayer2() - s.getScorePlayer1() - s.getBoard().nThreeLineBoxes();
        }
        return s.getScorePlayer2() - s.getScorePlayer1();
    }
}

