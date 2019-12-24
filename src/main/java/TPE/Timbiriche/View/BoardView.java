package TPE.Timbiriche.View;

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
        this.gameType = gameType;
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
                        //System.out.println("entro a paintcomponent west");
                        if (boxes[i][j].getWest().humanCatch()) {
                            g.setColor(boxes[i][j].getWest().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }
                        g.drawLine(boxes[i][j].getWest().getX1(), boxes[i][j].getWest().getY1(), boxes[i][j].getWest().getX2(), boxes[i][j].getWest().getY2());
                    } else {
                        boxes[i][j].getWest().nonHumanCatch();
                    }

                    if (board.isLineTaken(i, j, Direction.EAST)) {
                        // System.out.println("entro a paintcomponent east");
                        if (boxes[i][j].getEast().humanCatch()) {
                            g.setColor(boxes[i][j].getEast().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }

                        g.drawLine(boxes[i][j].getEast().getX1(), boxes[i][j].getEast().getY1(), boxes[i][j].getEast().getX2(), boxes[i][j].getEast().getY2());
                    } else {
                        boxes[i][j].getEast().nonHumanCatch();
                    }

                    if (board.isLineTaken(i, j, Direction.NORTH)) {
                        // System.out.println("entro a paintcomponent north");
                        if (boxes[i][j].getNorth().humanCatch()) {
                            g.setColor(boxes[i][j].getNorth().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }

                        g.drawLine(boxes[i][j].getNorth().getX1(), boxes[i][j].getNorth().getY1(), boxes[i][j].getNorth().getX2(), boxes[i][j].getNorth().getY2());
                    } else {
                        boxes[i][j].getNorth().nonHumanCatch();
                    }


                    if (board.isLineTaken(i, j, Direction.SOUTH)) {
                        // System.out.println("entro a paintcomponent south");
                        if (boxes[i][j].getSouth().humanCatch()) {
                            g.setColor(boxes[i][j].getSouth().getColor());
                        } else {
                            g.setColor(Color.blue);
                        }

                        g.drawLine(boxes[i][j].getSouth().getX1(), boxes[i][j].getSouth().getY1(), boxes[i][j].getSouth().getX2(), boxes[i][j].getSouth().getY2());
                    } else {
                        boxes[i][j].getSouth().nonHumanCatch();
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
                Line north = new Line(20 + space*j + 5, 20 + space*i + 5, 20+space*j +space + 5, 20+space*i + 5);

                Line south = new Line(20+ space*j + 5, 20 + space*i + space + 5,20+space*j +space + 5, 20+space*i + space + 5);

                Line west = new Line(20+space*j + 5, 20 +space*i + 5, 20 +space*j + 5 , 20+space*i + space + 5);

                Line east = new Line(20+space*j + space + 5, 20 + space*i + 5, 20 + space*j + space + 5, 20+space*i + space + 5);

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


        if(gameType == 1 && !frame.isPlaying()){
        }
        else if(gameType == 3){

        }
        else {
            for (int i = 0; i < n && !found; i++) {
                for (int j = 0; j < n && !found; j++) {

                    if (!controller.isLineTaken(i, j, Direction.SOUTH) && !found) { ///
                        x1 = boxes[i][j].getSouth().getX1();
                        y1 = boxes[i][j].getSouth().getY1();
                        x2 = boxes[i][j].getSouth().getX2();
                        y2 = boxes[i][j].getSouth().getY2();

                        if (e.getX() >= x1 && e.getX() <= x2 && e.getX() <= 20 + space * (n) && e.getY() >= y1 - 10 && e.getY() <= y2 + 10) {
                            // System.out.println(i+" " +j);
                            // System.out.println("entro en south");
                            if (controller.getTurn() == 1) {
                                boxes[i][j].getSouth().setColor(Color.yellow);
                                boxes[i][j].getSouth().setHumanCatch();
                                if (i < n - 1) {
                                    boxes[i + 1][j].getNorth().setColor(Color.yellow);
                                    boxes[i + 1][j].getNorth().setHumanCatch();
                                }
                            } else {
                                boxes[i][j].getSouth().setColor(Color.red);
                                boxes[i][j].getSouth().setHumanCatch();
                                if (i < n - 1) {
                                    boxes[i + 1][j].getNorth().setColor(Color.red);
                                    boxes[i + 1][j].getNorth().setHumanCatch();
                                }
                            }
                            controller.addLine(i, j, Direction.SOUTH);
                            found = true;
                        }

                    }

                    if (!controller.isLineTaken(i, j, Direction.NORTH) && !found) { ///
                        x1 = boxes[i][j].getNorth().getX1();
                        y1 = boxes[i][j].getNorth().getY1();
                        x2 = boxes[i][j].getNorth().getX2();
                        y2 = boxes[i][j].getNorth().getY2();


                        if (e.getX() >= x1 && e.getX() <= x2 && e.getX() <= 20 + space * (n) && e.getY() >= y1 - 10 && e.getY() <= y2 + 10) {
                            //  System.out.println("entro en north");
                            if (controller.getTurn() == 1) {
                                boxes[i][j].getNorth().setColor(Color.yellow);
                                boxes[i][j].getNorth().setHumanCatch();
                                if (i > 0) {
                                    boxes[i - 1][j].getSouth().setColor(Color.yellow);
                                    boxes[i - 1][j].getSouth().setHumanCatch();
                                }
                            } else {
                                boxes[i][j].getNorth().setColor(Color.red);
                                boxes[i][j].getNorth().setHumanCatch();
                                if (i > 0) {
                                    boxes[i - 1][j].getSouth().setColor(Color.red);
                                    boxes[i - 1][j].getSouth().setHumanCatch();
                                }
                            }
                            controller.addLine(i, j, Direction.NORTH);
                            found = true;
                        }
                    }


                    if (!controller.isLineTaken(i, j, Direction.WEST) && !found) {///
                        x1 = boxes[i][j].getWest().getX1();
                        y1 = boxes[i][j].getWest().getY1();
                        x2 = boxes[i][j].getWest().getX2();
                        y2 = boxes[i][j].getWest().getY2();


                        if (e.getY() >= y1 && e.getY() <= y2 && e.getY() <= 20 + space * (n) && e.getX() >= x1 - 10 && e.getX() <= x2 + 10) {
                            //  System.out.println("entro en west");
                            if (controller.getTurn() == 1) {
                                boxes[i][j].getWest().setColor(Color.yellow);
                                boxes[i][j].getWest().setHumanCatch();
                                if (j > 0) {
                                    boxes[i][j - 1].getEast().setColor(Color.yellow);
                                    boxes[i][j - 1].getEast().setHumanCatch();
                                }
                            } else {
                                boxes[i][j].getWest().setColor(Color.red);
                                boxes[i][j].getWest().setHumanCatch();
                                if (j > 0) {
                                    boxes[i][j - 1].getEast().setColor(Color.red);
                                    boxes[i][j - 1].getEast().setHumanCatch();
                                }
                            }
                            controller.addLine(i, j, Direction.WEST);
                            found = true;
                        }
                    }

                    if (!controller.isLineTaken(i, j, Direction.EAST) && !found) {


                        x1 = boxes[i][j].getEast().getX1();
                        y1 = boxes[i][j].getEast().getY1();
                        x2 = boxes[i][j].getEast().getX2();
                        y2 = boxes[i][j].getEast().getY2();

                        if (e.getY() >= y1 && e.getY() <= y2 && e.getY() <= 20 + space * (n) && e.getX() >= x1 - 10 && e.getX() <= x2 + 10) {
                            /// System.out.println(i+" "+j);
                            // System.out.println("entro en east");
                            if (controller.getTurn() == 1) {
                                // System.out.println("entro turno 1");
                                boxes[i][j].getEast().setColor(Color.yellow);
                                boxes[i][j].getEast().setHumanCatch();
                                if (j < n - 1) {
                                    boxes[i][j + 1].getWest().setColor(Color.yellow);
                                    boxes[i][j + 1].getWest().setHumanCatch();
                                }
                            } else {
                                // System.out.println("entro turno 2");
                                boxes[i][j].getEast().setColor(Color.red);
                                boxes[i][j].getEast().setHumanCatch();
                                if (j < n - 1) {
                                    boxes[i][j + 1].getWest().setColor(Color.red);
                                    boxes[i][j + 1].getWest().setHumanCatch();
                                }
                            }
                            //System.out.println("antes de llamar al controller");
                            controller.addLine(i, j, Direction.EAST);
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
