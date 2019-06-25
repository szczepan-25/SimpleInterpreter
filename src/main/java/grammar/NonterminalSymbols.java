package grammar;

import java.util.Arrays;

/**
 * <code>NonterminalSymbols</code> object represents finite set of nonterminal characters (symbols).
 * Each variable represents a different type of phrase or clause in the sentence.
 * Each variable defines a sub-language of the language defined by Grammar.
 */
public class NonterminalSymbols {

    private final String[] nonterminalSymbols;

    public NonterminalSymbols(String[] nonterminalSymbols) {
        this.nonterminalSymbols = nonterminalSymbols;
    }

    public String[] getNonterminalSymbols() {
        return nonterminalSymbols;
    }

    @Override
    public String toString() {
        return Arrays.toString(nonterminalSymbols);
    }

    public static void main(String[] args) {
        String[] tsArray = new String[]{
                "t", "e", "s", "t", "nts"
        };
        TerminalSymbols ts = new TerminalSymbols(tsArray);
        System.out.println(ts.toString());
    }
}
