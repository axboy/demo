package cn.axboy.demo.web.enums;

public enum AnimalEnum implements BaseEnum {

    DOGE(1),
    CAT(2),
    ;

    public int code;

    AnimalEnum(int code) {
        this.code = code;
    }

    @Override
    public int getEnumCode() {
        return code;
    }

    @Override
    public BaseEnum valueOf(int enumCode) {
        return null;
    }
}
