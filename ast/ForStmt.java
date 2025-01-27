package ast;

import java.io.PrintStream;

public class ForStmt extends Stmt {
    public final Expr init;
    public final Expr condition;
    public final Expr update;
    public final Stmt body;

    public ForStmt(Expr i, Expr c, Expr u, Stmt b) {
        init = i;
        condition = c;
        update = u;
        body = b;
    }

    @Override
    public void print(PrintStream ps, String space) {
        ps.print(space + "for (");
        if (init != null) {
            init.print(ps);
        }
        ps.print("; ");
        if (condition != null) {
            condition.print(ps);
        }
        ps.print("; ");
        if (update != null) {
            update.print(ps);
        }
        ps.println(")");
        body.print(ps, space + "  ");
    }
}
