package my.st.domain.type;

public enum MatchType {
    // 与标准完全相等的匹配
    STRICT,

    // 与含义相同，用词不同的匹配
    COMPUTE,

    // 模糊匹配，需人工识别
    VAGUE
}
