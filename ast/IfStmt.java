package ast;

import java.io.PrintStream;

public class IfStmt extends Stmt {
    public final Expr expr;
    public final Stmt thenStmt;
    public final Stmt elseStmt; // null for if-then

    public IfStmt(Expr e, Stmt ts) {
        expr = e;
        thenStmt = ts;
        elseStmt = null;
    }

    public IfStmt(Expr e, Stmt ts, Stmt es) {
        expr = e;
        thenStmt = ts;
        elseStmt = es;
    }

    public void print(PrintStream ps, String space) {
        ps.print(space + "if (");
        expr.print(ps);
        ps.println(")");
        thenStmt.print(ps, space + "  ");
        if (elseStmt != null) {
            ps.println(space + "else");
            elseStmt.print(ps, space + "  ");
        }
    }
}
