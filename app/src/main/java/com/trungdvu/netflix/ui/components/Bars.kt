package com.trungdvu.netflix.ui.components

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.trungdvu.netflix.ui.theme.AlphaNearOpaque
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.R

private val TopBarHeight = 80.dp
private val MovieDetailTopBarHeight = 70.dp

@Composable
fun getTopBarWidthState(isScrolledDown: Boolean): State<Dp> {
    return animateDpAsState(
        targetValue = if (isScrolledDown) 0.dp else 24.dp,
    )
}

@Composable
fun getTopBarColorState(isScrolledDown: Boolean): State<Color> {
    return animateColorAsState(
        targetValue = if (isScrolledDown) Color.Black.copy(alpha = 0.5f) else Color.Transparent,
    )
}

@Composable
fun TopBar(
    isScrolledDown: Boolean,
    modifier: Modifier = Modifier,
) {
    NetflixSurface(
        modifier = modifier
            .padding(top = getTopBarWidthState(isScrolledDown = isScrolledDown).value)
            .height(TopBarHeight),
        color = getTopBarColorState(isScrolledDown = isScrolledDown).value
    ) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = NetflixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            modifier = modifier
                .height(TopBarHeight)
        ) {
            AnimatedVisibility(visible = isScrolledDown.not()) {
                AppBar()
            }
        }
    }
}

@Composable
fun AppBar(
    showBack: Boolean = false,
    upPress: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(start = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(6f)
                .align(Alignment.CenterVertically)
        ) {
            if (showBack) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = NetflixTheme.colors.iconInteractive,
                    modifier = Modifier.clickable { upPress() }
                )
            } else {
                Image(
                    painterResource(id = R.drawable.netflix_logo),
                    contentDescription = "Netflix Logo",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { }
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { }
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = NetflixTheme.colors.iconInteractive
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { }
        ) {
            Image(
                painterResource(id = R.drawable.netflix_profile),
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}

@Preview("TopBar", showBackground = true)
@Composable
fun TopBarPreview() {
    NetflixTheme {
        TopBar(
            isScrolledDown = false
        )
    }
}

enum class DashboardSections(
    @SuppressLint("SupportAnnotationUsage") @StringRes val title: String,
    val icon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Default.Home, "dashboard/home"),
    PLAY_SOMETHING("Play Something", Icons.Default.PlayArrow, "dashboard/play"),
    COMING_SOON("Coming Soon", Icons.Outlined.PlayArrow, "dashboard/coming_soon"),
    DOWNLOADS("Downloads", Icons.Default.KeyboardArrowDown, "dashboard/downloads")
}

private val BottomNavigationItemPadding = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)

@Composable
fun NetflixBottomBar(
    navController: NavController,
    tabs: Array<DashboardSections>,
    color: Color = NetflixTheme.colors.uiBackground,
    contentColor: Color = NetflixTheme.colors.iconInteractive
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dashboardSections = remember { DashboardSections.values() }
    val routes = remember { dashboardSections.map { it.route } }

    if (currentRoute in routes) {
        val currentSection = dashboardSections.first { it.route == currentRoute }

        NetflixSurface(
            color = color,
            contentColor = contentColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 65.dp)
            ) {
                tabs.forEach { section ->
                    val selected = section == currentSection
                    val tint by getIconTint(selected = selected)

                    NetflixBottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = section.icon,
                                tint = tint,
                                contentDescription = null
                            )
                        },
                        text = {
                            Text(
                                text = "Trung",
                                color = tint,
                                fontSize = 8.sp,
                                style = MaterialTheme.typography.button,
                                maxLines = 1
                            )
                        },
                        selected = selected,
                        onSelected = {
                        },
                        modifier = BottomNavigationItemPadding.weight(1f)
                    )
                }
            }

        }
    }
}

@Composable
fun getIconTint(selected: Boolean): State<Color> {
    return animateColorAsState(
        if (selected) {
            NetflixTheme.colors.iconInteractive
        } else {
            NetflixTheme.colors.iconInteractiveInactive
        }
    )
}

@Composable
fun NetflixBottomNavigationItem(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    selected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = selected, onClick = onSelected)
    ) {
        Box(
            modifier = Modifier.layoutId("icon"),
            content = icon
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier.layoutId("text"),
            content = text
        )
    }
}