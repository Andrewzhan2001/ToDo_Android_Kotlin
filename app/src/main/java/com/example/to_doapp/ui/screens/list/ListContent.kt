package com.example.to_doapp.ui.screens.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.ui.data.models.Priority
import com.example.to_doapp.ui.data.models.TodoTask
import com.example.to_doapp.ui.theme.*
import com.example.to_doapp.util.Action
import com.example.to_doapp.util.RequestState
import com.example.to_doapp.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun ListContent(
    allTasks: RequestState<List<TodoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (allTasks is RequestState.Success) {
        HandleListContent(
            tasks = allTasks.data,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks: List<TodoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}



// when we click one of those tasks, it will pass to task screen with that taskid

// surface component api ins in experimental
@ExperimentalMaterialApi
@Composable
fun TaskItem(toDoTask: TodoTask, navigateToTaskScreen: (taskId: Int) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                // title of task
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                // priority icon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = toDoTask.priority.color)
                    }
                }
            }
            // detials of task
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        toDoTask = TodoTask(
            id = 0,
            title = "Title",
            description = "Lorem ipsum dolor sit amet.",
            priority = Priority.MEDIUM
        ), navigateToTaskScreen = {}
    )
}