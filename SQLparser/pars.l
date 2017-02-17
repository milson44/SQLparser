%{
#include <stdio.h>
#include "y.tab.h"
#include <stdlib.h>

  void yyerror(char *);
  int lineNumber = 1;
  int positionString = 0;


%}
DIGIT [0-9]
LETTER [A-Za-z]
NUMBEROPER ["+""-""*"]
COMMA ","
QUOTES \'
DOT "."
OPEN \(
CLOSE \) 
END ";"
EQUAL "="
UNEQUAL "<>"
MORE ">"
LESS "<"
MORE_OR_EQUAL ">="
LESS_OR_EQUAL "<="
DELIMITER ["\t""\s"]
SYMBOL {DIGIT}|{LETTER}|{DOT}|{COMMA}|{END}|{DELIMITER}
NUMBER ("-"*)({DIGIT}+)({DIGIT}*)(".")*({DIGIT}*)
IDEN ({LETTER}+)("_")*({LETTER}*)({DIGIT}*)
LINE '{IDEN}*'

%%
(?i:update)          {printf("update\n"); return UPDATE;} 
(?i:set)             {printf("set\n"); return SET;} 
(?i:where)           {printf("where\n"); return WHERE;}
(?i:null)            {printf("null\n"); return NULLL;} 
(?i:default)         {printf("DEFAULT\n");return DEFAULT;}
(?i:not)             {printf("NOT\n");return NOT;}
(?i:or)              {printf("OR\n");return OR;}
(?i:and)             {printf("AND\n");return AND;}
{NUMBER}             {printf("NUMBER\n");return NUMBER;}
{NUMBEROPER}         {printf("NUMBEROPER\n");return NUMBEROPER;} 
{OPEN}               {printf("OPEN\n");return OPEN;}
{CLOSE}              {printf("CLOSE\n");return CLOSE;}
{IDEN}               {printf("ident\n"); return IDEN;}
{LINE}               {printf("LINE\n"); return LINE; }
{EQUAL}              {printf("equal\n"); return EQUAL;}
{UNEQUAL}            {printf("UNEQUAL\n"); return UNEQUAL;}
{MORE}               {printf("MORE\n"); return MORE;}
{LESS}               {printf("LESS\n"); return LESS;}
{MORE_OR_EQUAL}      {printf("MORE_OR_EQUAL\n");return MORE_OR_EQUAL;}
{LESS_OR_EQUAL}      {printf("LESS_OR_EQUAL\n");return LESS_OR_EQUAL;}
{COMMA}\             {printf("COMMA\n");return COMMA;}
{END}                {printf("END\n");return END;}

[\r\t ]				{;}
"\n" {lineNumber++;}
.            {
                printf(" Error: %s at line %i \n", yytext, lineNumber);
                positionString ++;
              }
%%
int yywrap(void) {
 return 1;
}