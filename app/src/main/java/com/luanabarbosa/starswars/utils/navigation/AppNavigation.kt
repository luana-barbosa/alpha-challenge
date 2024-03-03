package com.luanabarbosa.starswars.utils.navigation

enum class Screen {
    HOME,
    PEOPLE_LIST,
    PEOPLE_DETAILS,
    PLANETS_LIST,
    PLANET_DETAILS,
    FILMS_LIST,
    FILMS_DETAILS
}

sealed class NavItem(val route: String) {

    object Home : NavItem(route = Screen.HOME.name)
    object PeopleList : NavItem(route = Screen.PEOPLE_LIST.name)
    object PlanetsList : NavItem(route = Screen.PLANETS_LIST.name)
    object FilmsList : NavItem(route = Screen.FILMS_LIST.name)


    object PeopleDetails : NavItem(
        route = buildComposableRouteMap(
            basePath = Screen.PEOPLE_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )

    object PlanetDetails : NavItem(
        route = buildComposableRouteMap(
            basePath = Screen.PLANET_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )

    object FilmsDetails: NavItem(
        route = buildComposableRouteMap(
            basePath = Screen.FILMS_DETAILS.name,
            argumentNames = listOf(
                NavigationArgs.ITEM_ID.name
            )
        )
    )

}
