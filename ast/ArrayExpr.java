package ast;

import java.io.PrintStream;
import java.util.List;

public class ArrayExpr extends Expr {
    public final String id;
    public final List<Expr> dims;

    public ArrayExpr(String i, List<Expr> d) {
        id = i;
        dims = d;
    }

    public void print(PrintStream ps) {
        ps.print(id);
        for (Expr d : dims) {
            ps.print("[");
            d.print(ps);
            ps.print("]");
        }
    }
}
