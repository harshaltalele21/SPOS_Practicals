
%{
	#include<stdio.h>
%}

%%

(import[' ']([a-z]|.)+) {printf("\n%s is a library import",yytext);}

([A-Z](([a-zA-Z0-9]|[_])*)) {printf("\n%s is a class name",yytext);}

(["]([a-zA-Z0-9]|[ ])*["]) {printf("\n%s is a string literal",yytext);}

(void|int|float|char|boolean|double|byte|short|long|true|null|false|const|default|new|goto|static|if|else|for|do|while|switch|case|break|return|continue|public|private|protected|class|interface|extends|implements|try|thow|catch|throws) {printf("\n%s is a keyword",yytext);}

([<>=]|[<>]=) {printf("\n%s is a relational operator",
([a-zA-z]([a-zA-Z0-9])*) {printf("\n%s is an identifier or object",yytext);}

([a-zA-z]([a-zA-Z0-9])*[(](.)*[)]) {printf("\n%s is a function",yytext);}


([;\n\t(){}.]) {}
%%

void main(int argc, char **argv)
{
  FILE *file;
  if (argc == 2 && (file = fopen(argv[1], "r"))) {
    yyin = file;
  }
  yylex();
  printf("\n\n");
}
