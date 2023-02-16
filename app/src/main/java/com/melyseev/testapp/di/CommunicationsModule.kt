package com.melyseev.testapp.di

import com.melyseev.testapp.common.Communications
import com.melyseev.testapp.firstscreen.communications.FirstScreenCommunications
import com.melyseev.testapp.secondscreen.communications.SecondScreenCommunications
import dagger.Binds
import dagger.Module

@Module
interface CommunicationsModule {

    @Binds
    fun provideFirstScreenCommunications(param: FirstScreenCommunications.Base): FirstScreenCommunications

    @Binds
    fun provideSecondScreenCommunications(param: SecondScreenCommunications.Base): SecondScreenCommunications


    @Binds
    fun provideIntCommunication(param: Communications.IntCommunication.Base): Communications.IntCommunication

    @Binds
    fun provideBooleanCommunication(param: Communications.BooleanCommunication.Base): Communications.BooleanCommunication

    @Binds
    fun provideDataRaitingsCommunication(param: Communications.DataRaitingsCommunication.Base): Communications.DataRaitingsCommunication

}