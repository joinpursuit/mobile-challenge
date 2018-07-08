// Generated by Dagger (https://google.github.io/dagger).
package nyc.c4q.vice.mobile;

import com.squareup.picasso.Picasso;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import nyc.c4q.vice.mobile.api.ApiModule;
import nyc.c4q.vice.mobile.api.ApiModule_ProvideHttpClientFactory;
import nyc.c4q.vice.mobile.api.ApiModule_ProvideMovieServiceFactory;
import nyc.c4q.vice.mobile.api.ApiModule_ProvideRetrofitFactory;
import nyc.c4q.vice.mobile.api.MovieService;
import nyc.c4q.vice.mobile.db.DatabaseModule;
import nyc.c4q.vice.mobile.db.DatabaseModule_ProvideDatabaseHelperFactory;
import nyc.c4q.vice.mobile.db.FavoritesDatabaseHelper;
import nyc.c4q.vice.mobile.presenters.MainPresenter;
import nyc.c4q.vice.mobile.ui.DetailsActivity;
import nyc.c4q.vice.mobile.ui.DetailsActivity_MembersInjector;
import nyc.c4q.vice.mobile.ui.FavoritesView;
import nyc.c4q.vice.mobile.ui.FavoritesView_MembersInjector;
import nyc.c4q.vice.mobile.ui.HomeView;
import nyc.c4q.vice.mobile.ui.HomeView_MembersInjector;
import nyc.c4q.vice.mobile.ui.MainView;
import nyc.c4q.vice.mobile.ui.MainView_MembersInjector;
import nyc.c4q.vice.mobile.ui.MovieViewHolder;
import nyc.c4q.vice.mobile.ui.MovieViewHolder_MembersInjector;
import nyc.c4q.vice.mobile.ui.PicassoModule;
import nyc.c4q.vice.mobile.ui.PicassoModule_ProvidePicassoFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class DaggerViceAppComponent implements ViceAppComponent {
  private AndroidModule_ProvideAppContextFactory provideAppContextProvider;

  private Provider<ResourceManager> provideResourceManagerProvider;

  private Provider<OkHttpClient> provideHttpClientProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<MovieService> provideMovieServiceProvider;

  private Provider<FavoritesDatabaseHelper> provideDatabaseHelperProvider;

  private Provider<Picasso> providePicassoProvider;

  private DaggerViceAppComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private MainPresenter getMainPresenter() {
    return new MainPresenter(provideResourceManagerProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideAppContextProvider =
        AndroidModule_ProvideAppContextFactory.create(builder.androidModule);
    this.provideResourceManagerProvider =
        DoubleCheck.provider(
            AndroidModule_ProvideResourceManagerFactory.create(
                builder.androidModule, provideAppContextProvider));
    this.provideHttpClientProvider =
        DoubleCheck.provider(ApiModule_ProvideHttpClientFactory.create(builder.apiModule));
    this.provideRetrofitProvider =
        DoubleCheck.provider(
            ApiModule_ProvideRetrofitFactory.create(builder.apiModule, provideHttpClientProvider));
    this.provideMovieServiceProvider =
        DoubleCheck.provider(
            ApiModule_ProvideMovieServiceFactory.create(
                builder.apiModule, provideRetrofitProvider));
    this.provideDatabaseHelperProvider =
        DoubleCheck.provider(
            DatabaseModule_ProvideDatabaseHelperFactory.create(
                builder.databaseModule, provideAppContextProvider));
    this.providePicassoProvider =
        DoubleCheck.provider(
            PicassoModule_ProvidePicassoFactory.create(
                builder.picassoModule, provideAppContextProvider, provideHttpClientProvider));
  }

  @Override
  public void inject(MainView view) {
    injectMainView(view);
  }

  @Override
  public void inject(HomeView view) {
    injectHomeView(view);
  }

  @Override
  public void inject(FavoritesView view) {
    injectFavoritesView(view);
  }

  @Override
  public void inject(DetailsActivity activity) {
    injectDetailsActivity(activity);
  }

  @Override
  public void inject(MovieViewHolder viewHolder) {
    injectMovieViewHolder(viewHolder);
  }

  private MainView injectMainView(MainView instance) {
    MainView_MembersInjector.injectPresenter(instance, getMainPresenter());
    return instance;
  }

  private HomeView injectHomeView(HomeView instance) {
    HomeView_MembersInjector.injectMovieService(instance, provideMovieServiceProvider.get());
    return instance;
  }

  private FavoritesView injectFavoritesView(FavoritesView instance) {
    FavoritesView_MembersInjector.injectDatabaseHelper(
        instance, provideDatabaseHelperProvider.get());
    return instance;
  }

  private DetailsActivity injectDetailsActivity(DetailsActivity instance) {
    DetailsActivity_MembersInjector.injectMovieService(instance, provideMovieServiceProvider.get());
    DetailsActivity_MembersInjector.injectDatabaseHelper(
        instance, provideDatabaseHelperProvider.get());
    DetailsActivity_MembersInjector.injectPicasso(instance, providePicassoProvider.get());
    return instance;
  }

  private MovieViewHolder injectMovieViewHolder(MovieViewHolder instance) {
    MovieViewHolder_MembersInjector.injectPicasso(instance, providePicassoProvider.get());
    return instance;
  }

  public static final class Builder {
    private AndroidModule androidModule;

    private ApiModule apiModule;

    private DatabaseModule databaseModule;

    private PicassoModule picassoModule;

    private Builder() {}

    public ViceAppComponent build() {
      if (androidModule == null) {
        throw new IllegalStateException(AndroidModule.class.getCanonicalName() + " must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      if (databaseModule == null) {
        this.databaseModule = new DatabaseModule();
      }
      if (picassoModule == null) {
        this.picassoModule = new PicassoModule();
      }
      return new DaggerViceAppComponent(this);
    }

    public Builder androidModule(AndroidModule androidModule) {
      this.androidModule = Preconditions.checkNotNull(androidModule);
      return this;
    }

    public Builder apiModule(ApiModule apiModule) {
      this.apiModule = Preconditions.checkNotNull(apiModule);
      return this;
    }

    public Builder databaseModule(DatabaseModule databaseModule) {
      this.databaseModule = Preconditions.checkNotNull(databaseModule);
      return this;
    }

    public Builder picassoModule(PicassoModule picassoModule) {
      this.picassoModule = Preconditions.checkNotNull(picassoModule);
      return this;
    }
  }
}
