package TPE.Timbiriche.View;

import TPE.Timbiriche.Model.Node;

public class aux {
  /*  package TPE.Timbiriche.View;
import TPE.Timbiriche.Controller.Controller;
import TPE.Timbiriche.Model.Board;
import TPE.Timbiriche.Model.Direction;
import TPE.Timbiriche.Model.State;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
    public class BoardView extends JPanel implements MouseListener {
        private FrameView frame;
        private int gameType;
        private int n;
        private int playerTurn;
        private boolean firstPlayerTurn;
        private HashMap<Integer, Line> lines;
        private Controller controller;
        private BoxView[][] boxes;
        private Board board;
        public BoardView(FrameView frame, int gameType, int boardSize){
            this.frame = frame;
            this.n= boardSize;
            this.playerTurn = 1;
            this.firstPlayerTurn = true;
            this.lines = new HashMap<Integer,Line>();
            super.repaint();
            init();
        }
        public void setController(Controller controller){
            this.controller = controller;
            setBoard();
        }
        public void setBoard(){
            this.board = controller.getBoard();
        }
        public void init() {
            this.setBackground(Color.lightGray);
            Dimension dim = new Dimension(605, 800);
            this.setSize(605, 800);
            this.setPreferredSize(dim);
            this.setMaximumSize(dim);
            this.setMinimumSize(dim);
            this.addMouseListener(this);
            //createLines();
            createBoxes();
            super.repaint();
        }
        public void draw(State state){
            this.board = state.getBoard();
            System.out.println("entro a draw");
            state.getBoard().printBoard();
            this.repaint();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.lightGray);
            setSize(605, 800);
            g.setColor(Color.BLACK);
            //this.addMouseListener(this);
            drawPoints(g);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board.isLineTaken(i, j, Direction.WEST)) {
                        if (boxes[i][j].getWest().humanCatch()) {
                            g.setColor(boxes[i][j].getWest().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }
                        g.drawLine(boxes[i][j].getWest().getX1(), boxes[i][j].getWest().getY1(), boxes[i][j].getWest().getX2(), boxes[i][j].getWest().getY2());
                    }
                    if (board.isLineTaken(i, j, Direction.EAST)) {
                        System.out.println("entro a paintcomponent east");
                        if (boxes[i][j].getEast().humanCatch()) {
                            g.setColor(boxes[i][j].getEast().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }
                        g.drawLine(boxes[i][j].getEast().getX1(), boxes[i][j].getEast().getY1(), boxes[i][j].getEast().getX2(), boxes[i][j].getEast().getY2());
                    }
                    if (board.isLineTaken(i, j, Direction.NORTH)) {
                        if (boxes[i][j].getNorth().humanCatch()) {
                            g.setColor(boxes[i][j].getNorth().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }
                        g.drawLine(boxes[i][j].getNorth().getX1(), boxes[i][j].getNorth().getY1(), boxes[i][j].getNorth().getX2(), boxes[i][j].getNorth().getY2());
                    }
                    if (board.isLineTaken(i, j, Direction.SOUTH)) {
                        if (board.isLineTaken(i, j, Direction.NORTH)) {
                            if (boxes[i][j].getSouth().humanCatch()) {
                                g.setColor(boxes[i][j].getSouth().getColor());
                            } else {
                                g.setColor(Color.blue);
                            }
                            g.drawLine(boxes[i][j].getSouth().getX1(), boxes[i][j].getSouth().getY1(), boxes[i][j].getSouth().getX2(), boxes[i][j].getSouth().getY2());
                        }
                    }
                }
            }
        }
        public void drawPoints(Graphics g){
            int x = 20;
            int y = 20;
            int space = (605-40)/(n+1);
            for(int i=0; i<n+1; i++) {
                for(int j=0; j<n+1 ; j++) {
                    g.fillOval(x, y, 10, 10);
                    y+=space;
                }
                x+=space;
                y=20;
            }
        }
        public void createBoxes(){
            int space = (605-40)/(n+1);
            this.boxes = new BoxView[n][n];
            for(int i=0 ; i< n; i++){
                for(int j=0; j<n ; j++){
                    Line east = new Line(20 + space*i + 5, 20 + space*j + 5, 20+space*i +space + 5, 20+space*j + 5);
                    Line west = new Line(20+ space*i + 5, 20 + space*j + space + 5,20+space*i +space + 5, 20+space*j + space + 5);
                    Line north = new Line(20+space*i + 5, 20 +space*j + 5, 20 +space*i + 5 , 20+space*j + space + 5);
                    Line south = new Line(20+space*i + space + 5, 20 + space*j + 5, 20 + space*i + space + 5, 20+space*j + space + 5);
                    boxes[i][j] = new BoxView(east, north, west, south);
                }
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            boolean found = false;
            int x1, x2;
            int y1, y2;
            int space = (605 - 40) / (n + 1);
            for(int i =0; i< n && !found; i++){
                for(int j=0 ; j< n && !found; j++){
                    if(!controller.isLineTaken(i,j,Direction.WEST) && !found){
                        x1 = boxes[i][j].getWest().getX1();
                        y1 = boxes[i][j].getWest().getY1();
                        x2 = boxes[i][j].getWest().getX2();
                        y2=  boxes[i][j].getWest().getY2();
                        if(e.getX() >= x1 && e.getX() <= x2 && e.getX() <= 20+space*(n) && e.getY() >= y1 - 10  && e.getY() <= y2 +10 ){
                            System.out.println("entro en west");
                            if(controller.getTurn() == 1){
                                boxes[i][j].getWest().setColor(Color.yellow);
                                boxes[i][j].getWest().setHumanCatch();
                                if(j<n-1){
                                    boxes[i][j+1].getEast().setColor(Color.yellow);
                                    boxes[i][j+1].getEast().setHumanCatch();
                                }
                            }
                            else{
                                boxes[i][j].getWest().setColor(Color.red);
                                boxes[i][j].getWest().setHumanCatch();
                                if(j<n-1){
                                    boxes[i][j+1].getEast().setColor(Color.red);
                                    boxes[i][j+1].getEast().setHumanCatch();
                                }
                            }
                            controller.addLine(i,j, Direction.WEST);
                            found = true;
                        }
                    }
                    if(!controller.isLineTaken(i,j,Direction.EAST) && !found){
                        x1 = boxes[i][j].getEast().getX1();
                        y1 = boxes[i][j].getEast().getY1();
                        x2 = boxes[i][j].getEast().getX2();
                        y2=  boxes[i][j].getEast().getY2();
                        if(e.getX() >= x1 && e.getX() <= x2 && e.getX() <= 20+space*(n) && e.getY() >= y1 - 10  && e.getY() <= y2 +10 ){
                            System.out.println("entro en east");
                            if(controller.getTurn() == 1){
                                System.out.println("entro turno 1");
                                boxes[i][j].getEast().setColor(Color.yellow);
                                boxes[i][j].getEast().setHumanCatch();
                                if(j>0){
                                    boxes[i][j-1].getWest().setColor(Color.yellow);
                                    boxes[i][j-1].getWest().setHumanCatch();
                                }
                            }
                            else{
                                System.out.println("entro turno 2");
                                boxes[i][j].getEast().setColor(Color.red);
                                boxes[i][j].getEast().setHumanCatch();
                                if(j>0){
                                    boxes[i][j-1].getWest().setColor(Color.red);
                                    boxes[i][j-1].getWest().setHumanCatch();
                                }
                            }
                            System.out.println("antes de llamar al controller");
                            controller.addLine(i,j, Direction.EAST);
                            found = true;
                        }
                    }
                    if(!controller.isLineTaken(i,j,Direction.NORTH) && !found){
                        x1 = boxes[i][j].getNorth().getX1();
                        y1 = boxes[i][j].getNorth().getY1();
                        x2 = boxes[i][j].getNorth().getX2();
                        y2=  boxes[i][j].getNorth().getY2();
                        if(e.getY() >=y1 && e.getY() <= y2 && e.getY() <=20+space*(n) && e.getX() >= x1-10 && e.getX() <= x2+10 ){
                            System.out.println("entro en north");
                            if(controller.getTurn() == 1){
                                boxes[i][j].getNorth().setColor(Color.yellow);
                                boxes[i][j].getNorth().setHumanCatch();
                                if(i>0){
                                    boxes[i-1][j].getSouth().setColor(Color.yellow);
                                    boxes[i-1][j].getSouth().setHumanCatch();
                                }
                            }
                            else{
                                boxes[i][j].getNorth().setColor(Color.red);
                                boxes[i][j].getNorth().setHumanCatch();
                                if(i>0){
                                    boxes[i-1][j].getSouth().setColor(Color.red);
                                    boxes[i-1][j].getSouth().setHumanCatch();
                                }
                            }
                            controller.addLine(i,j, Direction.NORTH);
                            found = true;
                        }
                    }
                    if(!controller.isLineTaken(i,j,Direction.SOUTH) && !found){
                        x1 = boxes[i][j].getSouth().getX1();
                        y1 = boxes[i][j].getSouth().getY1();
                        x2 = boxes[i][j].getSouth().getX2();
                        y2=  boxes[i][j].getSouth().getY2();
                        if(e.getY() >=y1 && e.getY() <= y2 && e.getY() <=20+space*(n) && e.getX() >= x1-10 && e.getX() <= x2+10 ){
                            System.out.println("entro en south");
                            if(controller.getTurn() == 1){
                                System.out.println("entrooooooooo");
                                boxes[i][j].getSouth().setColor(Color.yellow);
                                boxes[i][j].getSouth().setHumanCatch();
                                if(i<n-1){
                                    boxes[i+1][j].getNorth().setColor(Color.yellow);
                                    boxes[i+1][j].getNorth().setHumanCatch();
                                }
                            }
                            else{
                                boxes[i][j].getSouth().setColor(Color.red);
                                boxes[i][j].getSouth().setHumanCatch();
                                if(i<n-1){
                                    boxes[i+1][j].getNorth().setColor(Color.red);
                                    boxes[i+1][j].getNorth().setHumanCatch();
                                }
                            }
                            controller.addLine(i,j, Direction.SOUTH);
                            found = true;
                        }
                    }
                }
//            if(found){
//                //this.repaint();
//            this.revalidate();
//        }
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
*/

//    private Node depthLimitSearch(Node currentNode, long depth, int alpha, int beta, int currentPlayer){
//        // System.out.println("f: "+currentNode+" ,"+depth+" ,"+currentPlayer);
//
//        if(depth == 0 || currentNode.getState().isOver()) {
//            currentNode.setWeight(getWeight(currentNode, currentPlayer));
//            return currentNode;
//        }
//        Integer bestValue = Integer.MIN_VALUE;
//        Node bestNode = null;
//        currentPlayer = currentPlayer == 1 ? 2:1;
//        //System.out.println("    for: ");
//        for(Node childNode : currentNode.getState().getStateNeighbours()){
//            childNode.setMax(false); //esto q onda??
//
//            Node node = depthLimitSearch(childNode, depth -1, alpha, beta, currentPlayer);
//            System.out.println("childnode"+ childNode.toString());
//            System.out.println("node"+node.toString());
//            //System.out.println("        *"+node.toString());
//
//            if(node.getWeight() > bestValue){
//                if(bestNode!=null) {
//                    // System.out.println("best Node update from: " + bestNode.getWeight() + "to " + node.getWeight());
//                }
//                bestValue = node.getWeight();
//                bestNode = childNode;
//                bestNode.setWeight(bestValue);
//                //System.out.println("bestNode move: "+ bestNode.toString());
//
//            }
//
//            dot.addEdge(currentNode, node);
//            dot.setLabel(node, rootNode);
//
//        }
//
//        dot.changeColor(bestNode);
//        dot.setLabel(currentNode, rootNode);
//
//        bestNode.setWeight(-1*bestNode.getWeight());
//        return bestNode;
//    }



//    private Node depthLimitSearch(Node currentNode, long depth, int alpha, int beta, int currentPlayer){
//        // System.out.println("f: "+currentNode+" ,"+depth+" ,"+currentPlayer);
//
//        if(depth == 0 || currentNode.getState().isOver()) {
//            currentNode.setWeight(getWeight(currentNode, currentPlayer));
//            System.out.println("currentNode corta: "+currentNode.toString());
//            return currentNode;
//        }
//        Integer bestValue = Integer.MIN_VALUE;
//        Node bestNode = null;
//        currentPlayer = currentPlayer == 1 ? 2:1;
//        for(Node childNode : currentNode.getState().getStateNeighbours()){
//            childNode.setMax(false); //esto q onda??
//            childNode.setWeight(depthLimitSearch(childNode, depth -1, alpha, beta, currentPlayer).getWeight());
//            if(childNode.getWeight() > bestValue){
//                bestNode = childNode;
//                bestValue = childNode.getWeight();
//            }
//            if(currentPlayer == maxPlayer) {
//                alpha = Math.max(bestValue, childNode.getWeight());
//                if(alpha > beta){
//                    childNode.setPruned(true);
//
//                }
//            }
//            else{
//                beta = Math.max(bestValue, childNode.getWeight());
//                if(beta > alpha){
//                    currentNode.setPruned(true);
//
//                }
//            }
//
//            alpha = -alpha;
//            beta = -beta;
//
//            dot.addEdge(currentNode, childNode);
//            dot.setLabel(childNode, rootNode);
//
//        }
//
//        dot.changeColor(bestNode);
//        dot.setLabel(currentNode, rootNode);
//
//        bestNode.setWeight(-1*bestNode.getWeight());
//        return bestNode;
//    }


//
//    package TPE.Timbiriche.Model;
//
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//    public class TreeBuilder {
//        private String tree;
//        private int id;
//        private int firstPlayer;
//
//        public TreeBuilder() {
//            tree = "digraph {\n";
//            id = 0;
//        }
//
//        public void restart(int player) {
//            tree = "digraph {\n";
//            id = 0;
//            firstPlayer = player;
//        }
//
//        public void addEdge(Node father, Node child) {
//            addNode(father);
//            addNode(child);
//            tree += father.getId() + " -> " + child.getId() + "\n";
//        }
//
//        private void addNode(Node node) {
//            if (node.getId() == 0)
//                node.setId(++id);
//        }
//
//        public void changeColor(Node node) {
//            addNode(node);
//            tree += node.getId() + " [color= red , style = filled ]\n";
//        }
//
//        public void setLabel(Node node, Node first) {
//            addNode(node);
//            if (node.equals(first))
//                tree += node.getId() + " [label = \"START " + firstPlayer +"\" shape = " + getShape(node) + ", color = red, style = filled ]\n";
//            else
//                tree += getOutput(node);
//        }
//
//        private String getOutput(Node node) {
//            String out = node.getId() + " [label = \"" + getLabel(node) + "\" , shape = " + getShape(node);
//            if (node.isPruned()) {
//                System.out.println("entro a node pruned en tree builder");
//                out += ", color = grey, style = filled";
//            }
//            out += "]\n";
//            return out;
//        }
//
//        private String getLabel(Node node) {
//            //System.out.println("tree build"+ node.toString());
//            String label = node.toString();
////        if (!node.isPruned())
////            label += " " + node.getWeight();
//            return label;
//        }
//
//        private String getShape(Node node) {
//            //return node.getPlayer() != startingPlayer ? "rectangle" : "ellipse";
//            return "ellipse";
//        }
//
//        public void generateDotFile() {
//            FileWriter file;
//            tree = tree.concat("}");
//            try {
//                file = new FileWriter("tree.dot");
//                file.write(tree);
//                file.close();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }


//
//    private Node depthLimitSearch(Node currentNode, long depth, int alpha, int beta, int currentPlayer){
//
//
//        if(depth == 0 || currentNode.getState().isOver()) {
//            //currentNode.setWeight(getWeight(currentNode, currentPlayer));
//            if(currentPlayer != maxPlayer){
//                currentNode.setWeight(currentNode.getWeight());
//            }
//            return currentNode;
//        }
//        Integer bestValue = Integer.MIN_VALUE;
//        Node bestNode = null;
//        currentPlayer = currentPlayer == 1 ? 2:1;
//
//        for(Node childNode : currentNode.getState().getStateNeighbours()){
//            childNode.setMax(false); //esto q onda??
//            childNode.setWeight(depthLimitSearch(childNode, depth -1, alpha, beta, currentPlayer).getWeight());
//            if(childNode.getWeight() > bestValue){
//                bestNode = childNode;
//                bestValue = childNode.getWeight();
//            }
//
//            dot.drawDirectedEdge(currentNode, childNode);
//            dot.setLabel(childNode, rootNode);
//
//        }
//
//        dot.changeColor(bestNode);
//        dot.setLabel(currentNode, rootNode);
//
//        bestNode.setWeight(-1*bestNode.getWeight());
//        return bestNode;
//    }

}


