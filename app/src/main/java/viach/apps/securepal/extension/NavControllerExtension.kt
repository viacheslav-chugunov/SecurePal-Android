package viach.apps.securepal.extension

import android.os.Bundle
import androidx.navigation.NavController
import viach.apps.securepal.Screen

fun NavController.navigate(screen: Screen, args: (Bundle.() -> Unit)? = null) {
    val currentRoute = currentDestination?.route ?: ""
    if (screen.newTask) {
        popBackStack(currentRoute, inclusive = true)
        popBackStack()
    }
    navigate(screen.route) {
        currentBackStackEntry?.arguments?.let { args?.invoke(it) }
    }
}