package com.trungdvu.netflix.ui.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.ui.components.NetflixSurface
import androidx.constraintlayout.compose.ConstraintLayout
import com.trungdvu.netflix.data.constant.ApiConstant
import com.trungdvu.netflix.ui.components.InfoButton
import com.trungdvu.netflix.ui.components.MyListButton
import com.trungdvu.netflix.ui.components.PlayButton
import com.trungdvu.netflix.ui.theme.NetflixTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun HighlightedMovie(
    onClick: (Long) -> Unit,
    modifier: Modifier,
    movie: Movie
) {
    ConstraintLayout {
        val (movieImage, buttonPanel, topTrendingBanner) = createRefs()
        HighlightedMovieItem(movie, onClick,
            modifier = modifier.constrainAs(movieImage) {
                top.linkTo(parent.top)
            }
        )
//        TopTenBanner(
//            modifier = modifier.constrainAs(topTrendingBanner) {
//                bottom.linkTo(buttonPanel.top, margin = 24.dp)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            },
//        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .constrainAs(buttonPanel) {
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                }
        ) {
            MyListButton(modifier = modifier.weight(1f))
            PlayButton(isPressed = mutableStateOf(true), modifier = modifier.weight(1f))
            InfoButton(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun HighlightedMovieItem(
    movie: Movie,
    onMovieSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        FullScreenRemoteImage(
            imageUrl = ApiConstant.IMAGE_BASE_URL_W500 + movie.posterPath,
            modifier = modifier
                .fillMaxWidth()
                .height(640.dp)
                .clickable(onClick = {
                    onMovieSelected(movie.id)
                })
                .blur(50.dp)
                .applyGradient()
        )
        FullScreenRemoteImage(
            imageUrl = ApiConstant.IMAGE_BASE_URL_W500 + movie.posterPath,
            modifier = modifier
                .fillMaxWidth()
                .height(640.dp)
                .clickable(onClick = {
                    onMovieSelected(movie.id)
                })
                .padding(bottom = 124.dp, top = 124.dp, start = 48.dp, end = 48.dp)
        )
    }
}

@Composable
private fun TopTenBanner(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            backgroundColor = NetflixTheme.colors.banner,
            shape = RoundedCornerShape(10),
            modifier = modifier
                .size(
                    width = 28.dp,
                    height = 28.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TOP",
                    fontSize = 8.sp,
                    style = MaterialTheme.typography.button,
                    maxLines = 1
                )
                Text(
                    text = "10",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.button,
                    maxLines = 1
                )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "#2 in Vietnam Today",
            color = NetflixTheme.colors.textPrimary,
            fontSize = 14.sp,
            letterSpacing = (-0.10).sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
    }
}

@Composable
fun FullScreenRemoteImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    NetflixSurface(
        color = Color.LightGray,
        elevation = 0.dp,
        shape = RectangleShape,
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
    }
}

fun Modifier.applyGradient(): Modifier {
    return drawWithCache {
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = size.height / 3,
            endY = size.height
        )
        onDrawWithContent {
            drawContent()
            drawRect(gradient, blendMode = BlendMode.Multiply)
        }
    }
}


