package com.example.jetsnack

import ai.securiti.cmpsdkcore.main.CmpSDKLoggerLevel
import ai.securiti.cmpsdkcore.main.CmpSDKOptions
import ai.securiti.cmpsdkcore.main.SecuritiMobileCmp
import com.example.jetsnack.ui.MainActivity

object SecuritiBanner {

    fun initializeBanner(activity: MainActivity) {
        println("SECURITI_SDK_VERSION: ${SecuritiMobileCmp.VERSION}")
        println("Initializing Securiti Banner")

        val options = CmpSDKOptions(
            appURL = "https://qa.securiti.xyz",
            cdnURL = "https://cdn-qa.securiti.xyz/consent",
            tenantID = "b9153366-8d40-43b0-8009-e7c5f7342b6c",
            appID = "e6c35be9-0159-4f5e-ad95-d7476ce39943",
            testingMode = false,
            loggerLevel = CmpSDKLoggerLevel.DEBUG,
            backgroundTaskIntervalTime = 1
        )

        SecuritiMobileCmp.initialize(activity.application, options)


        SecuritiMobileCmp.isReady { isReady ->
            println("Securiti Banner initialized")
            SecuritiMobileCmp.presentConsentBanner(activity)
        }
    }
}