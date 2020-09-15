package jp.babyplus.android.api

import com.sample.interviewproject.api.service.SampleService
import javax.inject.Inject
import javax.inject.Named

class SampleClient @Inject internal constructor(
        val babyService: SampleService,
        @param:Named("accept null field") val acceptNullFieldBabyService: SampleService)
