package pe.edu.ulima.cinelunaapp.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.edu.ulima.cinelunaapp.presentation.components.DrawerMenu
import pe.edu.ulima.cinelunaapp.presentation.components.NavigationGraph
import pe.edu.ulima.cinelunaapp.presentation.components.TopBar

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val scaffoldState  = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val title = remember {
        mutableStateOf("Hola Randal")
    }

    val openDrawer : () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.open()
        }

    }
    val closeDrawer : () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
    val changeTitle : (String) -> Unit = { newTitle ->
        title.value = newTitle
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                title = title.value,
                onOpenDrawer = openDrawer
            )
        },
        drawerContent = {
            DrawerMenu(
                navController = navController,
                onCloseDrawer = closeDrawer,
                onChangeTitle = changeTitle
            )
        }
    ) {
        NavigationGraph(
            navController = navController
        )
    }
}