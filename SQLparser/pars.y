%{
#include <stdio.h>
#include <stdlib.h>
int yylex(void);
    
void yyerror(char *);
int lineNumber;
int positionString;
int ErrorCount2 = 0;
%} 

%token NUMBER
%token NUMBEROPER
%token OPEN
%token CLOSE
%token NULLL
%token DEFAULT
%token IDEN
%token LINE
%token EQUAL
%token UNEQUAL
%token MORE
%token LESS
%token MORE_OR_EQUAL
%token LESS_OR_EQUAL
%token NOT
%token OR
%token AND
%token COMMA
%token UPDATE
%token SET
%token WHERE
%token END


%%
query:
    UPDATE IDEN set_q
    {printf("UPDATE NOT WHERE\n");}
    |
    error IDEN set_q
    {printf("Пропущен UPDATE! \n");}
    |
    UPDATE error set_q
    {printf("Пропущен иден\n");}
    ;

set_q:
    SET assignment_more END
    |
    SET assignment_more WHERE condition END
    |
    error assignment_more END
    {printf("Пропущен SET!\n");}
    |
    error assignment_more WHERE condition END
    {printf("Пропущен SET!\n");}
    |
    SET assignment_more error condition END
    {printf("Пропущен WHERE!\n");}
    ;


assignment_more:
    assignment
   // {printf("присваивание\n");}
    |
    assignment COMMA assignment_more
    //{printf("присваивание, присваивание\n");}
    ;

assignment:
    IDEN EQUAL value
    //{printf("IDEN=value\n");}
    |
    IDEN EQUAL error
    {printf("Пропущено значение! \n");}
    |
    IDEN error value
    {printf("Это не присваивание! \n");}
    |
    error EQUAL value
    {printf("Пропущено название поля!\n");}
    ;
    

condition:   
    condition_factor
    //{printf("Фактор условия\n");}
    |   
    OPEN condition_factor CLOSE
    //{printf("(Фактор условия)\n");}
   /* |
    error
    {printf("Пропущен фактор условия!\n");}
    |
    OPEN error CLOSE
    {printf("Пропущен фактор условия!\n");}
    |
    OPEN condition_factor error
    {printf("Пропущена закрывающая скобка!\n");}
    |
    error condition_factor CLOSE
    {printf("Пропущена открывающая скобка!\n");}
    */;

condition_factor:
    NOT predikat
    //{printf("NOT predikat \n");}
    |
    predikat
    //{printf("predikat\n");}
    |   
    predikat CONDITION_OPER condition
    //{printf("predikat oper condition \n");}
    |
    NOT predikat CONDITION_OPER condition
    //{printf("NOT predikat oper condition \n");}
    |
    NOT error
    {yyerror("Пропущен предикат! \n");}
    |
    error
    {yyerror("Пропущен предикат! \n");}
    |
    NOT error CONDITION_OPER condition
    {yyerror("Пропущен предикат! \n");}
    |
    error CONDITION_OPER condition
    {yyerror("Пропущен предикат! \n");}
    |
    /*NOT predikat error condition
    {yyerror("Пропущен условный оператор!\n");}
    |
    predikat error condition
    {yyerror("Пропущен условный оператор!\n");}
    |*/
    NOT predikat OPERLIKE error
    {yyerror("Пропущено условие! \n");}
    |
    predikat OPERLIKE error
    {yyerror("Пропущено условие! \n");}
    
    ;
    

CONDITION_OPER:
    AND
   // {printf("AND\n");}
    |
    OR
    //{printf("OR\n");}
    |
    error
    {printf("Пропущен условный опреатор! \n");}
    ;


predikat:
    values_field OPERLIKE values_field 
    //{printf("PREDIKAT \n");} 
    |
    error OPERLIKE values_field
    {yyerror("Пропущена левое значение поля в предикате! \n");}
    |
    values_field OPERLIKE error
    {yyerror("Пропущена правое значение поля в предикате! \n");}
    |
    values_field error values_field
    {yyerror("Пропущен оператор сравнения! \n");}
   
    ;

OPERLIKE:  
    UNEQUAL
    //{printf("UNEQUAL\n");}
    |   
    LESS
    //{printf("LESS\n");}
    |   
    MORE
    //{printf("MORE\n");}
    |   
    LESS_OR_EQUAL
    //{printf("LESS_OR_EQUAL\n");}
    |   
    MORE_OR_EQUAL
    //{printf("MORE_OR_EQUAL\n");}
    |
    EQUAL
    //{printf("EQUAL\n");}
    ;

values_field:
     value
     //{printf("VALUE \n");}
     |
     IDEN
     //{printf("IDEN \n");}
     ;

value:
     LINE
     //{printf("STROKA \n");}
     |
     numeric_expression
     //{printf("numeric_expression\n");}
     |
     NULLL
    // {printf("PUSTOO \n");}
     |
     DEFAULT
    // {printf("DEFAULT \n");}
     ;


     
numeric_expression:
    //OPEN numeric_factor CLOSE
   // {printf("(numeric_expression))\n");}
    numeric_factor
    //{printf("numeric_expression\n");}
    ;

/*check_numeric_factor:
   
    ;
*/
numeric_factor:
    NUMBER NUMBEROPER numeric_expression
    // {printf("NUMBER NUMBEROPER numeric_expression\n");}
    |
    /*NUMBER error numeric_expression
     //{printf("Пропущен оператор! \n");}
    |*/
    error NUMBEROPER numeric_expression
     //{printf("Пропущено число!\n");}
    |
    NUMBER NUMBEROPER error
     //{printf("Пропущено число или числовое выражение! \n");}  
    |
    NUMBER
     //{printf("NUMBER\n");}
    |
     OPEN numeric_factor CLOSE 
    //{printf("(NUMERIC_FACTOR)\n");}
    /*|
    OPEN numeric_factor error
    {yyerror("Пропущена закрывающая скобка в числовом факторе! Завершение анализа выражения!");exit(1);}
    |
    error numeric_factor CLOSE
    {yyerror("Пропущена открывающая скобка в числовом факторе!");}
    |
    OPEN error CLOSE
    {yyerror("Пропущен числовой фактор!");}
    */
    ;

%%

void yyerror(char *s) 
{
 fprintf(stderr, "Ошибка в строке %i : %s\n", lineNumber, s);
 ErrorCount2++;
} 

int main(void)
{
 yyparse();
 printf("Количество ошибок, обнаруженных лексическим анализатором = %i ; \n", positionString);
printf("Количество ошибок, обнаруженных синтаксическим анализатором = %i ; \n", ErrorCount2);
if(positionString == 0 && ErrorCount2 == 0)
{
    printf("Успешное завершение анализа!!!\n"); 
}
else
{
    printf("Неудачное завершение анализа!!!\n");
}
return 0;
}



