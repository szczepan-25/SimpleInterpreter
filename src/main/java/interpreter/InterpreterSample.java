package interpreter;

import grammar.*;
import parser.Parser;

import java.util.*;

public class InterpreterSample {

    public static void main(String[] args) {

        String[] tsArray = new String[]{
                "(", ")", "+", "-", "*", "/", ","
                , "%eax", "%ebx", "%ecx", "%edx", "0x80"
                , "mov", "push", "int", "xor", "1", "2"
                , "3", "4", "5", "6", "7", "8", "9", "0"
                , " "
        };
        //Create set of grammar terminal symbols
        TerminalSymbols t = new TerminalSymbols(tsArray);


        String[] ntsArray = new String[]{
                "D", "E", "E2", "E3", "E4", "E5", "I", "I2", "N", "R", "S", "S2"
        };
        //Create set of grammar nonterminal symbols
        NonterminalSymbols n = new NonterminalSymbols(ntsArray);

        //Create list contains lists of rules (productions) to each nonterminal symbol
        List<List<Rule>> sRules = new ArrayList<List<Rule>>(ntsArray.length);
        for (int i = 0; i < ntsArray.length; i++)
            sRules.add(new ArrayList<Rule>());

        //set of rules to D symbol
        sRules.get(0).add(new Rule(new String[]{"1"}, "(1)"));
        sRules.get(0).add(new Rule(new String[]{"2"}, "(2)"));
        sRules.get(0).add(new Rule(new String[]{"3"}, "(3)"));
        sRules.get(0).add(new Rule(new String[]{"4"}, "(4)"));
        sRules.get(0).add(new Rule(new String[]{"5"}, "(5)"));
        sRules.get(0).add(new Rule(new String[]{"6"}, "(6)"));
        sRules.get(0).add(new Rule(new String[]{"7"}, "(7)"));
        sRules.get(0).add(new Rule(new String[]{"8"}, "(8)"));
        sRules.get(0).add(new Rule(new String[]{"9"}, "(9)"));
        sRules.get(0).add(new Rule(new String[]{"0"}, "(0)"));

        //set of rules to E symbol
        sRules.get(1).add(new Rule(new String[]{"(", "E", ")", "-", "(", "E", ")"}, "(?<=^(\\())(.+)(?=(\\))(\\-)\\()[)][-][(](?<=(\\())(.+)(?=(\\))$)[)]"));
        sRules.get(1).add(new Rule(new String[]{"(", "E", ")", "-", "E"}, "(?<=^(\\())(.+)(?=(\\))(\\-))[)][-](.+)"));
        sRules.get(1).add(new Rule(new String[]{"E", "-", "(", "E", ")"}, "(.+)[-][(](?<=(\\-)(\\())(.+)(?=(\\))$)[)]"));

        sRules.get(1).add(new Rule(new String[]{"(", "E", ")", "+", "(", "E", ")"}, "(?<=^(\\())(.+)(?=(\\))(\\+)\\()[)][+][(](?<=(\\())(.+)(?=(\\))$)[)]"));
        sRules.get(1).add(new Rule(new String[]{"(", "E", ")", "+", "E"}, "(?<=^(\\())(.+)(?=(\\))(\\+))[)][+](.+)"));
        sRules.get(1).add(new Rule(new String[]{"E", "+", "(", "E", ")"}, "(.+)[+][(](?<=(\\+)(\\())(.+)(?=(\\))$)"));

        sRules.get(1).add(new Rule(new String[]{"E2"}, "(.+)"));


        //set of rules to E2 symbol
        sRules.get(2).add(new Rule(new String[]{"(", "E", ")", "/", "(", "E", ")"}, "(?<=^(\\())(.+)(?=(\\))(/)\\()[)][/][(](?<=(\\())(.+)(?=(\\))$)[)]"));
        sRules.get(2).add(new Rule(new String[]{"(", "E", ")", "/", "E"}, "(?<=^(\\())(.+)(?=(\\))(/))[)][/](.+)"));
        sRules.get(2).add(new Rule(new String[]{"E", "/", "(", "E", ")"}, "(.+)[/][(](?<=(/)(\\())(.+)(?=(\\))$)[)]"));

        sRules.get(2).add(new Rule(new String[]{"(", "E", ")", "*", "(", "E", ")"}, "(?<=^(\\())(.+)(?=(\\))(\\*)\\()[)][*][(](?<=(\\())(.+)(?=(\\))$)[)]"));
        sRules.get(2).add(new Rule(new String[]{"(", "E", ")", "*", "E"}, "(?<=^(\\())(.+)(?=(\\))(\\*))[)][*](.+)"));
        sRules.get(2).add(new Rule(new String[]{"E", "*", "(", "E", ")"}, "(.+)[*][(](?<=(\\*)(\\())(.+)(?=(\\))$)[)]"));

        sRules.get(2).add(new Rule(new String[]{"E3"}, "(.+)"));



        //set of rules to E3 symbol
        sRules.get(3).add(new Rule(new String[]{"E", "-", "E"}, "(.+)([-])(.+)"));
        sRules.get(3).add(new Rule(new String[]{"E", "+", "E"}, "([^+]+)([+])(.+)"));
        sRules.get(3).add(new Rule(new String[]{"E4"}, "(.+)"));

        //set of rules to E4 symbol
        sRules.get(4).add(new Rule(new String[]{"E", "/", "E"}, "(.+)([/])(.+)"));
        sRules.get(4).add(new Rule(new String[]{"E", "*", "E"}, "([^*]+)([*])(.+)"));
        sRules.get(4).add(new Rule(new String[]{"E5"}, "(.+)"));

        //set of rules to E5 symbol
        sRules.get(5).add(new Rule(new String[]{"(", "E", ")"}, "(?<=(\\())(.+)(?=(\\)))"));
        sRules.get(5).add(new Rule(new String[]{"R"}, "^(%.+)"));
//        sRules.get(2).add(new Rule(new String[]{"-", "N"}, "([-])(\\d+)"));
        sRules.get(5).add(new Rule(new String[]{"N"}, "^(\\d+)$"));

        //set of rules to I symbol
        sRules.get(6).add(new Rule(new String[]{"int"}, "^(int)$"));
        sRules.get(6).add(new Rule(new String[]{"push"}, "^(push)$"));

        //set of rules to I2 symbol
        sRules.get(7).add(new Rule(new String[]{"mov"}, "^(mov)$"));
        sRules.get(7).add(new Rule(new String[]{"xor"}, "^(xor)$"));

        //set of rules to N symbol
        sRules.get(8).add(new Rule(new String[]{"D", "N"}, "^(\\d)(\\d+)$"));
        sRules.get(8).add(new Rule(new String[]{"D"}, "^(\\d)$"));

        //set of rules to R symbol
        sRules.get(9).add(new Rule(new String[]{"%eax"}, "^(%eax)$"));
        sRules.get(9).add(new Rule(new String[]{"%ebx"}, "^(%ebx)$"));
        sRules.get(9).add(new Rule(new String[]{"%ecx"}, "^(%ecx)$"));
        sRules.get(9).add(new Rule(new String[]{"%edx"}, "^(%edx)$"));

        //set of rules to S symbol
        sRules.get(10).add(new Rule(new String[]{"int", " ", "0x80"}, "^(int)(\\s)(0x80)"));
        sRules.get(10).add(new Rule(new String[]{"push", " ", "E"}, "^(push)(\\s)(.+)"));

        //set of rules to S2 symbol
        sRules.get(11).add(new Rule(new String[]{"I2", " ", "E", ",", "R"}, "(\\S+)(\\s)([^,]+)(,)(.+)"));
        sRules.get(11).add(new Rule( new String[]{"S"}, "(.+)"));

        //Create set of rules which contains pair of nonterminal symbol and corresponding list of productions
        Rules[] rules = new Rules[]{
                new Rules(ntsArray[0], sRules.get(0)), new Rules(ntsArray[1], sRules.get(1)), new Rules(ntsArray[2], sRules.get(2))
                ,new Rules(ntsArray[3], sRules.get(3)), new Rules(ntsArray[4], sRules.get(4)), new Rules(ntsArray[5], sRules.get(5))
                ,new Rules(ntsArray[6], sRules.get(6)), new Rules(ntsArray[7], sRules.get(7)), new Rules(ntsArray[8], sRules.get(8))
                ,new Rules(ntsArray[9], sRules.get(9)), new Rules(ntsArray[10], sRules.get(10)), new Rules(ntsArray[11], sRules.get(11))
        };
        //Create set of grammar productions
        ProductionsSet p = new ProductionsSet(rules);
        //Create grammar start symbol
        StartSymbol s = new StartSymbol(ntsArray[11]);
        //Create Grammar
        Grammar g = new Grammar(t, n, p, s);

        System.out.println(g.toString());

        Parser parser = new Parser(g);
        Stack<Integer> interpreterStack = new Stack<Integer>();
        Map<String, Integer> interpreterVarSpace = new HashMap<String, Integer>();

//        System.out.println(parser.parseCode("mov %ebx + 3 + %edx*2 + 5 - %ecx, %ecx"));

        interpreterVarSpace.put("accumulator", null);
        interpreterVarSpace.put("%eax", null);
        interpreterVarSpace.put("%ebx", null);
        interpreterVarSpace.put("%ecx", null);
        interpreterVarSpace.put("%edx", null);

//        interpreterStack.push(50);

        if (parser.parseCode("mov %ebx + 3 + (%edx*(2 + 5)) - %ecx, %ecx")) {
            parser.execute(interpreterStack, interpreterVarSpace);
        }
    }
}
