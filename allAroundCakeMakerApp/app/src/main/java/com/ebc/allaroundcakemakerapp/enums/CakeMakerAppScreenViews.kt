package com.ebc.allaroundcakemakerapp.enums

import androidx.annotation.StringRes
import com.ebc.allaroundcakemakerapp.R

enum class CakeMakerAppScreenViews(@StringRes val title: Int) {
    OnBoarding(title = R.string.OnBoarding),
    Home(title = R.string.Home),
    Splash(title = R.string.Splash),
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary),
    Finish(title = R.string.finish_order)
}