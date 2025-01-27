package ast;

import java.io.PrintStream;

public class WhileStmt extends Stmt {
    public final Expr expr;
    public final Stmt body;

    public WhileStmt(Expr e, Stmt b) {
        expr = e;
        body = b;
    }

    @Override
    public void print(PrintStream ps, String space) {
        ps.print(space + "while (");
        expr.print(ps);
        ps.println(")");
        body.print(ps, space + "  ");
    }
}
