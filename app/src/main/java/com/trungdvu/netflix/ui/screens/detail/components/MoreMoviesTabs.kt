package com.trungdvu.netflix.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trungdvu.netflix.ui.theme.NetflixTheme

enum class MoreMoviesCategory {
    MoreLikeThis,
    TrailersAndMore
}

@Composable
fun MoreMoviesTabs(
    modifier: Modifier = Modifier,
    categories: List<MoreMoviesCategory> = listOf(
        MoreMoviesCategory.MoreLikeThis, MoreMoviesCategory.TrailersAndMore
    ),
    selectedCategory: MoreMoviesCategory,
    onCategorySelected: (MoreMoviesCategory) -> Unit
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        HomeCategoryTabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
        )
    }

    Column {
        Divider(
            color = NetflixTheme.colors.uiLighterBackground,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            indicator = indicator,
            divider = {},
            edgePadding = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = { onCategorySelected(category) },
                    modifier = modifier,
                    text = {
                        Text(
                            text = when (category) {
                                MoreMoviesCategory.MoreLikeThis -> "More Like This"
                                MoreMoviesCategory.TrailersAndMore -> "Trailers & More"
                            },
                            color = if (index == selectedIndex) {
                                NetflixTheme.colors.textPrimary
                            } else {
                                NetflixTheme.colors.textSecondaryDark
                            },
                            style = TextStyle(
                                fontWeight = if (index == selectedIndex) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                },
                                fontSize = 12.sp
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun HomeCategoryTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = NetflixTheme.colors.accent
) {
    Spacer(
        modifier
            .width(5.dp)
            .height(3.dp)
            .background(color, RoundedCornerShape(percent = 50))
    )
}
