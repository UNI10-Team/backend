package com.uni10.backend.api.filter;

public enum Operator {

    EQUAL("eq"),
    NOT_EQUAL("ne"),
    LESS_THAN("lt"),
    LESS_OR_EQUAL("le"),
    GREATER_THAN("gt"),
    GREATER_OR_EQUAL("ge"),
    LIKE("lk"),
    DEFAULT("")
    ;

    private String stringFormat;

    Operator(String stringFormat) {
        this.stringFormat = stringFormat;
    }

    public static Operator fromSimpleFormat(final String stringFormat){
        for(Operator operator : values()){
            if(operator.stringFormat.equals(stringFormat)){
                return operator;
            }
        }
        return null;
    }
}
