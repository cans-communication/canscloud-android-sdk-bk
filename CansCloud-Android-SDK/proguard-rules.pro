# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the CansCenter class and all its methods and fields
#-keep class com.cans.canscloud_android_sdk.CansCenter {
#    *;
#}

## Keep the companion object and its members (methods/fields)
#-keep class com.cans.canscloud_android_sdk.CansCenter$Companion {
#    *;
#}
#
## Ensure method names are not obfuscated, especially config method
#-keepnames class com.cans.canscloud_android_sdk.CansCenter$Companion {
#    public *;
#}
#
#-keep class com.cans.canscloud_android_sdk.CansCenter$Companion {
#     config(android.content.Context, android.content.pm.PackageManager, java.lang.String);
#}

#-keep class com.cans.canscloud_android_sdk.CansCenter$Companion  {
#   public void config(android.content.Context, android.content.pm.PackageManager, java.lang.String);
#   public void startCall(java.lang.String);
#   public java.lang.String username();
#}
#
#
#-keep class com.cans.canscloud_android_sdk.CansCenterTest {
#   public void config(android.content.Context, android.content.pm.PackageManager, java.lang.String);
#   public void startCall(java.lang.String);
#   public java.lang.String username();
#}

-keep class com.cans.canscloud_android_sdk.CansCenter {
    public <fields>;
    public <methods>;
}

-keep class com.cans.canscloud_android_sdk.CansCenterTest {
    public <fields>;
    public <methods>;
}

#-keepclassmembers class com.cans.canscloud_android_sdk.CansCenter$Companion { *; }