package com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graphs

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.FeatureLocationsDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.TopDestination
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.graph
import com.tonyGnk.thessBus.designSystem.showCaseMobile.navigation.route
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.FeaturesList
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsFeaturePager
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsPrePickWrapper
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.features.directions.DirectionsPreStartWrapper

fun NavGraphBuilder.featuresGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val navigateTo: (FeatureDestination) -> Unit = { destination ->
        navController.navigate(destination)
    }

    graph<TopDestination.FeaturesGraph>(
        startDestination = FeatureDestination.List
    ) {
        route<FeatureDestination.List> {
            FeaturesList(
                onBack = onBack,
                goTo = navigateTo
            )
        }

        featuresLocationsGraph(navController)
    }
}

fun NavGraphBuilder.featuresLocationsGraph(
    navController: NavController
) {
    val onBack: () -> Unit = { navController.navigateUp() }
    val goTo: (FeatureLocationsDestination) -> Unit = { phase ->
        navController.navigate(phase)
    }

    graph<FeatureDestination.LocationsGraph>(
        startDestination = FeatureLocationsDestination.Card
    ) {
        route<FeatureLocationsDestination.Info> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            DirectionsFeaturePager(
                model = viewModel(parentEntry),
                onFullScreen = { goTo(FeatureLocationsDestination.Card) },
                onBack = onBack
            )
        }

        route<FeatureLocationsDestination.Card> {
            DirectionsPreStartWrapper(
                goToPickTarget = { goTo(FeatureLocationsDestination.PickTarget) },
            )
        }

        route<FeatureLocationsDestination.PickTarget> {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(TopDestination.FeaturesGraph)
            }
            DirectionsPrePickWrapper(
                model = viewModel(parentEntry),
                onBack = onBack,
                goToCategories = {
                    //goTo(FeatureLocationsDestination.PickCategory)
                },
            )
        }
    }
}