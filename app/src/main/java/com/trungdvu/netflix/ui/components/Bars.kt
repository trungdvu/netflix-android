package com.trungdvu.netflix.ui.components

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        targetValue = if (isScrolledDown) 0.dp else 0.dp,
    )
}

@Composable
fun getTopBarBlurState(isScrolledDown: Boolean): State<Dp> {
    return animateDpAsState(
        targetValue = if (isScrolledDown) 50.dp else 0.dp,
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
            .padding(top = getTopBarWidthState(isScrolledDown = isScrolledDown).value),
        color = getTopBarColorState(isScrolledDown = isScrolledDown).value,
    ) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = NetflixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            modifier = modifier.padding(top = 24.dp, bottom = 4.dp)
        ) {
            AppBar()
//          AnimatedVisibility(visible = isScrolledDown.not(), enter = fadeIn(), exit = fadeOut()) {
//          }
        }
    }
}

@Composable
fun AppBar(
    showBack: Boolean = false,
    upPress: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
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
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Box(
                modifier = Modifier
                    .clickable { }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = NetflixTheme.colors.iconInteractive
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
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
}

@Composable
private fun TopAppBarMenuItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = NetflixTheme.colors.textSecondary,
        maxLines = 1,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            shadow = Shadow(color = Color.Black, blurRadius = 10f),
            fontSize = 16.sp
        ),
        modifier = modifier
            .padding(5.dp)
            .clickable { }
    )
}

@Composable
private fun MenuBar() {
    Row {
        TopAppBarMenuItem(
            text = "TV Shows",
        )
        TopAppBarMenuItem(
            text = "Movies",
        )
        TopAppBarMenuItem(
            text = "Categories",
        )
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

@ExperimentalAnimationApi
@Composable
fun MovieDetailAppBar(
    modifier: Modifier = Modifier,
    upPress: () -> Unit
) {
    NetflixSurface(
        color = Color.Black.copy(alpha = 0.5f)
    ) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            contentColor = NetflixTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            modifier = modifier.padding(top = 24.dp, bottom = 4.dp)

        ) {
            Column {
                AppBar(showBack = true, upPress = upPress)
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
