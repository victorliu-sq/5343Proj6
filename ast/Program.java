package ast;

import java.io.PrintStream;
import java.util.List;

public class Program extends ASTNode {
    public final String funName; // function name
    public final int retType; // function return type
    public final List<Decl> dList; // list of declarations
    public final List<Stmt> sList; // list of statements

    public Program(String i, int t, List<Decl> dl, List<Stmt> sl) {
        funName = i;
        retType = t;
        dList = dl;
        sList = sl;
    }

    public void print(PrintStream ps) {
        ps.println(Types.toString(retType) + " " + funName + "()\n{");
        for (Decl d : dList)
            d.print(ps);
        for (Stmt s : sList)
            s.print(ps, "  ");
        ps.println("}");
    }
}
