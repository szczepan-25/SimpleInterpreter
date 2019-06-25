package parser;

import grammar.Grammar;
import grammar.Rule;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final Grammar grammar;
    private Tree tree;

    public Parser(Grammar grammar) {

        this.grammar = grammar;
        this.tree = null;
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public boolean parseCode(String sentence) {

        Pattern pattern = Pattern.compile("([^\\s]*)\\s*?(.*)");
        Matcher matcher = pattern.matcher(sentence);

        String instruction = "";
        String expression = "";

        if (matcher.find()) {

             instruction = matcher.group(1);
             expression = matcher.group(2).replaceAll("\\s+", "");

            System.out.println("ins: " + instruction);
            System.out.println("exp: " + expression);

            sentence = instruction + " " + expression;
        }

        System.out.println(sentence);

        Node root = new Node(sentence, this.grammar.getS().getStartSymbol());
        this.tree = new Tree(root);

        return recursionParse(root.getSentence(), root.getSymbol(), root.getChildren());
    }

    private boolean recursionParse(String sentence, String symbol, List<Node> children) {

        System.out.println(symbol + " [" + sentence + "]");

        boolean correctNode = true;

        int idx = 0;
        boolean nSymbol = false;

        while (idx < grammar.getP().getProductionsSet().length
                && grammar.getP().getProductionsSet()[idx].getNts().compareTo(symbol) != 0)
            idx++;

        if (idx < grammar.getP().getProductionsSet().length)
            nSymbol = true;

        if (nSymbol) {

            List<Rule> symbolRules = grammar.getP().getProductionsSet()[idx].getRules();
            Pattern pattern = null;
            Matcher matcher = null;
            boolean  matchFind = false;

            for (Rule r : symbolRules) {

                pattern = r.getPattern();
                matcher = pattern.matcher(sentence);

                if (matcher.find()) {
                    for (int i = 0; i < r.getRule().length; i++)
                        children.add(new Node(matcher.group(i + 1), r.getRule()[i]));
                    matchFind = true;
                    break;
                }
            }

            if (matchFind) {

                for (Node n : children)
                    if (!recursionParse(n.getSentence(), n.getSymbol(), n.getChildren())) {
                        correctNode = false;
                        break;
                    }
            } else
                correctNode = false;
        } else {

            for (String s: grammar.getT().getTerminalSymbols())
                if (s.compareTo(sentence) == 0)
                    return true;

            correctNode = false;
        }

        return correctNode;
    }

    public void execute(Stack<Integer> stack, Map<String, Integer> varSpace) {

        String sentence = recursionExecute(tree.getRoot() ,tree.getRoot().getChildren());
        System.out.println("\nexec: " + sentence);
    }

    private String recursionExecute(Node node, List<Node> children) {

        String tempSentence = "";

        if (children.size() > 0) {
            for (int i = 0; i < children.size(); i++)
                tempSentence += recursionExecute(children.get(i) ,children.get(i).getChildren());

            if (tempSentence.matches("[(]?([-]?\\d+)[)]?")) {
                Matcher matcher = Pattern.compile("[(]?([-]?\\d+)[)]?").matcher(tempSentence);

                if (matcher.find())
                    return matcher.group(1);

            } else if (tempSentence.matches("[(]?[-]?\\d+[)]?[-+/*][(]?[-]?\\d+[)]?")) {

                Matcher matcher = Pattern.compile("[(]?([-]?\\d+)[)]?([-+/*])([-]?\\d+)").matcher(tempSentence);
                if (matcher.find()) {

                    int x1 = Integer.parseInt(matcher.group(1));
                    int x2 = Integer.parseInt(matcher.group(3));
                    int result = 0;

                    if (matcher.group(2).compareTo("+") == 0)
                        result = x1 + x2;
                    else if (matcher.group(2).compareTo("-") == 0)
                        result = x1 - x2;
                    else if (matcher.group(2).compareTo("/") == 0)
                        result = x1 / x2;
                    else if (matcher.group(2).compareTo("*") == 0)
                        result = x1 * x2;

                    return String.valueOf(result);
                }
            }
        } else {

            Pattern pattern = Pattern.compile("[^()]");
            Matcher matcher = pattern.matcher(node.getSentence());

            if (matcher.find())
                tempSentence = node.getSentence();
        }

        return tempSentence;
    }


    public static void main(String[] args) {

//        String regex = "\\s*(\\S+)(\\s)\\s*([^,]+)(,)\\s*(.+)";
//        String regex = "\\s*([\\S]*)\\s*([*])\\s*([(])(.*)([)])\\s*([+])\\s*(.*)";
//        String regex = "(\\s*([(].*[)])\\s*([+])\\s*(.*))|(\\s*([^+\\s]+)\\s*([+])\\s*(.*))";
//        String regex = "(?<=(\\(){4})(.+)(?=\\))";
//        String regex = "[^(]*(\\()[^(]*";
        String regex = "(?<=^(\\()).*(?=(\\))\\+)";
        String regex1 = "(.+)[+][(](?<=(\\+)(\\())(.+)(?=(\\))$)";
        String regex2 = "(?<=(\\())(.+)(?=(\\))(\\+))[)][+](.+)";
        String regex3 = "(?<=(\\())(.+)(?=(\\))(\\*)\\()[)][*][(](?<=(\\())(.+)(?=(\\)))[)]";
        String regex4 = "(.+)([-])(.+)";
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);
        Pattern pattern4 = Pattern.compile(regex4);
        Matcher matcher = pattern.matcher("(((test)test2)+cos)+cos2");
        Matcher matcher1 = pattern1.matcher("test+(((test1)test2)+test3)");
        Matcher matcher2 = pattern2.matcher("(((test)test2)+cos)+cos2");
        Matcher matcher3 = pattern3.matcher("(((test)test2)*cos)*(((test1)test2)*test3)");
        Matcher matcher4 = pattern4.matcher("1-2-3-4-5-6-7+8+9+0");


        if (matcher1.find()) {
            System.out.println(matcher1.groupCount());
            System.out.println(matcher1.group(0));

            for (int i = 0; i < matcher1.groupCount(); i++)
                System.out.println(matcher1.group(i + 1));
        }


        System.out.println("\n" + "(".matches("[(]?([-]?\\d+)[)]?"));

    }
}
