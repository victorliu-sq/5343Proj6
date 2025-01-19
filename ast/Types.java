package ast;

public class Types {
    public static final int INT = 1;
    public static final int DOUBLE = 2;

    public static String toString(int type) {
        switch (type) {
            case INT:
                return "int";
            case DOUBLE:
                return "double";
            default:
                return "?";
        }
    }
}
