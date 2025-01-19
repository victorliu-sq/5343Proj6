package ast;
import java.io.PrintStream;

public class IntConstExpr extends Expr {
    public final Integer ival; 
    public IntConstExpr(Integer i) {
	ival = i;
    }
    public void print(PrintStream ps) {
	ps.print(ival);
    }
}
