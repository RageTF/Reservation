package spring.fx.util;

import org.springframework.stereotype.Component;

/**
 * Created by Rage on 28.05.2017.
 */
public class Validator {

    private static final String EMAIL_REGEX="^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String SYMBOLS_REGEX = ".*[^а-яА-Яa-zA-Z\\S]+.*";
    private static final String NUMBERS_ONLY_REGEX = "\\d*";
    private static final String DATE_REGEX = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
    private static final String FULL_NAME_REGEX = "[а-яА-Яa-zA-Z]+\\s[а-яА-Яa-zA-Z]+";
    private static final String NUMBER_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    public static boolean validPhone(String text){
        if(text.matches(NUMBER_REGEX)){
            return true;
        }
        return false;
    }

    public static boolean validNumber(String text){
        if(text.matches(NUMBERS_ONLY_REGEX)){
            return true;
        }
        return false;
    }

    public static boolean validEmail(String text){
        if(text.matches(EMAIL_REGEX)){
            return true;
        }
        return false;
    }

}
