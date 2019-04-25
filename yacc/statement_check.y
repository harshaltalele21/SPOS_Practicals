%{
#include<stdio.h>
int yylex();
void yyerror(const char *s);
%}

%token NOUN VERB COMMA FULLSTOP CONJUNCTION PREPOSITION

%%

start:simplestmt|complexstmt;

complexstmt:simple CONJUNCTION simple FULLSTOP
	|subject CONJUNCTION simple FULLSTOP
	|simple CONJUNCTION object FULLSTOP {printf("It is a complex sentence\n\n"); return 1;}
	;
simplestmt:simple FULLSTOP {printf("It is a simple sentence\n\n"); return 1;}
	;
simple:subject verb object;
subject:subject COMMA NOUN
	|NOUN
	;
object:object COMMA NOUN
	|NOUN
	|PREPOSITION NOUN
	;
verb:verb COMMA VERB
	|VERB
	;
%%


int main()
{
	printf("Enter the sentence : \n");
	yyparse();
	return 1;
}
