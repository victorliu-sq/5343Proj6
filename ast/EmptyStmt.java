package ast;

import java.io.PrintStream;

public class EmptyStmt extends Stmt {
    public void print(PrintStream ps, String space) {
        ps.println(space + ";");
    }
}
