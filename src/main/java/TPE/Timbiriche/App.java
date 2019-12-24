package TPE.Timbiriche;
import TPE.Timbiriche.Controller.Controller;
import TPE.Timbiriche.Model.Game;
import TPE.Timbiriche.View.FrameView;
import org.apache.commons.cli.*;

import java.security.InvalidParameterException;

public class App {
    public static void main( String[] args) {
        Options options = new Options();
        options.addOption(new Option( "size", true, "Tamaño del lado del tablero"));
        options.addOption(new Option("load", true, "Carga una partida guardada. File es una referencia al archivo donde se guardó el tablero "));
        options.addOption(new Option("ai", true, "0: no hay AI, 1: AI mueve primero, 2: AI mueve segundo, 3: AI vs AI"));
        options.addOption(new Option( "mode", true, "[time | depth] determina si el algoritmo minimax se corre por tiempo o por profundidad "));
        options.addOption(new Option("param", true, "Acompaña al parámetro anterior. En el caso de “time”, k deben ser los segundos. En el caso de “depth”, debe ser la profundidad del árbol."));
        options.addOption(new Option("prune", true, "Activa o desactiva la poda"));

        boolean pruneOn;
        boolean timeSet;
        boolean depthSet;
        long timeMinimax = 0;
        long depthMinimax = 0;
        char gameType;
        int boardSize;



        CommandLineParser parser = new DefaultParser();
        try {

            CommandLine commands = parser.parse(options, args);

            if(commands.hasOption("ai") && commands.hasOption("size") && commands.hasOption("mode") && commands.hasOption("param") && commands.hasOption("prune")) {
                /////////////////////////////
                if (commands.getOptionValue("ai").equals("0")) {
                    gameType = 0;
                } else if (commands.getOptionValue("ai").equals("1")) {
                    gameType = 1;
                } else if (commands.getOptionValue("ai").equals("2")) {
                    gameType = 2;
                } else if (commands.getOptionValue("ai").equals("3")) {
                    gameType = 3;
                } else {
                    System.out.println("ai");
                    throw new InvalidParameterException();
                }

                //////////////////////////

                if (Integer.valueOf(commands.getOptionValue("size")) <= 0 || Integer.valueOf(commands.getOptionValue("size")) > 20) {
                    System.out.println("size");
                    throw new InvalidParameterException();
                } else {
                    boardSize = Integer.valueOf(commands.getOptionValue("size"));
                }

                //////////////////////////

                if (commands.getOptionValue("mode").equals("time")) {
                    timeSet = true;
                    depthSet = false;
                    timeMinimax = Integer.valueOf(commands.getOptionValue("param"));
                } else if (commands.getOptionValue("mode").equals("depth")) {
                    timeSet = false;
                    depthSet = true;
                    depthMinimax = Integer.valueOf(commands.getOptionValue("param"));
                } else {
                    System.out.println("mode");
                    throw new InvalidParameterException();
                }
                //////////////////////////

                if (commands.getOptionValue("prune").equals("on")) {
                    pruneOn = true;
                } else if (commands.getOptionValue("prune").equals("off")){
                    pruneOn = false;
                }
                else {
                    System.out.println("prune");
                    throw new InvalidParameterException();
                }

            }
            else{
               throw new InvalidParameterException();
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return;
        } catch(InvalidParameterException e){
            System.out.println(options.toString());
            return;
        }


        Game game = new Game(boardSize);
        Controller controller;
        FrameView frameV = new FrameView(gameType, boardSize);
        controller = new Controller(game, frameV, gameType, timeSet, timeMinimax , depthSet,depthMinimax ,pruneOn);
        frameV.setController(controller);


    }
}
