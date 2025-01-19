package ast;

import java.io.PrintStream;

public abstract class Stmt extends ASTNode {
    public abstract void print(PrintStream ps, String space);
}
