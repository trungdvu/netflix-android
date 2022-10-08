package com.trungdvu.netflix.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trungdvu.netflix.ui.theme.NetflixTheme

@Composable
fun MyListButton(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = {})
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "My List",
            fontSize = 10.sp,
            style = MaterialTheme.typography.button,
            maxLines = 1,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.05).sp,
        )
    }
}

@Composable
fun InfoButton(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(selected = false, onClick = {})
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Info",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.05).sp,
            style = MaterialTheme.typography.button,
            maxLines = 1
        )
    }
}


@Composable
fun PlayButton(
    isPressed: MutableState<Boolean>,
    modifier: Modifier,
    cornerPercent: Int = 8
) {
    Button(
        onClick = { isPressed.value = isPressed.value.not() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(cornerPercent),
        modifier = modifier
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                tint = NetflixTheme.colors.textInteractive,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Play",
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.05).sp,
                color = NetflixTheme.colors.textInteractive,
                style = MaterialTheme.typography.button,
                maxLines = 1
            )
        }
    }
}

