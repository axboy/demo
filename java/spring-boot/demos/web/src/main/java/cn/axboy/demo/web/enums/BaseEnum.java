package cn.axboy.demo.web.enums;

public interface BaseEnum {

    int getEnumCode();

    BaseEnum valueOf(int enumCode);

    default String i18nKey() {
        return null;
    }
}
