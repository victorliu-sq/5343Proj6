package ast;

import java.io.PrintStream;

public class LabelStmt extends Stmt {
    public final String labelName;

    public LabelStmt(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public void print(PrintStream ps, String indent) {
        ps.println(indent + labelName + ":");
    }
}
