package android.content;

import android.content.pm.PackageManager;

public abstract class Context {
  public abstract String getCacheDir();
  public abstract PackageManager getPackageManager();
}
