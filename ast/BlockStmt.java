package ast;

import java.io.PrintStream;
import java.util.List;

public class BlockStmt extends Stmt {
    public final List<Stmt> sList;

    public BlockStmt(List<Stmt> sl) {
        sList = sl;
    }

    public void print(PrintStream ps, String space) {
        ps.println(space + "{");
        for (Stmt s : sList)
            s.print(ps, space + "  ");
        ps.println(space + "}");
    }
}

