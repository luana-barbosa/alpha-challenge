package com.luanabarbosa.starswars.utils.extensions

object ImageHelper {
    private const val IMAGE_BASE_PEOPLE = "https://starwars-visualguide.com/assets/img/characters/"
    private const val IMAGE_BASE_PLANETS = "https://starwars-visualguide.com/assets/img/planets/"
    private const val IMAGE_BASE_FILMS = "https://starwars-visualguide.com/assets/img/films/"
    const val IMAGE_HOME_PEOPLE= "https://starwars-visualguide.com/assets/img/categories/character.jpg"
    const val IMAGE_HOME_PLANETS = "https://starwars-visualguide.com/assets/img/categories/planets.jpg"
    const val IMAGE_HOME_FILMS = "https://starwars-visualguide.com/assets/img/categories/films.jpg"

    fun getPeopleImage(path: Int): String = "$IMAGE_BASE_PEOPLE$path.jpg"
    fun getPlanetsImage(path: Int): String = "$IMAGE_BASE_PLANETS$path.jpg"
    fun getFilmsImage(path: Int): String = "$IMAGE_BASE_FILMS$path.jpg"
}
