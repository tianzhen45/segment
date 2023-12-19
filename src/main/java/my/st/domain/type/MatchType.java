package my.st.domain.type;

public enum MatchType {
    // 与标准完全相等的匹配
    STRICT("严格匹配"),

    // 与含义相同，用词不同的匹配
    COMPUTE("转换匹配"),

    // 模糊匹配，需人工识别
    VAGUE("模糊匹配");

    private final String name;

    MatchType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
