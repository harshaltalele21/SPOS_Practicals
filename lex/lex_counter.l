
%{
	#include<stdio.h>
	int linecnt=0, charcnt=0, wordcnt=0;
%}

%%

([\n]) {linecnt++;}
([a-zA-Z0-9]+) {wordcnt++; charcnt+=strlen(yytext);}
(.) {charcnt++;}

%%

void main(int argc, char **argv)
{
  FILE *file;
  if (argc == 2 && (file = fopen(argv[1], "r"))) {
    yyin = file;
  }
  yylex();
  printf("\nNumber of lines = %d",linecnt);
  printf("\nNumber of words = %d",wordcnt);
  printf("\nNumber of characters = %d",charcnt);
  printf("\n\n");
}
