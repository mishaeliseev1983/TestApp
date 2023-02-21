package com.melyseev.testapp.di

import com.melyseev.testapp.common.Communications
import com.melyseev.testapp.firstscreen.communications.FirstScreenCommunications
import com.melyseev.testapp.secondscreen.communications.SecondScreenCommunications
import dagger.Binds
import dagger.Module

@Module
interface CommunicationsModule {

    @ApplicationScope
    @Binds
    fun provideFirstScreenCommunications(param: FirstScreenCommunications.Base): FirstScreenCommunications

    @ApplicationScope
    @Binds
    fun provideSecondScreenCommunications(param: SecondScreenCommunications.Base): SecondScreenCommunications


    @Binds
    fun provideIntCommunication(param: Communications.IntCommunication.Base): Communications.IntCommunication

    @Binds
    fun provideStateRaitingsCommunication(param: Communications.StateRaitingsCommunication.Base): Communications.StateRaitingsCommunication
}