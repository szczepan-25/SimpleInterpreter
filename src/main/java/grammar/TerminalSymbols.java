package grammar;

import java.util.Arrays;


/**
 * <code>TerminaSymbols</code> object represents finite set of terminals, which make up the actual
 * content of the sentence. The set of terminals is the alphabet of the language defined by the
 * Grammar.
 */
public class TerminalSymbols {

    private final String[] terminalSymbols;

    public TerminalSymbols(String[] terminalSymbols){
        this.terminalSymbols = terminalSymbols;
    }

    public String[] getTerminalSymbols() {
        return terminalSymbols;
    }

    @Override
    public String toString() {

        return Arrays.toString(terminalSymbols);
    }

    public static void main(String[] args) {
        String[] tsArray = new String[]{
                "t", "e", "s", "t", "ts"
        };
        TerminalSymbols ts = new TerminalSymbols(tsArray);
        System.out.println(ts.toString());

        System.out.println(Integer.parseInt("-15"));
    }
}
