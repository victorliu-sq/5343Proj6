package ast;

import java.io.PrintStream;

public class DoubleConstExpr extends Expr {
    public final Double dval;

    public DoubleConstExpr(Double d) {
        dval = d;
    }

    public void print(PrintStream ps) {
        ps.print(dval);
    }
}
