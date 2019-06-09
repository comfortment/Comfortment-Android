package com.comfortment.presentation.di.module

import com.comfortment.data.repository.NanumRepositoryImp
import com.comfortment.domain.usecases.nanum.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NanumModule {

    @Singleton
    @Provides
    fun provideGetJoinNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): GetJoinNanumUseCase =
        GetJoinNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun providePostNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): PostNanumUseCase =
        PostNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideEditNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): EditNanumUseCase =
        EditNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideUploadImageUseCase(nanumRepositoryImp: NanumRepositoryImp): UploadImageUseCase =
        UploadImageUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideGetNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): GetNanumUseCase =
        GetNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideGetRaisedNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): GetRaisedNanumUseCase =
        GetRaisedNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideGetStarNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): GetStarNanumUseCase =
        GetStarNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideJoinNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): JoinNanumUseCase =
        JoinNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideSetRaisedStateNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): SetRaisedStateNanumUseCase =
        SetRaisedStateNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideSetStarNanumUseCase(nanumRepositoryImp: NanumRepositoryImp): SetStarNanumUseCase =
        SetStarNanumUseCase(nanumRepositoryImp)

    @Singleton
    @Provides
    fun provideShowNanumDetailUseCase(nanumRepositoryImp: NanumRepositoryImp): GetNanumDetailUseCase =
        GetNanumDetailUseCase(nanumRepositoryImp)

}