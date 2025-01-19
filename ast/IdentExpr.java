package ast;

import java.io.PrintStream;

public class IdentExpr extends Expr {
    public final String id;

    public IdentExpr(String i) {
        id = i;
    }

    public void print(PrintStream ps) {
        ps.print(id);
    }
}
