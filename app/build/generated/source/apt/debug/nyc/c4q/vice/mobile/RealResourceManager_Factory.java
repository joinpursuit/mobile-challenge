// Generated by Dagger (https://google.github.io/dagger).
package nyc.c4q.vice.mobile;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RealResourceManager_Factory implements Factory<RealResourceManager> {
  private final Provider<Context> contextProvider;

  public RealResourceManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RealResourceManager get() {
    return provideInstance(contextProvider);
  }

  public static RealResourceManager provideInstance(Provider<Context> contextProvider) {
    return new RealResourceManager(contextProvider.get());
  }

  public static RealResourceManager_Factory create(Provider<Context> contextProvider) {
    return new RealResourceManager_Factory(contextProvider);
  }

  public static RealResourceManager newRealResourceManager(Context context) {
    return new RealResourceManager(context);
  }
}