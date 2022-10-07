package com.trungdvu.netflix.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = NetflixColorPalette(
    brand = White,
    accent = DeepGreen,
    accentDark = DarkGreen,
    iconTint = Grey,
    appBackground = AlmostBlack,
    uiBackground = Neutral0,
    uiLightBackground = LightGrey2,
    uiLighterBackground = LightGrey3,
    uiBorder = VeryLightGrey,
    banner = Red,
    uiFloated = FunctionalGrey,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textSecondaryDark = TextSecondaryDark,
    textHelp = Neutral6,
    textInteractive = Neutral0,
    textLink = Ocean11,
    iconSecondary = Neutral7,
    iconInteractive = Green,
    iconInteractiveInactive = Grey,
    error = FunctionalRed,
    gradient6_1 = listOf(Shadow4, Ocean3, Shadow2, Ocean3, Shadow4),
    gradient6_2 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4),
    gradient3_1 = listOf(Shadow2, Ocean3, Shadow4),
    gradient3_2 = listOf(Rose2, Lavender3, Rose4),
    gradient2_1 = listOf(Shadow4, Shadow11),
    gradient2_2 = listOf(Ocean3, Shadow3),
    progressIndicatorBg = LightGrey,
    switchColor = Green,
    isDark = false
)

private val DarkColorPalette = NetflixColorPalette(
    brand = Shadow1,
    accent = Red,
    accentDark = DarkGreen,
    iconTint = Grey,
    appBackground = AlmostBlack,
    uiBackground = DarkGrey,
    uiLightBackground = LightGrey2,
    uiLighterBackground = LightGrey3,
    uiBorder = Neutral3,
    banner = Red,
    uiFloated = FunctionalDarkGrey,
    textPrimary = Text,
    textSecondary = Neutral0,
    textHelp = Neutral1,
    textInteractive = Neutral7,
    textLink = Ocean2,
    iconPrimary = Neutral3,
    iconSecondary = Neutral0,
    textSecondaryDark = TextSecondary,
    iconInteractive = White,
    iconInteractiveInactive = Neutral2,
    error = FunctionalRedDark,
    gradient6_1 = listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5),
    gradient6_2 = listOf(Rose11, Lavender7, Rose8, Lavender7, Rose11),
    gradient3_1 = listOf(Shadow9, Ocean7, Shadow5),
    gradient3_2 = listOf(Rose8, Lavender7, Rose11),
    gradient2_1 = listOf(Ocean3, Shadow3),
    gradient2_2 = listOf(Ocean7, Shadow7),
    progressIndicatorBg = LightGrey,
    switchColor = Green,
    isDark = true
)

@Composable
fun NetflixTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideNetflixColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object NetflixTheme {
    val colors: NetflixColorPalette
        @Composable
        get() = LocalAppColor.current
}

/**
 * App custom Color Palette
 */
@Stable
class NetflixColorPalette(
    gradient6_1: List<Color>,
    gradient6_2: List<Color>,
    gradient3_1: List<Color>,
    gradient3_2: List<Color>,
    gradient2_1: List<Color>,
    gradient2_2: List<Color>,
    brand: Color,
    accent: Color,
    accentDark: Color,
    iconTint: Color,
    appBackground: Color,
    uiBackground: Color,
    uiLightBackground: Color,
    uiLighterBackground: Color,
    uiBorder: Color,
    banner: Color,
    uiFloated: Color,
    interactivePrimary: List<Color> = gradient2_1,
    interactiveSecondary: List<Color> = gradient2_2,
    interactiveMask: List<Color> = gradient6_1,
    textPrimary: Color = brand,
    textSecondaryDark: Color,
    textSecondary: Color,
    textHelp: Color,
    textInteractive: Color,
    textLink: Color,
    iconPrimary: Color = brand,
    iconSecondary: Color,
    iconInteractive: Color,
    iconInteractiveInactive: Color,
    error: Color,
    notificationBadge: Color = error,
    progressIndicatorBg: Color,
    switchColor: Color,
    isDark: Boolean
) {
    var gradient6_1 by mutableStateOf(gradient6_1)
        private set
    var gradient6_2 by mutableStateOf(gradient6_2)
        private set
    var gradient3_1 by mutableStateOf(gradient3_1)
        private set
    var gradient3_2 by mutableStateOf(gradient3_2)
        private set
    var gradient2_1 by mutableStateOf(gradient2_1)
        private set
    var gradient2_2 by mutableStateOf(gradient2_2)
        private set
    var brand by mutableStateOf(brand)
        private set
    var accent by mutableStateOf(accent)
        private set
    var accentDark by mutableStateOf(accentDark)
        private set
    var iconTint by mutableStateOf(iconTint)
        private set
    var appBackground by mutableStateOf(appBackground)
        private set
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var uiLightBackground by mutableStateOf(uiLightBackground)
        private set
    var uiLighterBackground by mutableStateOf(uiLighterBackground)
        private set
    var uiBorder by mutableStateOf(uiBorder)
        private set
    var banner by mutableStateOf(banner)
        private set
    var uiFloated by mutableStateOf(uiFloated)
        private set
    var interactivePrimary by mutableStateOf(interactivePrimary)
        private set
    var interactiveSecondary by mutableStateOf(interactiveSecondary)
        private set
    var interactiveMask by mutableStateOf(interactiveMask)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textSecondaryDark by mutableStateOf(textSecondaryDark)
        private set
    var textHelp by mutableStateOf(textHelp)
        private set
    var textInteractive by mutableStateOf(textInteractive)
        private set
    var textLink by mutableStateOf(textLink)
        private set
    var iconPrimary by mutableStateOf(iconPrimary)
        private set
    var iconSecondary by mutableStateOf(iconSecondary)
        private set
    var iconInteractive by mutableStateOf(iconInteractive)
        private set
    var iconInteractiveInactive by mutableStateOf(iconInteractiveInactive)
        private set
    var error by mutableStateOf(error)
        private set
    var notificationBadge by mutableStateOf(notificationBadge)
        private set
    var progressIndicatorBg by mutableStateOf(progressIndicatorBg)
        private set
    var switchColor by mutableStateOf(switchColor)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: NetflixColorPalette) {
        gradient6_1 = other.gradient6_1
        gradient6_2 = other.gradient6_2
        gradient3_1 = other.gradient3_1
        gradient3_2 = other.gradient3_2
        gradient2_1 = other.gradient2_1
        gradient2_2 = other.gradient2_2
        brand = other.brand
        uiBackground = other.uiBackground
        uiBorder = other.uiBorder
        uiFloated = other.uiFloated
        interactivePrimary = other.interactivePrimary
        interactiveSecondary = other.interactiveSecondary
        interactiveMask = other.interactiveMask
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textHelp = other.textHelp
        textInteractive = other.textInteractive
        textLink = other.textLink
        iconPrimary = other.iconPrimary
        iconSecondary = other.iconSecondary
        iconInteractive = other.iconInteractive
        iconInteractiveInactive = other.iconInteractiveInactive
        error = other.error
        notificationBadge = other.notificationBadge
        switchColor = other.switchColor
        isDark = other.isDark
    }
}

@Composable
fun ProvideNetflixColors(
    colors: NetflixColorPalette,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalAppColor provides colorPalette, content = content)
}

private val LocalAppColor = staticCompositionLocalOf<NetflixColorPalette> {
    error("No AppColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [NetflixTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Transparent
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)