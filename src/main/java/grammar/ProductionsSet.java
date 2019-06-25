package grammar;


/**
 * <code>ProductionsSet</code> object represents set of relations. The members of this set are called
 * the rules or productionsSet of the Grammar.
 */
public class ProductionsSet {

    private final Rules[] productionsSet;

    public ProductionsSet(Rules[] productionsSet) {
        this.productionsSet = productionsSet;
    }

    public Rules[] getProductionsSet() {
        return productionsSet;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < productionsSet.length; i++)
            if (productionsSet[i] != null)
                sb.append(productionsSet[i].toString());

        return sb.toString();
    }
}
