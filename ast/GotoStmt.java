package ast;

import java.io.PrintStream;

public class GotoStmt extends Stmt {
    public final String labelName;

    public GotoStmt(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public void print(PrintStream ps, String indent) {
        ps.println(indent + "goto " + labelName + ";");
    }
}
