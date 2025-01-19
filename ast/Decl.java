package ast;

import java.io.PrintStream;
import java.util.List;

public class Decl extends ASTNode {
    public final String id;
    public final int type;
    public final List<Integer> dims; // empty for scalars

    public Decl(String i, int t, List<Integer> d) {
        id = i;
        type = t;
        dims = d;
    }

    public boolean isScalar() {
        return dims.size() == 0;
    }

    public void print(PrintStream ps) {
        ps.print("  " + Types.toString(type) + " " + id);
        if (isScalar()) {
            ps.println(";");
            return;
        }
        // array type
        for (Integer i : dims) {
            ps.print("[");
            ps.print(i);
            ps.print("]");
        }
        ps.println(";");
    }
}
