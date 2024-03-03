package com.luanabarbosa.starswars.utils.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = NavItem.Home.route) {
            HomeScreen(navController = navController)
        }

        //PeopleRoute
        composable(route = NavItem.PeopleList.route) {
            PeopleListScreen(navController = navController)
        }

        composable(
            route = NavItem.PeopleDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID.name) { type = NavType.IntType }
            )
        ) {
            val peopleId = remember { it.arguments?.getInt(NavigationArgs.ITEM_ID.name) }
            PeopleDetailsScreen(navController = navController, id = peopleId)
        }

        //PlanetRoute
        composable(route = NavItem.PlanetsList.route) {
            PlanetListScreen(navController = navController)
        }

        composable(
            route = NavItem.PlanetDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID.name) { type = NavType.IntType }
            )
        ) {
            val planetId = remember { it.arguments?.getInt(NavigationArgs.ITEM_ID.name) }
            PlanetDetailsScreen(navController = navController, id = planetId)
        }

        //FilmsRoute
        composable(route = NavItem.FilmsList.route) {
            FilmsListScreen(navController = navController)
        }

        composable(
            route = NavItem.FilmsDetails.route,
            arguments = listOf(
                navArgument(NavigationArgs.ITEM_ID.name) { type = NavType.IntType }
            )
        ) {
            val filmsId = remember { it.arguments?.getInt(NavigationArgs.ITEM_ID.name) }
            FilmsDetailsScreen(navController = navController, id = filmsId)
        }
    }
}
