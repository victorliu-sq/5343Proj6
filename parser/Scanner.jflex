package parser;

import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

import compiler.Compiler;

%%

%public
%class Lexer
%cup
%implements sym
%char
%line
%column

%{
    StringBuffer string = new StringBuffer();
    public Lexer(java.io.Reader in, ComplexSymbolFactory sf, String fn){
	this(in);
	symbolFactory = sf;
	filename = fn;
    }
    ComplexSymbolFactory symbolFactory;
    String filename;

  private Symbol symbol(String name, int sym) {
      return symbolFactory.newSymbol(name, sym, new Location(filename, yyline + 1, yycolumn + 1), new Location(filename, yyline + 1, yycolumn + yylength()));
  }

  private Symbol symbol(String name, int sym, Object val) {
      Location left = new Location(filename, yyline + 1, yycolumn + 1);
      Location right = new Location(filename, yyline + 1, yycolumn + yylength());
      return symbolFactory.newSymbol(name, sym, left, right, val);
  } 
%} 

%eofval{
     return symbolFactory.newSymbol("EOF", EOF, new Location(filename, yyline + 1, yycolumn + 1), new Location(filename, yyline + 1, yycolumn + 1));
%eofval}

/*** TODO START: Replace these placeholders with your own definitions ***/

/* Identifiers: TODO - make them general, as described in Section
 * 6.4.2.1 in the C spec. Ignore "universal-character-name" and
 * "other implementation-defined characters" mentioned in Section
 * 6.4.2.1.
 */

// Ident = [a-z]
non_digit = [_a-zA-Z]
digit = [0-9]
Ident = {non_digit}({digit}|{non_digit})*

/* Integer literals: TODO - handle integer literals as described in
 * Section 6.4.4.1 of the C spec. For simplicity, do NOT
 * handle the 'long', 'long long', and 'unsigned' cases. 
 * But DO handle hexadecimal and octal constants. You do NOT need to 
 * change the scanner action associated with IntLiteral; that action
 * (later in this file) calls Integer.decode,  which should process 
 * correctly the matched literal. Assume the value fits in a Java int.
 */

// IntLiteral = 0 | [1-9][0-9]*
// digit = [0-9]
nonzero_digit = [1-9]
decimal_constant = {nonzero_digit}({digit})*

octal_digit = [0-7]
octal_constant = 0{octal_digit}*

hexadecimal_digit = [0-9a-fA-F]
hexadecimal_prefix = 0[xX]
hexadecimal_constant = {hexadecimal_prefix}({hexadecimal_digit})+

IntLiteral = {decimal_constant} | {octal_constant} | {hexadecimal_constant}

/* Floating point literals: TODO - handle floating point literals as 
 * described in Section 6.4.4.2 of the C spec. For
 * simplicity, do NOT handle the 'long double' cases. But DO handle
 * hexadecimal floating constants, e/E and p/P notation, and f/F
 * suffixes. You do NOT need to change the scanner action associated 
 * with DoubleLiteral; that action (later in this file) calls 
 * Double.valueOf, which should process correctly the matched literal. 
 * Assume the value fits in a Java double.
 */        

// DoubleLiteral = [0-9]+ \. [0-9]*

floating_suffix = [fF]

sign = [+-]
digit_sequence = {digit}+
exponent_part = [eE] {sign}? {digit_sequence}
fractional_constant = ( {digit_sequence}? \. {digit_sequence} ) | ({digit_sequence} \.)
decimal_floating_constant = ({fractional_constant} {exponent_part}? {floating_suffix}?) | ({digit_sequence} {exponent_part} {floating_suffix}?)

hexadecimal_digit_sequence = {hexadecimal_digit}+
binary_exponent_part = [pP] {sign}? {digit_sequence}
hexadecimal_fractional_constant = ( {hexadecimal_digit_sequence}? \.  {hexadecimal_digit_sequence} )  | ({hexadecimal_digit_sequence} \.)
hexadecimal_floating_constant = {hexadecimal_prefix} ({hexadecimal_fractional_constant} | {hexadecimal_digit_sequence}) {binary_exponent_part} {floating_suffix}?

DoubleLiteral = {decimal_floating_constant} | {hexadecimal_floating_constant}

/*** TODO END ***/

new_line = \r|\n|\r\n;
white_space = {new_line} | [ \t\f]
%%
<YYINITIAL>{

/* keywords */
"int"             { return symbol("int",         INT); }
"double"          { return symbol("double",      DOUBLE); }
"return"          { return symbol("return",      RETURN); }
"if"              { return symbol("if",          IF); }
"else"            { return symbol("else",        ELSE); }

/* literals */
{IntLiteral}    { return symbol("Intconst", INTCONST, Integer.decode(yytext())); }
{DoubleLiteral} { return symbol("Doubleconst", DOUBLECONST, Double.valueOf(yytext())); }

/* names */
{Ident}           { return symbol("ID", IDENT, yytext()); }

/* punctuators */
";"               { return symbol(";",  SEMICOLON); }
"("               { return symbol("(",  LPAREN); }
")"               { return symbol(")",  RPAREN); }
"["               { return symbol("[",  LBRACK); }
"]"               { return symbol("]",  RBRACK); }
"{"               { return symbol("{",  LBRACE); }
"}"               { return symbol("}",  RBRACE); }

/*** TODO START: Add more operators ***/

"="               { return symbol("=",  ASSIGN); }
"+"               { return symbol("+",  PLUS); }
"*"               { return symbol("*",  MUL); }

"-"                { return symbol("-", SUB); }
"/"                { return symbol("/", DIV); }
"%"                { return symbol("%", MOD); }

"+="              { return symbol("+=", PLUS_ASSIGN); }
"-="              { return symbol("-=", SUB_ASSIGN); }
"*="              { return symbol("*=", MUL_ASSIGN); }
"/="              { return symbol("/=", DIV_ASSIGN); }
"%="              { return symbol("%=", MOD_ASSIGN); }

/*** TODO END ***/

"/*" [^*] ~"*/" | "/*" "*"+ "/"
                  { /* ignore comments */ }

{white_space}     { /* ignore */ }

}

/* error fallback */
[^]               { Compiler.fatalError("Illegal character <" + yytext() + ">", Compiler.EXIT_PARSING_ERROR); }