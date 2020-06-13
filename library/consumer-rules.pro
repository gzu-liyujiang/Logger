# 本库模块专用的混淆规则
#
# 正式发布包混淆去掉日志，叙保证没有配置`-dontoptimize`规则
-assumenosideeffects class android.util.Log{
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}
-assumenosideeffects class java.io.PrintStream {
    public *** println(...);
    public *** print(...);
}
