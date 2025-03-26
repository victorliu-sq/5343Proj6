package ast;

import java.io.PrintStream;

public class NotExpr extends Expr {
    public final Expr expr;

    public NotExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public void print(PrintStream ps) {
        // Common pattern for unary operators
        ps.print("(!");
        expr.print(ps);
        ps.print(")");
    }
}