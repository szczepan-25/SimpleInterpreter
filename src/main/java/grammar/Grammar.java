package grammar;


/**
 * <code>Grammar</code> object represents context-free grammar (CFG): a set of
 * production rules (<code>ProductionsSet</code>) that describe all possible strings
 * in a given formal language.
 */
public class Grammar {

    private final TerminalSymbols t;
    private final NonterminalSymbols n;
    private final ProductionsSet p;
    private final StartSymbol s;

    public Grammar(TerminalSymbols t, NonterminalSymbols n,
                   ProductionsSet p, StartSymbol s) {

        this.t = t;
        this.n = n;
        this.p = p;
        this.s = s;
    }

    public TerminalSymbols getT() {
        return t;
    }

    public NonterminalSymbols getN() {
        return n;
    }

    public ProductionsSet getP() {
        return p;
    }

    public StartSymbol getS() {
        return s;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("T = {");
        sb.append(t.toString());
        sb.append("}\n");
        sb.append("N = {");
        sb.append(n.toString());
        sb.append("}\n");
        sb.append("P = {");
        sb.append(p.toString());
        sb.append("}\n");
        sb.append("S = {");
        sb.append(s.toString());
        sb.append("}");

        return sb.toString();
    }

    public static void main(String[] args) {
        Grammar grammar = new Grammar(new TerminalSymbols(new String[2]),
                new NonterminalSymbols(new String[2]),
                new ProductionsSet(new Rules[2]),
                new StartSymbol(null));

        System.out.println(grammar.toString());
    }
}