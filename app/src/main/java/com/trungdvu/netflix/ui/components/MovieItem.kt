package com.trungdvu.netflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.trungdvu.netflix.data.constant.ApiConstant
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.ui.theme.NetflixTheme

@Composable
fun SmallMovieItem(
    movie: Movie,
    onMovieSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout {
        val (movieImage, topTrendingBanner) = createRefs()
        RoundedCornerRemoteImage(
            imageUrl = ApiConstant.IMAGE_BASE_URL_W500 + movie.posterPath,
            modifier = modifier
                .width(110.dp)
                .height(150.dp)
                .clickable(onClick = {
                    onMovieSelected(movie.id)
                })
                .constrainAs(movieImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
            cornerPercent = 3
        )
        if (movie.voteAverage >= 8) {
            TopTrendingBanner(
                modifier = modifier.constrainAs(topTrendingBanner) {
                    top.linkTo(movieImage.top)
                    end.linkTo(movieImage.end)
                },
                width = 20.dp,
                height = 28.dp,
                fontSizeTitle = 6.sp,
                fontSizeSubtitle = 12.sp
            )
        }
    }
}

@Composable
fun LargeMovieItem(
    movie: Movie,
    onMovieSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (movieImage, topTrendingBanner) = createRefs()

        RoundedCornerRemoteImage(
            imageUrl = ApiConstant.IMAGE_BASE_URL_W500 + movie.posterPath,
            modifier = modifier
                .width(170.dp)
                .height(320.dp)
                .clickable(onClick = {
                    onMovieSelected(movie.id)
                })
                .constrainAs(movieImage) {
                    top.linkTo(parent.top)
                },
            cornerPercent = 3
        )
        if (movie.voteAverage >= 8) {
            TopTrendingBanner(
                modifier = modifier.constrainAs(topTrendingBanner) {
                    end.linkTo(movieImage.end)
                },
                width = 25.dp,
                height = 32.dp,
                fontSizeTitle = 8.sp,
                fontSizeSubtitle = 14.sp
            )
        }
    }
}

@Composable
fun TopTrendingBanner(
    modifier: Modifier,
    width: Dp,
    height: Dp,
    fontSizeTitle: TextUnit,
    fontSizeSubtitle: TextUnit
) {
    Card(
        backgroundColor = NetflixTheme.colors.banner,
        shape = RoundedCornerShape(10),
        modifier = modifier
            .size(
                width = width,
                height = height
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
                fontSize = fontSizeTitle,
                style = MaterialTheme.typography.button,
                maxLines = 1
            )
            Text(
                text = "10",
                fontSize = fontSizeSubtitle,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.button,
                maxLines = 1
            )
        }
    }
}

@Composable
fun RoundedCornerRemoteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    cornerPercent: Int,
    elevation: Dp = 0.dp
) {
    NetflixSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = RoundedCornerShape(cornerPercent),
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