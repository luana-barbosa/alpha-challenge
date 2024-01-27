package com.luanabarbosa.starswars.planets.di

import com.luanabarbosa.starswars.data.remote.NetworkInitialization
import com.luanabarbosa.starswars.planets.data.mapper.PlanetMapper
import com.luanabarbosa.starswars.planets.data.mapper.PlanetMapperImpl
import com.luanabarbosa.starswars.planets.data.repository.PlanetRepositoryImpl
import com.luanabarbosa.starswars.planets.data.service.PlanetApi
import com.luanabarbosa.starswars.planets.domain.repository.PlanetRepository
import com.luanabarbosa.starswars.planets.domain.usecase.GetPlanetCharacters
import com.luanabarbosa.starswars.planets.domain.usecase.PlanetUseCase
import com.luanabarbosa.starswars.planets.ui.PlanetViewModel
import org.koin.dsl.module

val planetModule = module {

    single { NetworkInitialization().createService(PlanetApi::class.java) }
    single<PlanetRepository> { PlanetRepositoryImpl(get(), get()) }
    factory<PlanetMapper> { PlanetMapperImpl() }
    single<PlanetUseCase> { GetPlanetCharacters(get()) }
    single { PlanetViewModel(get()) }

}
