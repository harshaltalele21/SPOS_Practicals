%{
#include<stdio.h>
#include "y.tab.h"
%}
%%
public|private|protected return ACCESS;
int|float|char return DATATYPE;
"," return COMMA;
";" return SC;
[a-zA-Z][a-zA-Z0-9]* return ID;
%%


