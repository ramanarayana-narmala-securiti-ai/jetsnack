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
//            appURL = "https://qa.securiti.xyz",
//            cdnURL = "https://cdn-qa.securiti.xyz/consent",
//            tenantID = "b9153366-8d40-43b0-8009-e7c5f7342b6c",
//            appID = "e6c35be9-0159-4f5e-ad95-d7476ce39943",
//            testingMode = false,
//            loggerLevel = CmpSDKLoggerLevel.DEBUG,
            appURL = "https://dev-intg-2.securiti.xyz",
            cdnURL = "https://cdn-dev-intg-2.securiti.xyz/consent",
            tenantID = "5e2dcf33-6eae-427a-afa4-c7667ee32a11",
            appID = "fb6018c4-49a5-4ba6-b84a-7e193d09c47e",
            testingMode = false,
            loggerLevel = CmpSDKLoggerLevel.WARNING,
            //languageCode= "en",
            consentsCheckInterval = 2
        )

        SecuritiMobileCmp.initialize(activity.application, options)


        SecuritiMobileCmp.isReady { isReady ->
            println("Securiti Banner initialized")
            SecuritiMobileCmp.presentConsentBanner(activity)
            println("XXXXXX")
            println(SecuritiMobileCmp.getConsent(1));
        }
    }
}