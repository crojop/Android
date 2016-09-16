package org.upm.pregon.almonaciddelasierra;

public class WhatsappUtils {

    public static String toTitle(String str) {
        return "[ *"+str+"* ]";
    }

    public static String toBold(String str) {
        return "*"+str+"*";
    }

    public static String toItalic(String str) {
        return "_"+str+"_";
    }

    public static String getFirma() {
        return toItalic("--Ayto. Almonacid de la Sierra--");
    }
}
