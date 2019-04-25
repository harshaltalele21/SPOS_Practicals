%{
#include<stdio.h>
#include "y.tab.h"
%}
%%
(am|asks|be|becomes|begins|calls|can|comes|could|does|feels|finds|gets|gives|go|goes|has|hears|helps|keeps|knows|leaves|lets|likes|lives|looks|makes|may|means|might|moves|needs|plays|puts|runs|says|sees|seems|should|shows|starts|takes|talks|tells|thinks|tries|turns|uses|wants|will|works|would) return VERB;
"," return COMMA;
([.]) return FULLSTOP;
(and|but|or|yet|so|nor|because) return CONJUNCTION;

(of|in|to|for|with|from|behind|on|at|into|over|under|up|down) return PREPOSITION;
([a-zA-Z]+) return NOUN;
%%


