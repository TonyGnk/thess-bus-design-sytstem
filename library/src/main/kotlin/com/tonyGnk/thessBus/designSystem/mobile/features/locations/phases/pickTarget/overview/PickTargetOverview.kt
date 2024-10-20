package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppIcon
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppPreview
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppTypo
import com.tonyGnk.thessBus.designSystem.mobile.components.actions.buttons.IconWithTextRow
import com.tonyGnk.thessBus.designSystem.mobile.components.containment.SurfaceWithShadows
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.card.LocationsProperties
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.LocationsPickTargetItems
import com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget.searchMode.PickTargetResult
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme


@Composable
internal fun PickTargetOverview(
    modifier: Modifier = Modifier,
    items: LocationsPickTargetItems = LocationsPickTargetItems.preview,
) {
    val labelStyle = AppTypo.titleMedium.copy(
        color = AppColor.onSurface.copy(alpha = 0.9f)
    )
    val arrangement = remember {
        LocationsProperties.ARRANGEMENT
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(arrangement.div(0.5).dp)
    ) {
        SurfaceWithShadows(
            shadowElevation = 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(items.horizontalPadding),
            shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
            color = AppColor.surfaceLowest,
            onClick = {}
        ) {
            IconWithTextRow(
                modifier = Modifier.padding(LocationsProperties.IN_PADDING.dp),
                contentColor = AppColor.primary,
                iconRes = AppIcon.mapMarker,
                style = AppTypo.titleMedium.copy(color = AppColor.primary),
                weight = FontWeight.W500,
                arrangement = Arrangement.spacedBy(18.dp),
                text = "Pick on map",
            )
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(arrangement.dp),
            modifier = Modifier.padding(items.horizontalPadding)
        ) {
            Text(text = "Collections", style = labelStyle)
            SurfaceWithShadows(
                shadowElevation = 0,
                color = AppColor.surfaceLowest,
                shape = RoundedCornerShape(LocationsProperties.IN_CORNERS.dp),
            ) {
                Column {
                    PickTargetOverviewCollection(
                        onFavoriteClick = items.onSavedLocationClick,
                        onAddCollectionClick = items.onAddCollectionClick,
                        onFavoriteNotConfiguredClick = items.onFavoriteNotConfiguredClick,
                        selectedFavoriteItemId = items.selectedFavoriteItemId,
                        updateSelectedFavoriteItemId = items.updateSelectedFavoriteItemId,
                    )
//                    SurfaceWithShadows(
//                        modifier = Modifier.fillMaxWidth(),
//                        shadowElevation = 0,
//                        color = AppColor.transparent,
//                        onClick = {
//                            Log.d("PickTargetOverview", items.collectionsBottomSheetType.toString())
//                            items.setBottomSheetType(CollectionBottomSheetType.Overview)
//                        },
//                    ) {
//                        Text("All")
//                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(arrangement.dp),
            modifier = Modifier.padding(items.horizontalPadding)
        ) {
            Text(text = "Recent", style = labelStyle)
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                items.history.forEach { item ->
                    PickTargetResult(
                        result = item, onClick = {
                            items.onResultClick(item)
                        }
                    )
                }
            }
        }
    }
}


@AppPreview.Light
@Composable
private fun Preview() = ThessBusTheme {
    PickTargetOverview()
}
