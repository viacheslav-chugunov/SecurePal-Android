package viach.apps.securepal.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import viach.apps.securepal.R

@Composable
fun DashboardDotIndicator(
    currentPage: Int,
    onBackArrowClicked: suspend () -> Unit,
    onNextArrowClicked: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .alpha(if (currentPage != 0) 1f else 0f)
                .clickable(enabled = currentPage != 0) {
                    coroutineScope.launch {
                        onBackArrowClicked()
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_two_arrows_back),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = if (currentPage == 2) R.string.notes else R.string.auth))
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier
                    .scale(if (currentPage == 0) 1.2f else 1f)
                    .size(16.dp)
                    .alpha(if (currentPage == 0) 1f else 0.2f),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier
                    .scale(if (currentPage == 1) 1.2f else 1f)
                    .size(16.dp)
                    .alpha(if (currentPage == 1) 1f else 0.2f),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier
                    .scale(if (currentPage == 2) 1.2f else 1f)
                    .size(16.dp)
                    .alpha(if (currentPage == 2) 1f else 0.2f),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Row(
            modifier = Modifier
                .alpha(if (currentPage != 2) 1f else 0f)
                .clickable(enabled = currentPage != 2) {
                    coroutineScope.launch {
                        onNextArrowClicked()
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = if (currentPage == 0) R.string.notes else R.string.cards))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_two_arrows_next),
                contentDescription = null
            )
        }
    }
}