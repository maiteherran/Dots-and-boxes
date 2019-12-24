package TPE.Timbiriche.Model;



import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TreeBuilder {
    private int id;
    private String buffer;
    private Node rootNode;
    private int firstPlayer;
    private PrintWriter file;

    TreeBuilder(Node rootNode) {
        this.rootNode = rootNode;
        this.firstPlayer = rootNode.getState().getTurn();
        id = 0;
        buffer = "digraph G{\n";
    }

    TreeBuilder(int id, String buffer, Node rootNode, int firstPlayer){
        this.id = id;
        this.buffer = buffer;
        this.rootNode = rootNode;
        this.firstPlayer = firstPlayer;
    }

    public void lastAIdot(String fileName) {

        buffer= buffer.concat("}");

        try {
            file = new PrintWriter(fileName);

            file.write(buffer);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addChild(Node parent, Node child) {
        addNode(parent);
        addNode(child);
        buffer += parent.getId()+" -> "+child.getId()+"\n";
    }

    private void addNode(Node node) {
        if (node.getId() == 0) {
            node.setId(++id);
        }
    }

    public void changeNodeColour(Node node, String colour) {
        addNode(node);
        buffer += node.getId() + " [color= "+colour+" , style = filled ]\n";
    }

    public void setNodeText(Node node) {
        addNode(node);
        String nodeAspect;
        int currentPlayer = node.getState().getTurn() == 1 ? 2 : 1;
        if(currentPlayer == firstPlayer){
            nodeAspect = "box";
        }
        else{
            nodeAspect = "oval";
        }
        if (node.equals(rootNode)) {
            buffer += node.getId() + " [label = \"START "+firstPlayer+"\" shape = "+nodeAspect+", color = red, style = filled ]\n";
        }else {
            buffer += node.getId()+" [label = \""+node.toString()+"\" , shape = "+nodeAspect;
            if (node.isPruned()) {
                buffer+= ", color = grey, style = filled";
            }
            buffer+= "]\n";
        }
    }

    public void newDot(){
        id = 0;
        buffer = "digraph G{\n";
    }

    public TreeBuilder clone(){
        return new TreeBuilder(this.id, this.buffer,this.rootNode , this.firstPlayer);
    }

    public void close(){
        buffer= buffer.concat("}");
        file.close();
    }


}
