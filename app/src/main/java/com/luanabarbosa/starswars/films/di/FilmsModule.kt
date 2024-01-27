package com.luanabarbosa.starswars.films.di

import com.luanabarbosa.starswars.data.remote.NetworkInitialization
import com.luanabarbosa.starswars.films.data.mapper.FilmsMapper
import com.luanabarbosa.starswars.films.data.mapper.FilmsMapperImpl
import com.luanabarbosa.starswars.films.data.repository.FilmsRepositoryImpl
import com.luanabarbosa.starswars.films.data.service.FilmsApi
import com.luanabarbosa.starswars.films.domain.repository.FilmsRepository
import com.luanabarbosa.starswars.films.domain.usecase.FilmsUseCase
import com.luanabarbosa.starswars.films.domain.usecase.GetFilmsCharacters
import com.luanabarbosa.starswars.films.ui.FilmsViewModel
import org.koin.dsl.module

val filmsModule = module {

    single { NetworkInitialization().createService(FilmsApi::class.java) }
    single<FilmsRepository> { FilmsRepositoryImpl(get(), get()) }
    factory<FilmsMapper> { FilmsMapperImpl() }
    single<FilmsUseCase> { GetFilmsCharacters(get()) }
    single { FilmsViewModel(get()) }

}
