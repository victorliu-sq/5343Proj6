=====
package parser: 

Contains specifications of the scanner (Scanner.jflex) and the parser
(Parser.cup), as well as a helper class ParserWrapper. You need to
edit Scanner.jflex and Parser.cup to generalize the handling of int
literals, double literals, and binary operators. See TODO comments in
those files. The changes for binary operators require corresponding
changes to ast/BinaryExpr.java. After any change, run "make".

=====
package ast: 

1) Expresisons are represented by abstract class Expr and its five
subclasses ArrayExpr, BinaryExpr, DoubleConstExpr, IdentExpr,
IntConstExpr. AST nodes for expressions are instances of those five
subclasses. 

- DoubleConstExpr represents a numeric literal of type 'double'
- IntConstExpr represents a numeric literal of type 'int' 
- IdentExpr represents an identifier - e.g., a variable name 
- ArrayExpr represents a expression for an array element - e.g.,
  a[x][y+z] where a is declared earlier as a two-dimensional array
- BinaryExpr represents a binary operator applied to two
  subexpressions. Currently the implementation includes only operators
  + (add), * (multiply), and = (assignment, which in C is just another
  binary operator). In future projects you will add more operators.

2) Statements are represented by abstract class Stmt and its 
subclasses BlockStmt, EmptyStmt, ExprStmt, IfStmt, and ReturnStmt.
AST nodes for statements are instances of those five subclasses.

- BlockStmt represents a block "{ list of statements }"
- EmptyStmt represent a null statement ";" (Section 6.8.3 of the C spec)
- ExprStmt represents a statement "expression;" (see Sec 6.8.3)
- IfStmt represents if-then and if-then-else statements
- ReturnStmt represents "return expr;"

3) Decl represents declarations for scalars (e.g. "int x;") and arrays
(e.g., "int a[10][20]"). For simplicity, (1) only one variable can be
declared per Decl AST node, (2) declarations cannot include
initializations, and (3) arrays have sizes that are integer literals.
The parser enforces these constraints. Thus, the following examples
will trigger parse errors:
int x, y;
double pi = 3.14;
int b[x][3];

4) Program represents the root of the AST. A program contains a single
function with return type "int" or "double" and no parameters. The
function contains list of declarations followed by a list of
statements.

5) Types is a utility class.

=====
package compiler:

Class Compiler contains the top-level processing logic. The code
should be self-explanatory.
