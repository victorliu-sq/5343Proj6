package ast;

import java.io.PrintStream;

public class BinaryExpr extends Expr {
    public static final int PLUS = 1;
    public static final int MUL = 2;
    public static final int ASSIGN = 3;

    // Project 1: added by Jiaxin
    public static final int SUB = 4;
    public static final int DIV = 5;
    public static final int MOD = 6;
    public static final int PLUS_ASSIGN = 7;
    public static final int SUB_ASSIGN = 8;
    public static final int MUL_ASSIGN = 9;
    public static final int DIV_ASSIGN = 10;
    public static final int MOD_ASSIGN = 11;

    // Project 2: New relational and equality operators
    public static final int LT = 12;
    public static final int LE = 13;
    public static final int GT = 14;
    public static final int GE = 15;
    public static final int EQ = 16;
    public static final int NE = 17;

    public final Expr expr1, expr2;
    public final int op;

    public BinaryExpr(Expr e1, int oper, Expr e2) {
        expr1 = e1;
        expr2 = e2;
        op = oper;
    }

    public void print(PrintStream ps) {
        ps.print("(");
        expr1.print(ps);
        switch (op) {
            case PLUS:
                ps.print("+");
                break;
            case MUL:
                ps.print("*");
                break;
            case ASSIGN:
                ps.print("=");
                break;
            // Project 1
            case SUB:
                ps.print("-");
                break;
            case DIV:
                ps.print("/");
                break;
            case MOD:
                ps.print("%");
                break;
            case PLUS_ASSIGN:
                ps.print("+=");
                break;
            case SUB_ASSIGN:
                ps.print("-=");
                break;
            case MUL_ASSIGN:
                ps.print("*=");
                break;
            case DIV_ASSIGN:
                ps.print("/=");
                break;
            case MOD_ASSIGN:
                ps.print("%=");
                break;
            // Project 2
            case LT:
                ps.print("<");
                break;
            case LE:
                ps.print("<=");
                break;
            case GT:
                ps.print(">");
                break;
            case GE:
                ps.print(">=");
                break;
            case EQ:
                ps.print("==");
                break;
            case NE:
                ps.print("!=");
                break;
        }
        expr2.print(ps);
        ps.print(")");
    }
}
