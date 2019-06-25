package grammar;


/**
 * <code>StartSymbol</code> object represents start variable, used to represent the whole sentence
 * (or program). It must be an element of <code>NonterminalSymbols</code>.
 */
public class StartSymbol {

    private String startSymbol;

    public StartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
    }

    @Override
    public String toString() {
        return "[" + startSymbol + "]";
    }
}
