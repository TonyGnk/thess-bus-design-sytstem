package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.layout

import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.navigation.NavigationPage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.ListItem
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Surface
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.NavigationBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.BasicTopBar
import com.tonyGnk.thessBus.designSystem.mobile.components.navigation.topBar.TopBarBackIcon
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.showCaseMobile.R
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing.LandingDestination
import kotlinx.serialization.Serializable

@Serializable
data object LayoutPageRoute

@Composable
fun LayoutPage(
    onBack: () -> Unit = {},
    onLayoutDestinations: (LayoutDestination) -> Unit = {}
) {
    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(DefaultScaffoldValues.NORMAL_MARGIN.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                BasicTopBar(
                    modifier = Modifier.padding(it),
                    labelRes = R.string.landing_destinations_layout,
                    backIcon = TopBarBackIcon(
                        onBack = onBack
                    )
                )
            }
//            items(
//                items = LayoutDestination.entries, key = { it.labelRes }
//            ) { destination ->
//                LayoutListItem(
//                    layoutDestination = destination,
//                    onLayoutDestinations = onLayoutDestinations
//                )
//            }
            item {
                ListContainer(onLayoutDestinations = onLayoutDestinations)
            }
        }
    }
}

@Composable
fun ListContainer(
    modifier: Modifier = Modifier,
    onLayoutDestinations: (LayoutDestination) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = AppColor.surfaceContainerLowest,
        shadowElevation = 1.dp,
        shape = AppShape.round30
    ) {
        Column {
            LayoutDestination.entries.forEachIndexed { index, destination ->
                Column {
                    LayoutListItem(
                        layoutDestination = destination,
                        onLayoutDestinations = onLayoutDestinations
                    )
                    //If is not last then add a divider and not the first
                    if (index != LandingDestination.entries.size - 1 && LandingDestination.entries.size ==1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun LayoutListItem(
    layoutDestination: LayoutDestination, onLayoutDestinations: (LayoutDestination) -> Unit
) {
    ListItem(
        padding = PaddingValues(DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp),
        onClick = { onLayoutDestinations(layoutDestination) }
    ) {
        Text(text = stringResource(layoutDestination.labelRes))
    }
}

@AppPreview.Brightness
@Composable
fun LandingPagePreview() = ClpTheme {
    LayoutPage()
}