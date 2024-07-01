package com.artemissoftware.multimovies.android.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemissoftware.multimovies.android.MyApplicationTheme
import com.artemissoftware.multimovies.android.PreviewData
import com.artemissoftware.multimovies.android.R
import com.artemissoftware.multimovies.domain.models.Movie

@Composable
internal fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: (Movie) -> Unit) {

    Card(
        modifier = modifier
            .height(220.dp)
            .clickable { onClick(movie) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 2.dp, bottomEnd = 2.dp))
                )

                Surface(
                    color = Color.Black.copy(alpha = 0.6f),
                    modifier = modifier
                        .size(50.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_play_button),
                        contentDescription = null,
                        modifier = modifier.padding(12.dp).align(Alignment.Center)
                    )
                }
            }

            Column(
                modifier = modifier.padding(10.dp)
            ){
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(4.dp))

                Text(text = movie.releaseDate, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
private fun MovieItemPreview() {
    MyApplicationTheme {
        MovieItem(
            movie = PreviewData.movie,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    }
}