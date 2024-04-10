    // Expression order in accordance with https://introcs.cs.princeton.edu/java/11precedence/

expression
    : primary
    | expression '[' expression ']'
    | expression bop = '.' (
        identifier
        | methodCall
        | THIS
        | NEW nonWildcardTypeArguments? innerCreator
        | SUPER superSuffix
        | explicitGenericInvocation
    )
    | methodCall
    | expression postfix = ('++' | '--')
    | prefix = ('+' | '-' | '++' | '--' | '~' | '!') expression
    | NEW creator
    ;

primary
    : '(' expression ')'
    | THIS
    | SUPER
    | literal
    | identifier
    ;