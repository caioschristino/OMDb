package caio.com.omdb.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import caio.com.cache.BuildConfig
import caio.com.cache.OmdbCacheImpl
import caio.com.cache.PreferencesHelper
import caio.com.cache.db.OmdbDatabase
import caio.com.cache.mapper.OmdbEntityMapper
import caio.com.data.OmdbDataRepository
import caio.com.data.executor.JobExecutor
import caio.com.data.repository.OmdbCache
import caio.com.data.repository.OmdbRemote
import caio.com.data.source.OmdbDataStoreFactory
import caio.com.domain.PostExecutionThread
import caio.com.domain.ThreadExecutor
import caio.com.domain.repository.OmdbRepository
import caio.com.omdb.injection.scopes.PerApplication
import caio.com.omdb.ui.UiThread
import caio.com.remote.OmdbAPIService
import caio.com.remote.ServiceFactory
import caio.com.remote.source.OmdbRemoteImpl
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @PerApplication
    internal fun provideOmdbRepository(
        factory: OmdbDataStoreFactory,
        mapper: caio.com.data.mapper.OmdbEntityMapper,
        mapperResponse: caio.com.data.mapper.OmdbResponseEntityMapper
    ): OmdbRepository {
        return OmdbDataRepository(factory, mapper, mapperResponse)
    }

    @Provides
    @PerApplication
    internal fun provideOmdbCache(
        database: OmdbDatabase,
        entityMapper: OmdbEntityMapper,
        helper: PreferencesHelper
    ): OmdbCache {
        return OmdbCacheImpl(database, entityMapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideOmdbRemote(
        service: OmdbAPIService,
        factory: caio.com.remote.mapper.OmdbResponseEntityMapper
    ): OmdbRemote {
        return OmdbRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideOmdbService(): OmdbAPIService {
        return ServiceFactory.makeOmdbAPIService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideDesafioDatabase(application: Application): OmdbDatabase {
        return Room.databaseBuilder(application.applicationContext, OmdbDatabase::class.java, "omdb.db").build()
    }
}
