package TPE.Timbiriche.View;

import TPE.Timbiriche.Controller.Controller;
import TPE.Timbiriche.Model.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private JPanel contentPane;
    private JTextField time;
    private JTextField depth;
    private JTextField size;

    private JLabel info_lbl;
    private JLabel info2_lbl;
    private JLabel label;
    private JButton set_btn;
    private JButton set2_btn;
    private JButton machineTurn_btn;
    private JButton machineTurn2_btn;
    private JButton boardSizeBtn;

    private boolean mvm = false;
    private boolean mvh = false;
    private boolean hvh = false;
    private boolean machineFirst = false;
    private boolean machineSecond = false;
    private int timeMinimax = 0;
    private int depthMinimax = 0;
    private int boardSize = 0;
    private boolean pruneOn = false;
    private boolean pruneOff = false;
    private boolean timeOn =false;
    private boolean depthOn =false;
    private boolean depthSet =false;
    private boolean timeSet =false;
    private boolean boardSizeSet = false;





   // private JTextField machieneDificult;

    public HomeView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setBounds(100, 100, 800, 800);
        this.setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JButton pvm_btn = new JButton("Machine vs Human");
        final JButton pvp_btn = new JButton("Human vs Human");
        final JButton mvm_btn = new JButton("Machine vs Machine");
        final JButton pruneOn_btn = new JButton("Prune on");
        final JButton pruneOff_btn = new JButton("Prune off");

        mvm_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!mvm) {
                    mvm = true;
                    hvh=false;
                    mvh=false;
                    pvp_btn.setBackground(null);
                    pvm_btn.setBackground(null);
                    machineTurn2_btn.setBackground(null);
                    machineTurn_btn.setBackground(null);
                    mvm_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    mvm =false;
                    mvm_btn.setBackground(null);
                }
            }
        });
        mvm_btn.setBounds(20, 64, 200, 48);
        contentPane.add(mvm_btn);
/////////////////////////////////



        pvp_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!hvh) {
                    hvh = true;
                    mvm =false;
                    mvh =false;
                    mvm_btn.setBackground(null);
                    pvm_btn.setBackground(null);
                    machineTurn2_btn.setBackground(null);
                    machineTurn_btn.setBackground(null);
                    pvp_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    hvh =false;
                    pvp_btn.setBackground(null);
                }

            }
        });
        pvp_btn.setBounds(230, 64, 200, 48);
        contentPane.add(pvp_btn);

        ////////////////////////////////



        pvm_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                machineTurn_btn.setVisible(true);
                machineTurn_btn.setEnabled(true);

                machineTurn2_btn.setVisible(true);
                machineTurn2_btn.setEnabled(true);

                if(!mvh) {
                    mvh = true;
                    mvm =false;
                    hvh =false;
                    pvp_btn.setBackground(null);
                    mvm_btn.setBackground(null);

                    pvm_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    mvh =false;
                    pvm_btn.setBackground(null);
                    machineFirst= false;
                    machineSecond=false;

                    machineTurn_btn.setVisible(false);
                    machineTurn_btn.setEnabled(false);

                    machineTurn2_btn.setVisible(false);
                    machineTurn2_btn.setEnabled(false);
                    machineTurn_btn.setBackground(null);
                    machineTurn2_btn.setBackground(null);


                }


            }
        });
        pvm_btn.setBounds(440, 64, 200, 48);
        contentPane.add(pvm_btn);

        ///////////////////////////////////

        machineTurn_btn = new JButton("Machine moves first");
        machineTurn_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!machineFirst) {
                    machineFirst= true;
                    machineSecond=false;
                    machineTurn2_btn.setBackground(null);
                    machineTurn_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    machineFirst =false;
                    machineTurn_btn.setBackground(null);
                }

            }
        });
        machineTurn_btn.setBounds(440, 164, 200, 20);
        machineTurn_btn.setVisible(false);
        machineTurn_btn.setEnabled(false);

        contentPane.add(machineTurn_btn);




        machineTurn2_btn = new JButton("Machine moves second");
        machineTurn2_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!machineSecond) {
                    machineFirst= false;
                    machineSecond=true;
                    machineTurn_btn.setBackground(null);
                    machineTurn2_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    machineSecond =false;
                    machineTurn2_btn.setBackground(null);
                }

            }
        });
        machineTurn2_btn.setBounds(440, 194, 200, 20);
        machineTurn2_btn.setVisible(false);
        machineTurn2_btn.setEnabled(false);

        contentPane.add(machineTurn2_btn);




        //////////////////////////



        pruneOn_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!pruneOn) {
                    pruneOff = false;
                    pruneOn=true;
                    pruneOff_btn.setBackground(null);
                    pruneOn_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    pruneOn =false;
                    pruneOn_btn.setBackground(null);
                }
            }
        });
        pruneOn_btn.setBounds(20, 238, 100, 48);
        contentPane.add(pruneOn_btn);

        ////////////////////////////////



        pruneOff_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!pruneOff) {
                    pruneOff = true;
                    pruneOn= false;
                    pruneOn_btn.setBackground(null);
                    pruneOff_btn.setBackground(new Color(255, 102, 102));
                }
                else{
                    pruneOff =false;
                    pruneOff_btn.setBackground(null);
                }

            }
        });
        pruneOff_btn.setBounds(150, 238, 100, 48);
        contentPane.add(pruneOff_btn);

        //////////////////////////////////


        final JButton time_btn = new JButton("Time");
        final JButton depth_btn = new JButton("Depth");

        time_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!timeOn) {
                    timeOn =true;
                    depthOn= false;
                    depth_btn.setBackground(null);
                    time_btn.setBackground(new Color(255, 102, 102));

                    set2_btn.setVisible(false);
                    set2_btn.setEnabled(false);
                    info2_lbl.setVisible(false);
                    depth.setVisible(false);

                    set_btn.setVisible(true);
                    set_btn.setEnabled(true);
                    time.setVisible(true);
                    info_lbl.setVisible(true);
                }
                else{
                    timeOn =false;
                    set_btn.setVisible(false);
                    set_btn.setEnabled(false);
                    time.setVisible(false);
                    info_lbl.setVisible(false);

                    time_btn.setBackground(null);
                }



            }
        });
        time_btn.setBounds(20, 338, 100, 48);
        contentPane.add(time_btn);

        /////////////////////////////////

        set_btn = new JButton("Set Time");
        set_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                try {
                    //long number = Long.parseLong(time.getText());
                    int number = Integer.parseInt(time.getText());
                    if(number > 0){
                        timeSet = true;
                        set_btn.setBackground(new Color(255, 102, 102));
                        timeMinimax = number;
                    }
                    else{
                        info_lbl.setText("<html>Set a valid number<br> greater than 0<html>");
                        set_btn.setBackground(null);
                    }
                }
                catch (Exception e) {
                    info_lbl.setText("<html>Set a valid number<br> greater than 0<html>");
                    set_btn.setBackground(null);
                }
            }
        });
        set_btn.setBounds(260, 548, 109, 20);
        set_btn.setVisible(false);
        set_btn.setEnabled(false);
        contentPane.add(set_btn);



        time = new JTextField("Seconds");
        time.setBounds(150, 548, 109, 20);
        contentPane.add(time);
        time.setColumns(10);

        info_lbl = new JLabel("<html>Choose the amount of time minimax algorithm runs<html>");
        info_lbl.setBounds(150, 448, 109, 109);
        info_lbl.setVisible(false);
        contentPane.add(info_lbl);
        time.setVisible(false);








        /////////////////////////////////////



        depth_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(!depthOn) {
                    timeOn =false;
                    depthOn= true;
                    time_btn.setBackground(null);
                    depth_btn.setBackground(new Color(255, 102, 102));

                    set_btn.setVisible(false);
                    set_btn.setEnabled(false);
                    time.setVisible(false);
                    info_lbl.setVisible(false);

                    set2_btn.setVisible(true);
                    set2_btn.setEnabled(true);
                    depth.setVisible(true);
                    info2_lbl.setVisible(true);
                }
                else{
                    depthOn =false;
                    depth_btn.setBackground(null);
                    set2_btn.setVisible(false);
                    set2_btn.setEnabled(false);
                    depth.setVisible(false);
                    info2_lbl.setVisible(false);
                }


               /* set_btn.setVisible(false);
                set_btn.setEnabled(false);
                machieneDificult.setVisible(false);
                info_lbl.setVisible(false);
                machineON = false;
                pvp= true;
                */

            }
        });
        depth_btn.setBounds(150, 338, 100, 48);
        contentPane.add(depth_btn);
        //////////////////////////////

        set2_btn = new JButton("Set Depth");
        set2_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    //long number = Long.parseLong(depth.getText());
                    int number = Integer.parseInt(depth.getText());
                    if(number > 0){
                        set2_btn.setBackground(new Color(255, 102, 102));
                        depthMinimax = number;
                        depthSet =true;

                    }
                    else{
                        info2_lbl.setText("<html>Set a valid number<br> bretween 0 and xxxxx <html>");
                        depthSet =false;
                        set2_btn.setBackground(null);
                    }
                }
                catch (Exception e) {
                    info2_lbl.setText("<html>Set a valid number<br> between 0 and xxxxxx<html>");
                    depthSet=false;
                    set2_btn.setBackground(null);
                }
            }
        });
        set2_btn.setBounds(260, 548, 109, 20);
        set2_btn.setVisible(false);
        set2_btn.setEnabled(false);
        contentPane.add(set2_btn);



        depth = new JTextField("Depth");
        depth.setBounds(150, 548, 109, 20);
        contentPane.add(depth);
        depth.setColumns(10);

        info2_lbl = new JLabel("<html>Choose the depth the minimax algorithm reaches<html>");
        info2_lbl.setBounds(150, 448, 109, 109);
        info2_lbl.setVisible(false);
        contentPane.add(info2_lbl);
        depth.setVisible(false);









        /////////////////////////////


        boardSizeBtn = new JButton("Set");
        boardSizeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                try {
                    int number = Integer.parseInt(size.getText());
                    if(number > 0 && number <=20){
                        boardSizeSet = true;
                        boardSizeBtn.setBackground(new Color(255, 102, 102));
                        boardSize = number;
                    }
                    else{
                        info_lbl.setText("<html>Set a valid number<br> greater than 0 and smaller than 20<html>");
                        boardSizeBtn.setBackground(null);
                    }
                }
                catch (Exception e) {
                    info_lbl.setText("<html>Set a valid number<br> greater than 0<html>");
                    boardSizeBtn.setBackground(null);
                }
            }
        });
        boardSizeBtn.setBounds(130, 600, 109, 20);
        boardSizeBtn.setVisible(true);
        boardSizeBtn.setEnabled(true);
        contentPane.add(boardSizeBtn);



        size = new JTextField("n*n");
        size.setBounds(20, 600, 109, 20);
        contentPane.add(size);
        size.setColumns(10);

        label = new JLabel("<html>Set board size<html>");
        label.setBounds(20, 530, 109, 109);
        label.setVisible(true);
        contentPane.add(label);





        ///////////////////////////////////////





        final JButton start_btn = new JButton("START");
        start_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                Game game = new Game();
//                Controller controller = new Controller();
//                FrameView frameV = new FrameView(3);


                if((hvh|| ((mvm||(machineFirst || machineSecond)) && (pruneOn || pruneOff) && (timeSet || depthSet) ))&& boardSizeSet){
                    start_btn.setBackground(new Color(255, 102, 102));
                    Game game = new Game(boardSize);
                    Controller controller;
                    int gameType=-1;
                    int minMaxCut = -1;
                    if(mvm){
                        gameType = 3;
                    }
                    else if(mvh){
                        if(machineFirst){
                            gameType =1;
                        }
                        else{
                            gameType =2;
                        }
                    }
                    else if(hvh){
                        gameType=0;
                    }

                    FrameView frameV = new FrameView(gameType, boardSize);
                    controller = new Controller(game, frameV, gameType, timeSet, timeMinimax , depthSet,depthMinimax ,pruneOn);
                    frameV.setController(controller);
                }
                else{

                }



                //mostrar un pop up q diga error en el ingreso de argumentos
            }

        });
        start_btn.setBounds(600, 600, 88, 58);
        contentPane.add(start_btn);











    }

}
