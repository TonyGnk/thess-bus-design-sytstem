package com.tonyGnk.thessBus.designSystem.mobile.features.directions.data

import androidx.annotation.DrawableRes
import com.tonyGnk.thessBus.designSystem.mobile.R
import org.maplibre.android.geometry.LatLng


sealed interface DirectionsFeatureItemType {
    data class MultipleItems(
        val items: List<Point>
    ) : DirectionsFeatureItemType

    data class Point(
        val id: String,
        val lat: Double,
        val lon: Double,
        val title: String,
        val subTitle: String,
        @DrawableRes val iconRes: Int,
    ) : DirectionsFeatureItemType

    data object JustMap : DirectionsFeatureItemType
}

enum class LocationsPoiCategory(
    val osmLabel: String,
    @DrawableRes val iconRes: Int
) {
    PHARMACY(osmLabel = "pharmacy", iconRes = R.drawable.pharmacy),
    CAFFE(osmLabel = "cafe", iconRes = R.drawable.mug_hot_alt),
    SUPER_MARKET(osmLabel = "supermarket", iconRes = R.drawable.shopping_cart),
    TICKET(osmLabel = "ticket", iconRes = R.drawable.ticket_alt),
    UNIVERSITY(osmLabel = "university", iconRes = R.drawable.graduation_cap),
    SCHOOL(osmLabel = "school", iconRes = R.drawable.school),
    COLLEGE(osmLabel = "college", iconRes = R.drawable.graduation_cap),
    HOTEL(osmLabel = "hotel", iconRes = R.drawable.bed),
    DOCTOR(osmLabel = "doctor", iconRes = R.drawable.stethoscope),
    STUDIO(osmLabel = "studio", iconRes = R.drawable.radio_tower),
    BICYCLE_PARKING(osmLabel = "bicycle_parking", iconRes = R.drawable.biking),
    FAST_FOOD(osmLabel = "fast_food", iconRes = R.drawable.burger_fries),
    CHURCH(osmLabel = "church", iconRes = R.drawable.church),
    ATM(osmLabel = "atm", iconRes = R.drawable.insert_credit_card),
    HEAR_DRESSER(osmLabel = "hairdresser", iconRes = R.drawable.shopping_cart),
    BIKE_RENTAL(osmLabel = "bicycle_rental", iconRes = R.drawable.biking),
    FUEL(osmLabel = "fuel", iconRes = R.drawable.gas_pump_alt),
    BUS_STOP(osmLabel = "bus_stop", iconRes = R.drawable.bus_alt),
    CLOTHES(osmLabel = "clothes", iconRes = R.drawable.shirt_long_sleeve),

    //
    THEATER(osmLabel = "theater", iconRes = R.drawable.theater_masks),
    BANK(osmLabel = "bank", iconRes = R.drawable.bank),
    OTHER(osmLabel = "other", iconRes = R.drawable.question);
}


val PickTargetFakeResults = listOf(
    DirectionsFeatureItemType.Point(
        id = "1",
        lat = 40.640063,
        lon = 22.943383,
        title = "Βασιλειάδης Χρ. Βασίλειος",
        subTitle = "Νικολάου Παρασκευά 17",
        iconRes = LocationsPoiCategory.PHARMACY.iconRes,
    ),
    DirectionsFeatureItemType.Point(
        id = "2",
        lat = 40.640033,
        lon = 22.943373,
        title = "Δεντρόσπιτο",
        subTitle = "Δεντρόσπιτο",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
    DirectionsFeatureItemType.Point(
        id = "4",
        lat = 40.640033,
        lon = 22.943373,
        title = "Κυλικείο Πρυτανείας",
        subTitle = "Κυλικείο Πρυτανείας",
        iconRes = LocationsPoiCategory.CAFFE.iconRes,
    ),
)

fun LatLng.toPoint() = DirectionsFeatureItemType.Point(
    id = latitude.toString() + longitude.toString(),
    lat = latitude,
    lon = longitude,
    title = "Σημείο στον χάρτη",
    subTitle = "",
    iconRes = LocationsPoiCategory.OTHER.iconRes,
)
