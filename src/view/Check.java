package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    private static final String accountRegex = "^[a-z]{3}$";

    public Check() {
    }
    public boolean validate(String regex){
        Pattern pattern = Pattern.compile(accountRegex);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
