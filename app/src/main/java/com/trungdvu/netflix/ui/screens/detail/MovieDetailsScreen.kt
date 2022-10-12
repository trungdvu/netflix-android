package com.trungdvu.netflix.ui.screens.detail

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trungdvu.netflix.R
import com.trungdvu.netflix.model.Movie
import com.trungdvu.netflix.ui.components.DownloadButton
import com.trungdvu.netflix.ui.components.MovieDetailAppBar
import com.trungdvu.netflix.ui.components.PlayButton
import com.trungdvu.netflix.ui.theme.NetflixTheme
import com.trungdvu.netflix.ui.viewModel.ViewModelProvider
import androidx.compose.runtime.saveable.rememberSaveable
import com.trungdvu.netflix.ui.screens.detail.components.*

@ExperimentalAnimationApi
@Composable
fun MovieDetailsScreen(
    movieId: Long,
    upPress: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        MovieDetailAppBar(upPress = upPress)

        ViewModelProvider.movieVideosViewModel.fetchMovieVideoById(movieId = movieId)
        val movieVideo: String by ViewModelProvider.movieVideosViewModel.movieVideo
            .observeAsState("")

        if (movieVideo.isNotEmpty()) {
            MovieVideo(videoUrl = movieVideo)
        }

        when (val selectedMovie = ViewModelProvider.previewMovieViewModel.selectedMovie) {
            is com.trungdvu.netflix.model.Result.Success -> {
                selectedMovie.data?.let { safeSelectedMovie ->
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .verticalScroll(state = rememberScrollState())
                    ) {
                        MovieDetailLayout(movie = safeSelectedMovie)
                        Spacer(modifier = Modifier.height(20.dp))
                        MoreMovies(movieId = safeSelectedMovie.id)
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieDetailLayout(movie: Movie) {
    Row {
        Image(
            painterResource(id = R.drawable.netflix_logo),
            contentDescription = "Netflix Logo",
            modifier = Modifier
                .size(20.dp)
                .clickable { }
        )
        Text(
            text = "FILM",
            fontSize = 13.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically),
            letterSpacing = 2.sp
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = movie.title,
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row {
        Text(
            text = "98% Match",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff65b562)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .background(
                    color = NetflixTheme.colors.uiLighterBackground,
                    shape = RoundedCornerShape(8)
                )
                .padding(start = 4.dp, top = 1.dp, end = 4.dp, bottom = 1.dp)
        ) {
            Text(
                text = "7+",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = NetflixTheme.colors.textSecondary
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "1h 25m",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = NetflixTheme.colors.textSecondaryDark
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .background(
                    color = NetflixTheme.colors.progressIndicatorBg,
                    shape = RoundedCornerShape(8)
                )
                .padding(start = 3.dp, top = 0.dp, end = 3.dp, bottom = 0.dp)
        ) {
            Text(
                text = "HD",
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                letterSpacing = 2.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
    PlayButton(
        isPressed = remember { mutableStateOf(true) },
        modifier = Modifier.fillMaxWidth(),
        cornerPercent = 5
    )
    Spacer(modifier = Modifier.height(8.dp))
    DownloadButton(
        isPressed = remember { mutableStateOf(true) },
        modifier = Modifier.fillMaxWidth(),
        cornerPercent = 5
    )
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = movie.overview,
        fontSize = 13.sp,
        textAlign = TextAlign.Justify,
        fontWeight = FontWeight.Light,
        maxLines = 4,
        lineHeight = 18.sp,
        overflow = TextOverflow.Ellipsis,
        color = NetflixTheme.colors.textSecondary
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row {
        Text(
            text = "Average Vote: ",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = NetflixTheme.colors.textSecondaryDark
        )
        Text(
            text = movie.voteAverage.toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = NetflixTheme.colors.textSecondaryDark
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row {
        ImageButton(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp),
            icon = Icons.Default.Check,
            text = "My List"
        )
        ImageButton(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp),
            icon = Icons.Outlined.ThumbUp,
            text = "Rate"
        )
        ImageButton(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp),
            icon = Icons.Default.Share,
            text = "Share"
        )
    }
}

@Composable
fun MoreMovies(movieId: Long) {
    val (currentCategory, setCurrentCategory) = rememberSaveable { mutableStateOf(MoreMoviesCategory.MoreLikeThis) }
    MoreMoviesTabs(
        selectedCategory = currentCategory,
        onCategorySelected = setCurrentCategory,
        modifier = Modifier.fillMaxWidth()
    )
    ViewModelProvider.previewMovieViewModel.fetchSimilarMovies(movieId = movieId)
    val tweenSpec = remember { getAnimSpec() }
    Crossfade(
        currentCategory,
        animationSpec = tweenSpec,
        modifier = Modifier.padding(2.dp)
    ) { category ->
        when (category) {
            MoreMoviesCategory.MoreLikeThis -> MoreLikeThis()
            MoreMoviesCategory.TrailersAndMore -> TrailersAndMore()
        }
    }
}

private fun getAnimSpec(): TweenSpec<Float> {
    return TweenSpec(
        durationMillis = 600,
        easing = LinearOutSlowInEasing
    )
}

@Composable
fun MovieVideo(videoUrl: String) {
    ViewModelProvider.videoViewModel.extract(LocalContext.current, youtubeLink = videoUrl)
    val extractedVideoUrl: String by ViewModelProvider.videoViewModel.extractedVideoUrl
        .observeAsState("")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(Color.Black)
    ) {
        if (extractedVideoUrl.isNotEmpty()) {
            VideoPlayer(url = extractedVideoUrl)
        }
    }
}

@Composable
fun ImageButton(
    modifier: Modifier,
    icon: ImageVector,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = {})
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 10.sp,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
    }
}