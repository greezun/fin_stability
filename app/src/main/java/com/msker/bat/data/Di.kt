package com.msker.bat.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Di {
    @Singleton
    @Provides
    fun provideBase(@ApplicationContext context: Context): FinBase {
        return  Room.databaseBuilder(
            context,
            FinBase::class.java,
            "database"
        ).build()
    }
}