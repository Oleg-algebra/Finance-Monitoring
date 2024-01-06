package com.example.financemonitoring.domain

import android.content.Intent

object IntentPreprocessing {

    fun parseIntent(intent: Intent): Filter{
        var filter = Filter()
        if (intent.hasExtra(EXTRA_CATEGORIES)){
//            Log.d(TAG, "parseIntent extra categories: ${intent.getStringExtra(EXTRA_CATEGORIES)}")
            filter = filter.copy(
                categories = intent.getStringExtra(EXTRA_CATEGORIES)
                    ?.split(",".toRegex()) ?: listOf()
            )
        }
        if(intent.hasExtra(EXTRA_FROM_DATE)){
//            Log.d(TAG, "parseIntent EXTRA_FROM_DATE: ${intent.getStringExtra(EXTRA_FROM_DATE)}")
            filter = filter.copy(fromDate = intent.getStringExtra(
                EXTRA_FROM_DATE)?.let { Date.stringToDateReverse(it) })
        }
        if(intent.hasExtra(EXTRA_TO_DATE)){
//            Log.d(TAG, "parseIntent EXTRA_TO_DATE: ${intent.getStringExtra(EXTRA_TO_DATE)}")
            filter = filter.copy(toDate = intent.getStringExtra(EXTRA_TO_DATE)
                ?.let { Date.stringToDateReverse(it) })
        }
//        Log.d(TAG, "parseIntent filter: $filter")

        return filter

    }
}