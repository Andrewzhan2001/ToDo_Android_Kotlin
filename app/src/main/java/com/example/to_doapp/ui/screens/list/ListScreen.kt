package com.example.to_doapp.ui.screens.list

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.to_doapp.R
import com.example.to_doapp.ui.theme.fabBackgroundColor
import com.example.to_doapp.ui.viewmodels.SharedViewModel
import com.example.to_doapp.util.SearchAppBarState

@Composable
@ExperimentalMaterialApi
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
       sharedViewModel.getAllTasks()
    }
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState



    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(allTasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        })
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = { onFabClicked(-1) },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add, // this is a  filled add icon
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White // set icon color
        )
    }
}

