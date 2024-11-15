package com.example.movieapp.presentation.UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import coil3.compose.SubcomposeAsyncImage
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.util.RatingBar

@Composable
fun DetailsScreen(
    backStackEntry: NavBackStackEntry
) {
    val detaildViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detaildViewModel.detailsState.collectAsState().value

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            SubcomposeAsyncImage(
                model = MovieApi.Image_Base_URL + detailsState.movie?.backdrop_path,
                contentDescription = detailsState.movie?.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.Gray)
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailsState.movie?.title
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)){

                Box (
                    modifier = Modifier
                        .width(160.dp)
                ){
                    SubcomposeAsyncImage(
                        model = MovieApi.Image_Base_URL + detailsState.movie?.poster_path,
                        contentDescription = detailsState.movie?.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(22.dp)),
                        contentScale = ContentScale.Crop,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.Gray)
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(70.dp),
                                    imageVector = Icons.Rounded.ImageNotSupported,
                                    contentDescription = detailsState.movie?.title
                                )
                            }
                        },
                    )
                }

                detailsState.movie?.let {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        Text(
                            text = it.title ,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            RatingBar(
                                starsModifier = Modifier.size(22.dp),
                                rating = it.vote_average /2,
                            )
                            Text(
                                text = it.vote_average.toString().take(3),
                                modifier = Modifier.padding(start = 4.dp),
                                color = Color.LightGray,
                                fontSize = 18.sp,
                                maxLines = 1
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Language: "+it.original_language,
                            fontSize = 16.sp,
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Release Date: "+it.release_date,
                            fontSize = 16.sp,
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = it.vote_count.toString() +" Votes",
                            fontSize = 16.sp,
                            maxLines = 3
                        )
                    }
                }

            }
        }

        item {
            Column(Modifier.fillMaxSize()) {
            Text(
                text = "Overview" ,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = detailsState.movie?.overview ?: "",
                    fontSize = 16.sp,
                    maxLines = 30
                )
            }
        }

    }
}














