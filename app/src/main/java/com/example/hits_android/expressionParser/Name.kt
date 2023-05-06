package com.example.hits_android.expressionParser

enum class Name (val value: String) {
    FOR("for"),
    RAND("rand()"),
    IF("if"),
    ELSE("else"),
    OUTPUT("output"),
    WHILE("while"),
    VAR("var"),
    SPACE("space"),
    NEW_STRING("\n"),
    INT("Int"),
    ARRAY("Array"),
    STR("STR"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    OR("||"),
    AND("&&"),
    MOD("%"),
    ASSIGN("="),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),
    NUMBER("number"),
    VARIABLE("variable"),
    EQUALS("=="),
    NOT_EQUALS("!="),
    L_BRACKET("("),
    R_BRACKET(")"),
    L_SQUARE_BRACKET("["),
    R_SQUARE_BRACKET("]"),
    L_FIG_BRACKET("{"),
    R_FIG_BRACKET("}"),
    GREATER_OR_EQUALS(">="),
    LESS_OR_EQUALS("<="),
    GREATER(">"),
    LESS("<")
}