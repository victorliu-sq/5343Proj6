package ast;

import java.io.PrintStream;

public class ReturnStmt extends Stmt {
    public final Expr expr;

    public ReturnStmt(Expr e) {
        expr = e;
    }

    public void print(PrintStream ps, String space) {
        ps.print(space + "return ");
        expr.print(ps);
        ps.println(";");
    }
}
