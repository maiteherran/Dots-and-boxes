package TPE.Timbiriche.View;

import TPE.Timbiriche.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameView {
    private JFrame frmDotsBoxes;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel p1NameLabel;
    private JLabel p2NameLabel;
    private int gameType;
    private int boardSize;
    private Controller controller;
    BoardView boardV;
    boolean playing;


    public FrameView(int gameType, int boardSize){
        this.gameType = gameType;
        this.boardSize = boardSize;
        this.playing = false;
        init();
    }

    public void init(){
        frmDotsBoxes = new JFrame();
        frmDotsBoxes.setTitle("Dots and Boxes");
        frmDotsBoxes.setSize(new Dimension(800, 605));
        frmDotsBoxes.setResizable(false);
        //frmDotsBoxes.setBounds(100, 100, 701, 447);
        frmDotsBoxes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDotsBoxes.setLocationRelativeTo(null); //myline



        frmDotsBoxes.setVisible(true);
        JPanel InfoPanel = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(195, 800);
            };
        };
        InfoPanel.setPreferredSize(new Dimension(195, 800));
        InfoPanel.setBackground(Color.white);
        frmDotsBoxes.getContentPane().add(InfoPanel, BorderLayout.EAST);

        JLabel lblNewLabel = new JLabel("Score");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setPreferredSize(new Dimension(190, 14));
        InfoPanel.add(lblNewLabel);








        this.p1NameLabel = new JLabel(">   Player 1    ");
        this.p2NameLabel = new JLabel("    Player 2    ");

        if(gameType == 1){
            this.p1NameLabel = new JLabel(">  Beatrix Kiddo   ");
            //this.p2NameLabel = new JLabel("   Player 2   ");
        }
        else if(gameType ==2){
            this.p2NameLabel = new JLabel("  Beatrix Kiddo   ");
            this.p1NameLabel = new JLabel(">    Player 1    ");
        }
        else if(gameType == 3){
            this.p1NameLabel = new JLabel(">  Beatrix Kiddo   ");
            this.p2NameLabel = new JLabel("   O-Ren Ishii   ");
        }

        InfoPanel.add(this.p1NameLabel);
        this.p1Score = new JLabel("0");
        p1Score.setFont(new Font("Tahoma", Font.PLAIN, 16));
        InfoPanel.add(p1Score);

        this.p2NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        InfoPanel.add(this.p2NameLabel);

        this.p2Score = new JLabel("0");
        p2Score.setFont(new Font("Tahoma", Font.PLAIN, 16));
        InfoPanel.add(this.p2Score);


        final JButton undoBtn = new JButton("UNDO");
        undoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.undo();
            }

        });
        undoBtn.setVisible(true);

        undoBtn.setBounds(20, 700, 88, 58);
        InfoPanel.add(undoBtn);


        final JButton startBtn = new JButton("START");
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(gameType == 1){
                    if(controller.getGame().getCurrentState().getTurn() == 1) {
                        playing = true;
                        controller.machineMove();
                    }
                }
                else if(gameType == 3){
                    controller.machineMove();
                }


            }

        });
        startBtn.setVisible(true);

        startBtn.setBounds(700, 700, 88, 58);
        InfoPanel.add(startBtn);

        final JButton dotBtn = new JButton("Generate dot file");
        dotBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.lastAIdot();
            }

        });
        dotBtn.setVisible(true);

        dotBtn.setBounds(700, 200, 88, 58);
        InfoPanel.add(dotBtn);




        boardV = new BoardView(this, gameType, boardSize);
        frmDotsBoxes.getContentPane().add(boardV, BorderLayout.WEST);

        //this.getFrame().revalidate();

    }

    public boolean isPlaying() {
        return playing;
    }

    public void p1TurnIndicatorOn() {
        if(gameType == 0 || gameType == 2) {
            this.p1NameLabel.setText(">    Player 1    ");
        }
        else  {
            this.p1NameLabel.setText(">  Beatrix Kiddo   ");
        }
        return;
    }

    public void  p1TurnIndicatorOff() {
        if(gameType == 0 || gameType == 2) {
            this.p1NameLabel.setText("    Player 1     ");
        }
        else  {
            this.p1NameLabel.setText("   Beatrix Kiddo    ");
        }
        return;
    }

    public void  p2TurnIndicatorOn(){
        if(gameType == 0 || gameType == 1) {
            this.p2NameLabel.setText(">    Player 2     ");
        }
        else  if(gameType == 2){
            this.p2NameLabel.setText(">    Beatrix Kiddo     ");
        }
        else{
            this.p2NameLabel.setText(">    O-Ren Ishii   ");
        }
        return;
    }

    public void  p2TurnIndicatorOff(){
        if(gameType == 0 || gameType == 1) {
            this.p2NameLabel.setText("    Player 2     ");
        }
        else  if(gameType == 2){
            this.p2NameLabel.setText("    Beatrix Kiddo     ");
        }
        else{
            this.p2NameLabel.setText("    O-Ren Ishii     ");
        }
        return;
    }

    public void setController(Controller controller){
        this.controller = controller;
        boardV.setController(controller);

    }

    public BoardView getBoardV(){
        return boardV;
    }

    public void updateScore(int p1, int p2){
        this.p1Score.setText(String.valueOf(p1));
        this.p2Score.setText(String.valueOf(p2));
    }

    public void endGame(int scoreP1, int scoreP2){
        String winner = new String();
        if(scoreP1 > scoreP2){
            if(gameType == 0){
                winner = "Player 1";
            }
            else if(gameType == 1){
                winner = "Beatrix Kiddo";
            }
            else if(gameType == 2){
                winner = "Player 1";
            }
            else{
                winner = "Beatrix Kiddo";
            }
        }
        else if (scoreP1 < scoreP2){
            if(gameType == 0){
                winner = "Player 2";
            }
            else if(gameType == 1){
                winner = "Player 2";
            }
            else if(gameType == 2){
                winner = "Beatrix Kiddo";
            }
            else{
                winner = "O-Ren Ishii";
            }
        }
        else{
            winner = "DRAW";
        }
        JFrame parent = new JFrame();
        int res = 2;
        if(winner.equals("DRAW")){
            JOptionPane.showMessageDialog(parent, "GAME OVER\n"+winner.toString());
        }
        else {
            JOptionPane.showMessageDialog(parent, "GAME OVER\n" + winner.toString() + " wins");
        }


     }



    public JFrame getFrame() {
        return this.frmDotsBoxes;
    }
}
