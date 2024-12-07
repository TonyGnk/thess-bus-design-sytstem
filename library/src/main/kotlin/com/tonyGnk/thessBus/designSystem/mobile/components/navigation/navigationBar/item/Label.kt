package com.tonyGnk.thessBus.designSystem.mobile.components.navigation.navigationBar.item

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tonyGnk.thessBus.designSystem.mobile.appStyles.AppColor
import com.tonyGnk.thessBus.designSystem.mobile.components.core.text.Text
import com.tonyGnk.thessBus.designSystem.mobile.theme.ThessBusTheme
import com.tonyGnk.thessBus.designSystem.mobile.utils.sp

private const val NAVIGATION_ITEM_FIXED_LABEL_SIZE = 14

@Composable
internal fun NavigationBarItemLabel(
    modifier: Modifier = Modifier,
    label: String = "Label",
    color: Color = AppColor.onSurface,
    selected: Boolean,
) {
    val textStyle = if (selected) {
        LocalTextStyle.current.copy(
            fontSize = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.sp,
            lineHeight = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.sp,
            fontWeight = FontWeight.W800
        )
    } else {
        LocalTextStyle.current.copy(
            fontSize = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.sp,
            lineHeight = NAVIGATION_ITEM_FIXED_LABEL_SIZE.dp.sp,
            fontWeight = FontWeight.W400
        )
    }

    val animatedTextStyle by animateTextStyleAsState(
        targetValue = textStyle,
        spring(stiffness = Spring.StiffnessLow)
    )

    Text(
        text = label,
        style = animatedTextStyle.copy(
            color = color
        ),
        maxLines = 1,
        modifier = modifier
    )
}


@Composable
private fun animateTextStyleAsState(
    targetValue: TextStyle,
    animationSpec: AnimationSpec<Float> = spring(),
    finishedListener: ((TextStyle) -> Unit)? = null
): State<TextStyle> {

    val animation = remember { Animatable(0f) }
    var previousTextStyle by remember { mutableStateOf(targetValue) }
    var nextTextStyle by remember { mutableStateOf(targetValue) }

    val textStyleState = remember(animation.value) {
        derivedStateOf {
            lerp(previousTextStyle, nextTextStyle, animation.value)
        }
    }

    LaunchedEffect(targetValue, animationSpec) {
        previousTextStyle = textStyleState.value
        nextTextStyle = targetValue
        animation.snapTo(0f)
        animation.animateTo(1f, animationSpec)
        finishedListener?.invoke(textStyleState.value)
    }

    return textStyleState
}


@Composable
@Preview
private fun Preview() = ThessBusTheme {
    NavigationBarItemLabel(selected = false)
}
