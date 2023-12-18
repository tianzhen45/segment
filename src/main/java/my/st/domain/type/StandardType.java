package my.st.domain.type;

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
    // 其他类型
    OTHER("其他类型");

    private String name;

    StandardType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
