package com.luanabarbosa.starswars.utils.navigation

private const val PATH_SEPARATOR = "/"

fun buildComposableRouteMap(
    basePath: String,
    argumentNames: List<String> = listOf()
): String {
    val formattedArgumentNames = argumentNames.map { "{${it}}" }
    val pieces: List<String> = listOf(basePath) + formattedArgumentNames
    return pieces.joinToString(separator = PATH_SEPARATOR)
}

fun buildPeopleDetailsRoute(id: String): String {
    return listOf(Screen.PEOPLE_DETAILS.name, id).joinToString(separator = PATH_SEPARATOR)
}

fun buildPlanetDetailsRoute(id: String): String {
    return listOf(Screen.PLANET_DETAILS.name, id).joinToString(separator = PATH_SEPARATOR)
}

fun buildFilmsDetailsRoute(id: String): String {
    return listOf(Screen.FILMS_DETAILS.name, id).joinToString(separator = PATH_SEPARATOR)
}
