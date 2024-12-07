package com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.Scaffold
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.HorizontalDivider
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.modifiers.getExtendedWindowInsets
import com.tonyGnk.thessBus.designSystem.mobile.utils.sp
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.LandingUnknown
import com.tonyGnk.thessBus.designSystem.showCaseMobile.screens.shared.SharedListContainer


private const val MARGIN = 20

@Composable
fun LandingPage(
    navigateToDestination: (LandingDestination) -> Unit = {},
) {

    Scaffold {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(MARGIN.dp),
            contentPadding = getExtendedWindowInsets(
                topPaddingIfNoStatusBar = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            item { Header() }
            item {
                SharedListContainer {
                    LandingDestination.entries.forEachIndexed { index, landingDestination ->
                        LandingUnknown(
                            text = stringResource(id = landingDestination.labelRes),
                            iconRes = landingDestination.iconRes,
                            onClick = { navigateToDestination(landingDestination) }
                        )
                        if (index != LandingDestination.entries.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MARGIN.dp),
        modifier = Modifier
            .padding(horizontal = DefaultScaffoldValues.NORMAL_BEZEL_PADDING.dp)
            .padding(top = MARGIN.dp)
    ) {
        LandingPageAppBar()
        LandingPageAppBarDescription()
        UpdateButton()
    }
}


@AppPreview.Scale
@Composable
fun LandingPagePreview() = ThessBusTheme {
    LandingPage()
}
