package com.cinder.juc.study.lock;

import lombok.Getter;

public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),
    FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    @Getter private int Code;
    @Getter private String message;

    CountryEnum(int code, String message) {
        Code = code;
        this.message = message;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if(index == countryEnum.Code){
                return countryEnum;
            }
        }

        return null;
    }
}
