package PruebasEspecificaciones;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Busca un String para subcadenas delimitadas por una etiqueta start y end,
     * y devuelve todas las subcadenas coincidentes en un arreglo.
     *
     * @param str   el String que contiene las subcadenas, null devuelve null, empty devuelve empty
     * @param open  el String identifica el inicio de la subcadena, empty retorna null
     * @param close the String identifica el fin de la subcadena, empty retorna null
     * @return un arreglo String de subcadenas o {@code null} si no empareja
     */
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if(str==null || isEmpty(open) || isEmpty(close)){
            return null;
        }
        int strLen = str.length();
        if(strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<>();
        int pos = 0;

        while(pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if(start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if(end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if(list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }
}


