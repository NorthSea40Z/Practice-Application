package org.northsea40.practiceapplication

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.northsea40.practiceapplication.Model.AppDatabase
import org.northsea40.practiceapplication.Model.UserDao
import org.northsea40.practiceapplication.ViewModel.MainViewModel

// Step K01
class MainApplication: Application() {
    val appModule = module {
        single {
            Room.databaseBuilder(
                this@MainApplication,
                AppDatabase::class.java,
                "APP_DATABASE"
            ).build()
        }
        single<UserDao> {
             get<AppDatabase>().userDao()
        }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule)
        }
    }
}