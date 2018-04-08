import java_cup.runtime.Symbol;

%%

%class ExprLex
%cup
%implements sym
%line
%column
%ignorecase
%eofclose

%{
  private void error(String message) {
    System.err.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }
%} 

int = 0 | -?[1-9][0-9]*
new_line = \r|\n|\r\n|\z;
white_space = {new_line} | [ \t\f]

%%


/* keywords */
"+"	{ return new Symbol(PLUS, yyline+1, yycolumn+1); }
"-"	{ return new Symbol(MINUS, yyline+1, yycolumn+1); }
"*"	{ return new Symbol(TIMES, yyline+1, yycolumn+1); }
"/"	{ return new Symbol(DIV, yyline+1, yycolumn+1); }
"("	{ return new Symbol(LPAREN, yyline+1, yycolumn+1); }
")"	{ return new Symbol(RPAREN, yyline+1, yycolumn+1); }

{int}	{return new Symbol(INT, yyline+1, yycolumn+1, new Integer(Integer.parseInt(yytext())));}

{new_line} { return new Symbol(EOL, yyline+1, yycolumn+1); }

{white_space}	{ /* ignore */ }

/* error fallback */
.|\n	{ error("Illegal character <"+ yytext()+">"); }
