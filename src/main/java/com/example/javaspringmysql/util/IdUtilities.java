package com.example.javaspringmysql.util;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class IdUtilities {
    
    /**
     * Generates automatic 12 bytes ID (24 characters), only if  
     * there is no id given in the request of the POST operation 
     * @return random string, made only of hexa characters
     */
    public static String generateId(){
        final String lower = "abcdef";
        final String digits = "0123456789";
        final String alphanum = lower + digits;
        final Random random = Objects.requireNonNull(new SecureRandom());
        final char[] symbols = alphanum.toCharArray();
        final char[] buf = new char[24];;
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

}
