package com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.searchMode

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppShape
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.DefaultScaffoldValues
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.icons.Icon
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardResult
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.selectDestination.data.NavCardResultFakeData
import com.tonyGnk.thessBus.designSystem.mobile.layouts.navCard.start.NavCardProperties
import com.tonyGnk.thessBus.designSystem.mobile.theme.ClpTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.findScreenSize
import com.tonyGnk.thessBus.designSystem.mobile.utils.isLanguageGreek

@Composable
fun NavCardSearchMode(
    detailedView: Boolean,
    onResultClick: (Long, Boolean) -> Unit,
    results: List<NavCardResult>
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(items = results, key = { it.id }) { result ->
                ResultLayout(
                    result = result,
                    onClick = {
                        onResultClick(result.id, result.isSinglePoint)
                    }
                )
            }
        }
    }
}

@Composable
private fun ResultLayout(
    result: NavCardResult,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = result.title
    val subTitle = result.subTitle

    val titleStyle = AppTypo.titleMedium
    val subTitleStyle = AppTypo.bodyMedium

    val totalHeight =
        title.findScreenSize(titleStyle).height + subTitle.findScreenSize(subTitleStyle).height
    val shape = RoundedCornerShape(NavCardProperties.SMALL_CORNERS.dp)

    val paddingOfTheBackButtonInSearch = DefaultScaffoldValues.MINIMUM_BEZEL_PADDING.dp + 12.dp
    val paddingForTheIcon = 7.dp
    val padding = paddingOfTheBackButtonInSearch - paddingForTheIcon

    Row(
        horizontalArrangement = Arrangement.spacedBy(padding),//23
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(AppColor.surfaceContainer)
            .selectable(
                selected = false,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(
                horizontal = padding,
                vertical = padding
            )
    ) {
        SurfaceWithShadows(
            color = AppColor.surfaceContainerLowest,
            shadowElevation = 1,
            shape = AppShape.round10,
            modifier = Modifier.size(totalHeight)
        ) {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier.padding(7.dp)
            ) {
                Icon(
                    iconRes = result.category.iconRes,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Column {
            Text(
                text = title,
                style = titleStyle,
                color = AppColor.onSurface
            )
            Text(
                text = subTitle,
                style = subTitleStyle,
                color = AppColor.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
@AppPreview.Brightness
private fun Preview() = ClpTheme {
    val results = NavCardResultFakeData

    NavCardSearchMode(
        results = results, detailedView = false, onResultClick = { _, _ -> }
    )
}