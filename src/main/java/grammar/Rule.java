package grammar;

import java.util.regex.Pattern;

public class Rule {

    private final String[] rule;
    private final Pattern pattern;

    public Rule(String[] rule, String regex) {
        this.rule = rule;
        if (regex != null)
            this.pattern = Pattern.compile(regex);
        else
            this.pattern = null;
    }

    public String[] getRule() {
        return rule;
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rule.length; i++)
            sb.append(rule[i]);

        return sb.toString();
    }
}
