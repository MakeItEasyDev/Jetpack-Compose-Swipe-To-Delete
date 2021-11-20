package com.jetpack.swipetodelete

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpack.swipetodelete.ui.theme.SwipeToDeleteTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeToDeleteTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SwipeToDelete()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun SwipeToDelete() {
    val items = mutableStateListOf(
        "Make it Easy 1",
        "Make it Easy 2",
        "Make it Easy 3",
        "Make it Easy 4",
        "Make it Easy 5",
        "Make it Easy 6",
        "Make it Easy 7",
        "Make it Easy 8",
        "Make it Easy 9",
        "Make it Easy 10"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Swipe to Delete",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item ->
                    item.hashCode()
                }
            ) { index, item ->
                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            items.remove(item)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = state,
                    background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Transparent
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = color)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = {
                        SampleItems(item)
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )
                Divider()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SampleItems(item: String) {
    ListItem(
        text = {
            Text(
                text = item
            )
        },
        overlineText = {
            Text(
                text = "Welcome To"
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Person"
            )
        },
        trailing = {
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = "DownArrow"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    )
}




















