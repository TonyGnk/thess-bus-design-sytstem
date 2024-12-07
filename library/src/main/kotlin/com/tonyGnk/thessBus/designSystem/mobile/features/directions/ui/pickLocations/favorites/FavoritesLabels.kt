package com.tonyGnk.thessBus.designSystem.mobile.features.directions.ui.pickLocations.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.TextFade

@Composable
fun FavoritesLargeLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    TextFade(
        text = text,
        textAlign = TextAlign.Center,
        style = AppTypo.bodySmall,
        modifier = modifier,
        backgroundColor = AppColor.surfaceLowest
    )
}


@Composable
fun FavoritesSmallLabel(
    modifier: Modifier = Modifier,
    text: String
) {
    TextFade(
        text = text,
        textAlign = TextAlign.Center,
        style = AppTypo.bodySmall.copy(
            color = AppColor.primary,
            fontSize = AppTypo.bodySmall.fontSize.div(1.25f)
        ),
        modifier = modifier,
        backgroundColor = AppColor.surfaceLowest
    )
}
