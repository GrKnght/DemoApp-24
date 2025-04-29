package ru.itis.demoapp24.nav

import ru.itis.demo24.navigation.Nav
import ru.itis.demo24.navigation.NavMain
import javax.inject.Inject
import ru.itis.demoapp24.app.R

class NavMainImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
) : NavMain {

    private var parent: Nav? = null

    override fun initNavMain(parent: Nav) {
        this.parent = parent
    }

    override fun goToSearchPage() {
        navigatorDelegate.navigate(action = R.id.action_global_search_page)
    }

    override fun goToDetailsPage() {
        navigatorDelegate.navigate(action = R.id.action_global_song_details_page)
    }
}