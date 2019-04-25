%{
#include<stdio.h>
int yylex();
void yyerror(const char *s);
%}

%token ID DATATYPE SC ACCESS COMMA

%%
start:ACCESS DATATYPE varlist SC {printf("Valid Syntax\n"); return 1;}
	;
varlist:varlist COMMA ID|ID;
%%


int main()
{
	printf("Enter variable declaration : \n");
	yyparse();
	return 1;
}
