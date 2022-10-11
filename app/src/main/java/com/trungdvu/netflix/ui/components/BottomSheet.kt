package com.trungdvu.netflix.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.ui.viewModel.ViewModelProvider
import com.trungdvu.netflix.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun BottomSheetContent(
    onMovieClick: (Long) -> Unit,
    onBottomSheetClosePressed: () -> Unit
) {
    when (val selectedMovie = ViewModelProvider.selectedMovieViewModel.selectedMovie) {
        is Result.Success -> {
            selectedMovie.data?.let { safeSelectedMovie ->
                BottomSheetLayout(
                    selectedMovie = safeSelectedMovie,
                    onMovieClick = onMovieClick,
                    onBottomSheetClosePressed = onBottomSheetClosePressed
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun BottomSheetLayout(
    selectedMovie: Movie,
    onMovieClick: (Long) -> Unit,
    onBottomSheetClosePressed: () -> Unit
) {
    NetflixSurface(
        shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
        color = NetflixTheme.colors.uiLightBackground,
        modifier = Modifier
            .wrapContentWidth()
            .height(350.dp)
            .clickable { onMovieClick(selectedMovie.id) }
    ) {
        NetflixSurface(
            shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5),
            color = NetflixTheme.colors.uiLightBackground,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column() {
                Row(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 6.dp
                    )
                ) {
                    SmallMovieItem(
                        selectedMovie,
                        onMovieSelected = onMovieClick
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = selectedMovie.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    maxLines = 1
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Row {
                                    Text(
                                        text = "",
                                        color = NetflixTheme.colors.textSecondaryDark,
                                        fontSize = 12.sp,
                                        maxLines = 1
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = selectedMovie.voteAverage.toString(),
                                        color = NetflixTheme.colors.textSecondaryDark,
                                        fontSize = 12.sp,
                                        maxLines = 1
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = selectedMovie.voteCount.toString(),
                                        color = NetflixTheme.colors.textSecondaryDark,
                                        fontSize = 12.sp,
                                        maxLines = 1
                                    )
                                }
                            }
                            IconButton(
                                onClick = { onBottomSheetClosePressed() },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color = NetflixTheme.colors.uiLighterBackground)
                                    .size(25.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    tint = NetflixTheme.colors.iconTint,
                                    contentDescription = null,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = selectedMovie.overview,
                            fontSize = 14.sp,
                            maxLines = 5,
                            lineHeight = 18.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.padding(
                        top = 6.dp,
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                ) {
                    PlayButton(
                        isPressed = mutableStateOf(true),
                        modifier = Modifier.weight(2f)
                    )
                    IconTextButton(
                        buttonIcon = Icons.Outlined.KeyboardArrowDown,
                        buttonText = "Download",
                        onButtonClick = {},
                        modifier = Modifier.weight(1f),
                    )
                    IconTextButton(
                        buttonIcon = Icons.Outlined.PlayArrow,
                        buttonText = "Preview",
                        onButtonClick = {},
                        modifier = Modifier.weight(1f),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = NetflixTheme.colors.uiLighterBackground, thickness = 1.dp)
                EpisodesAndInfo(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    )
                )
            }
        }
    }
}

@Composable
fun IconTextButton(
    buttonIcon: ImageVector,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = { onButtonClick() })
    ) {
        Icon(
            imageVector = buttonIcon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buttonText,
            fontSize = 10.sp,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
    }
}

@Composable
fun EpisodesAndInfo(modifier: Modifier) {
    Row(modifier = modifier) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Episodes &amp; Info",
            modifier = Modifier
                .weight(10f)
                .align(Alignment.CenterVertically)
                .padding(start = 4.dp),
            fontSize = 12.sp,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
        Icon(
            imageVector = Icons.Outlined.ArrowForward,
            modifier = Modifier
                .weight(1f)
                .size(20.dp),
            contentDescription = null
        )
    }
}

@ExperimentalMaterialApi
fun onBottomSheetTapped(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    coroutineScope.launch {
        try {
            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            } else {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        } catch (e: IllegalArgumentException) {
            Timber.e("Exception in Bottom Sheet: ${e.message}")
        }
    }
}
