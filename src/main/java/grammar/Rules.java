package grammar;

import java.util.ArrayList;
import java.util.List;


public class Rules {

    private String nts;
    private List<Rule> rules;

    public Rules(String nts, List<Rule> rules) {

        this.nts = nts;
        this.rules = rules;
    }

    public String getNts() {
        return nts;
    }

    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (rules != null) {

            sb.append("[");
            sb.append(this.nts);
            sb.append("->");
            boolean first = true;

            for (int i = 0; i < this.rules.size(); i++) {

                if (first) first = false;
                else sb.append("|");
                sb.append(rules.get(i).toString());
            }

            sb.append("]");
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        String nts = "S";
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule(new String[]{"S", " ", "R"}, null));
        rules.add(new Rule(new String[]{"S2"}, null));


        Rules rulesSet = new Rules(nts, rules);

        System.out.println(rulesSet.toString());
    }
}
