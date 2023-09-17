package viach.apps.securepal.ui.screen.dashboard

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viach.apps.securepal.R
import viach.apps.securepal.Screen
import viach.apps.securepal.ui.view.DashboardDotIndicator
import viach.apps.securepal.ui.view.empty

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
    openScreen: (Screen) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val currentPage = pagerState.currentPage

    LaunchedEffect(state.openScreen) {
        state.openScreen?.let {
            openScreen(it)
            onAction(DashboardAction.HandleOpenedScreen)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DashboardDotIndicator(
            currentPage = currentPage,
            onBackArrowClicked = { pagerState.animateScrollToPage(currentPage - 1) },
            onNextArrowClicked = { pagerState.animateScrollToPage(currentPage + 1) }
        )
        HorizontalPager(
            state = pagerState,
            pageSpacing = 16.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
        ) { page ->
            when (page) {
                0 -> {
                    val emptyMessageColor = MaterialTheme.colorScheme.onPrimaryContainer
                    val emptyMessageButtonColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = CutCornerShape(16.dp)
                            )
                    ) {
                        empty(
                            items = state.authRecords,
                            messageRes = R.string.you_dont_have_any_added_auth_records_yet,
                            messageColor = emptyMessageColor,
                            buttonRes = R.string.add_auth_record,
                            buttonColors = emptyMessageButtonColors,
                            onButtonClick = { onAction(DashboardAction.OpenScreen(Screen.NewAuthRecord)) }
                        )
                        itemsIndexed(state.authRecords) { index, authRecord ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CutCornerShape(8.dp)
                                    ).clickable {
                                        onAction(DashboardAction.OpenScreen(Screen.ShowAuthRecord(authRecord)))
                                    }
                            ) {
                                Text(
                                    text = authRecord.title,
                                    modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 2.dp)
                                )
                                Text(
                                    text = authRecord.auth,
                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                                )
                            }
                            if (index != state.authRecords.lastIndex) {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
                1 -> {
                    val emptyMessageColor = MaterialTheme.colorScheme.onSecondaryContainer
                    val emptyMessageButtonColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = CutCornerShape(16.dp)
                            )
                    ) {
                        empty(
                            items = state.noteRecords,
                            messageRes = R.string.you_dont_have_any_added_notes_yet,
                            messageColor = emptyMessageColor,
                            buttonRes = R.string.add_note,
                            buttonColors = emptyMessageButtonColors,
                            onButtonClick = { onAction(DashboardAction.OpenScreen(Screen.NewNoteRecord)) }
                        )
                        items(state.noteRecords) { noteRecrod ->

                        }
                    }
                }
                2 -> {
                    val emptyMessageColor = MaterialTheme.colorScheme.onTertiaryContainer
                    val emptyMessageButtonColors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = CutCornerShape(16.dp)
                            )
                    ) {
                        empty(
                            items = state.cardRecords,
                            messageRes = R.string.you_dont_have_any_added_cards_yet,
                            messageColor = emptyMessageColor,
                            buttonRes = R.string.add_card,
                            buttonColors = emptyMessageButtonColors,
                            onButtonClick = { onAction(DashboardAction.OpenScreen(Screen.NewCardRecord)) }
                        )
                        items(state.cardRecords) { cardRecord ->

                        }
                    }
                }
            }
        }
    }

}