package TPE.Timbiriche.Controller;

import TPE.Timbiriche.Model.*;
import TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken;
import TPE.Timbiriche.View.BoardView;
import TPE.Timbiriche.View.FrameView;

import java.util.concurrent.TimeUnit;

public class Controller {
    private Game game;
    private FrameView frameV;
    /*
    0: hvh
    1: mvh (machine mueve primero)
    2: hvm (machine mueve segundo)
    3: mvm
    * */
    private int gameType;
    /*private boolean mvm;
    private boolean mvh;
    private boolean hvh;*/


//    /*
//     * 0:time
//     * 1:depth
//     * */
//    private int minimaxCut;

    private boolean timeCut;
    private boolean depthCut;
    private long data;
    private boolean pruneOn;
    private MiniMaxTree lastMiniMax;

    public Controller(Game game, FrameView frameV, int gameType , boolean timeCut, long maxTime, boolean depthCut, long maxDepth, boolean pruneOn ){
        this.game = game;
        this.frameV = frameV;
        this.gameType = gameType;
        this.timeCut = timeCut;
        this.depthCut = depthCut;
        this.pruneOn = pruneOn;
        if(depthCut){
            this.data = maxDepth;
        }
        else{
            this.data = maxTime*1000; //en milisegundos
        }

    }


    public void addLine(int i, int j, Direction dir){
        try {
            game.addEdge(i,j,dir);
        } catch (TPE.Timbiriche.Model.Exceptions.edgeAlreadyTaken edgeAlreadyTaken) {
            edgeAlreadyTaken.printStackTrace();
        }

        updateBoardView();
        if(game.isGameOver()){
            frameV.endGame(game.getCurrentState().getScorePlayer1(), game.getCurrentState().getScorePlayer2());
            return;
        }
        if((gameType == 1 && this.game.getCurrentState().getTurn() == 1) || (gameType == 2 && this.game.getCurrentState().getTurn() == 2)){
            machineMove();
        }
    }

    public void updateBoardView(){
        if(game.getCurrentState().getTurn() == 1){
            frameV.p2TurnIndicatorOff();
            frameV.p1TurnIndicatorOn();
        }
        else{
            frameV.p2TurnIndicatorOn();
            frameV.p1TurnIndicatorOff();
        }
        frameV.updateScore(game.getCurrentState().getScorePlayer1(), game.getCurrentState().getScorePlayer2());
        frameV.getBoardV().draw(game.getCurrentState());
    }

    public boolean isLineTaken(int i, int j, Direction dir){
        return game.isLineTaken(i,j,dir);
    }

    public Board getBoard(){
        return this.game.getCurrentState().getBoard();
    }


    public int getTurn(){
        return this.game.getCurrentState().getTurn();
    }


    public void undo(){

        if(gameType == 0 || (gameType==2 && game.getCurrentState().keepOn()) || (gameType==1 && game.getCurrentState().keepOn())) {
            game.undo();
        }
        else if (gameType == 1){
            if(game.stateHistorySize()>1) {
                game.undo();
                game.undo();
            }
        }
        else if (gameType == 2){
            game.undo();
            game.undo();

        }
        updateBoardView();
    }



    public void machineMove(){

    lastMiniMax = new MiniMaxTree(game.getCurrentState(), this.timeCut, this.depthCut, this.data, pruneOn,game.getCurrentState().getTurn());

    State nextState  = lastMiniMax.getOptimalState();


    game.setNewCurrentState(nextState);

       updateBoardView();
        if(game.isGameOver()){
            frameV.endGame(game.getCurrentState().getScorePlayer1(), game.getCurrentState().getScorePlayer2());
            return;
        }

    }


    public Game getGame(){
        return this.game;
    }

    public void lastAIdot(){
        lastMiniMax.getTreeBuilder().lastAIdot("tree.dot");
    }


}