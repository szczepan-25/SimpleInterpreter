package parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final String sentence;
    private final String symbol;
    private Node parent;
    private List<Node> children;

    public Node(String sentence, String symbol) {
        this.sentence = sentence;
        this.symbol = symbol;
        this.parent = null;
        this.children = new ArrayList<Node>();
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public String getSentence() {
        return sentence;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return this.symbol + " (" + this.sentence + ")";
    }
}
