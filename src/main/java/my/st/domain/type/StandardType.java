package my.st.domain.type;

/**
 * 标准数据类别
 */
public enum StandardType {
    // 标志类
    FLAG("标志类"),
    // 编码类
    NO("编码类"),
    // 代码类
    CODE("代码类"),
    // 日期和时间类
    DATE("日期和时间类"),
    // 数值类
    NUM("数值类"),
    // 金额类
    MONEY("金额类"),
    //文本类
    TEXT("文本类");

    private final String name;

    StandardType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
