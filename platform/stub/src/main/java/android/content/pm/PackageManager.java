package android.content.pm;

public abstract class PackageManager {
  public static int MATCH_APEX = -1;

  public abstract ModuleInfo getModuleInfo(String s, int i);

  public abstract PackageInfo getPackageInfo(String name, int matchApex) throws NameNotFoundException;

  public static class NameNotFoundException extends Exception {}
}
