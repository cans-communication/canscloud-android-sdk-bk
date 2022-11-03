
## OkHttp
#-keepattributes Signature
#-keepattributes Annotation
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**
#-dontwarn okio.**
#
## Retrofit
#-keep class com.google.gson.** { *; }
#-keep public class com.google.gson.** {public private protected *;}
#-keep class com.google.inject.** { *; }
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-keep class javax.inject.** { *; }
#-keep class javax.xml.stream.** { *; }
#-keep class retrofit.** { *; }
#-keep class com.google.appengine.** { *; }
#-keepattributes *Annotation*
#-keepattributes Signature
#-dontwarn com.squareup.okhttp.*
#-dontwarn rx.**
#-dontwarn javax.xml.stream.**
#-dontwarn com.google.appengine.**
#-dontwarn java.nio.file.**
#-dontwarn org.codehaus.**
#
#
#
#-dontwarn retrofit2.**
#-dontwarn org.codehaus.mojo.**
#-keep class retrofit2.** { *; }
#-keepattributes Exceptions
#-keepattributes RuntimeVisibleAnnotations
#-keepattributes RuntimeInvisibleAnnotations
#-keepattributes RuntimeVisibleParameterAnnotations
#-keepattributes RuntimeInvisibleParameterAnnotations
#
#-keepattributes EnclosingMethod
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-keepclasseswithmembers interface * {
#    @retrofit2.* <methods>;
#}
## Platform calls Class.forName on types which do not exist on Android to determine platform.
#-dontnote retrofit2.Platform
## Retain generic type information for use by reflection by converters and adapters.
#-keepattributes Signature
## Retain declared checked exceptions for use by a Proxy instance.
#-keepattributes Exceptions
#
#-keepattributes Signature
#
## For using GSON @Expose annotation
#-keepattributes *Annotation*


# Add any classes the interact with gson
# the following line is for illustration purposes
#-keepclasseswithmembernames class * {
#    native <methods>;
#}

-printmapping out.map
-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

# Preserve all annotations.

-keepattributes *Annotation*

# Preserve all public classes, and their public and protected fields and
# methods.

-keep public class * {
    public protected *;
}

# Preserve all .class method names.

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

# Preserve all native method names and the names of their classes.

-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration
# classes.

-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# The library may contain more items that need to be preserved;
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface
