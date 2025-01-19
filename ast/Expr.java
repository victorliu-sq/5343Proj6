package ast;

import java.io.PrintStream;

public abstract class Expr extends ASTNode {
    public abstract void print(PrintStream ps);
}
