package com.trungdvu.netflix.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import com.trungdvu.netflix.ui.components.NetflixSurface
import com.trungdvu.netflix.ui.components.getIconTint
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.ui.navigation.Screen

enum class DashboardSections(
    @SuppressLint("SupportAnnotationUsage") @StringRes val title: String,
    val icon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Rounded.Home, Screen.DashboardHome.route),
    NEW_AND_HOT(
        "New & Hot",
        Icons.Rounded.PlayArrow,
        Screen.DashboardNewAndHot.route
    ),
    DOWNLOADS(
        "Downloads",
        Icons.Filled.Favorite,
        Screen.DashboardDownloads.route
    )
}

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
            contentColor = contentColor,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
                                text = section.title,
                                color = tint,
                                fontSize = 8.sp,
                                style = MaterialTheme.typography.button,
                                maxLines = 1
                            )
                        },
                        selected = selected,
                        onSelected = {
                            if (section.route != currentRoute) {
                                navController.navigate(section.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(findStartDestination(navController.graph).id) {
                                        saveState = true
                                    }
                                }
                            }
                        },
                        modifier = BottomNavigationItemPadding.weight(1f)
                    )
                }
            }

        }
    }
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
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onSelected,
                indication = null,
                interactionSource = remember { MutableInteractionSource() } // This is mandatory
            )
            .padding(bottom = 16.dp)
            .padding(top = 4.dp)
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

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private val BottomNavigationItemPadding = Modifier.padding()

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
