package viach.apps.securepal.extension

import androidx.navigation.NavController
import viach.apps.securepal.Screen

fun NavController.navigate(screen: Screen) {
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""
    navigate(screen.route) {
        if (screen.newTask && currentRoute.isNotEmpty()) {
            popBackStack(currentRoute, inclusive = true)
            popBackStack()
        }
    }
}