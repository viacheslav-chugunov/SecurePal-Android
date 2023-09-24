package viach.apps.securepal.ui.view

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import viach.apps.securepal.R
import viach.apps.securepal.Screen

@Composable
fun MainBottomAppBar(
    currentRoute: String,
    onClickDashboard: () -> Unit,
    onClickSettings: () -> Unit,
    onClickAdd: () -> Unit
) {
    if (currentRoute !in setOf(Screen.Route.DASHBOARD, Screen.Route.SETTINGS)) return

    BottomAppBar(
        actions = {
            NavigationBarItem(
                selected = currentRoute == Screen.Route.DASHBOARD,
                onClick = onClickDashboard,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dashboard),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.dashboard)
                    )
                }
            )
            NavigationBarItem(
                selected = currentRoute == Screen.Route.SETTINGS,
                onClick = onClickSettings,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.settings)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickAdd) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    )
}