package com.luanabarbosa.starswars.people.di

import com.luanabarbosa.starswars.data.remote.NetworkInitialization
import com.luanabarbosa.starswars.people.data.mapper.PeopleMapper
import com.luanabarbosa.starswars.people.data.mapper.PeopleMapperImpl
import com.luanabarbosa.starswars.people.data.repository.PeopleRepositoryImpl
import com.luanabarbosa.starswars.people.data.service.PeopleApi
import com.luanabarbosa.starswars.people.domain.repository.PeopleRepository
import com.luanabarbosa.starswars.people.domain.usecase.GetPeopleCharacters
import com.luanabarbosa.starswars.people.domain.usecase.PeopleUseCase
import com.luanabarbosa.starswars.people.ui.PeopleViewModel
import org.koin.dsl.module

val peopleModule = module {

    single { NetworkInitialization().createService(PeopleApi::class.java) }
    single<PeopleRepository> { PeopleRepositoryImpl(get(), get()) }
    factory<PeopleMapper> { PeopleMapperImpl() }
    single<PeopleUseCase> { GetPeopleCharacters(get()) }
    single { PeopleViewModel(get()) }

}
