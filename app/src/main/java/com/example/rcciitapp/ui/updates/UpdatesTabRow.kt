package com.example.rcciitapp.ui.updates

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rcciitapp.R

enum class Sections(@StringRes val titleResId: Int) {
    Event(R.string.event), News(R.string.news), Notice(R.string.notice)
}

/**
 * TabContent for a single tab of the screen.
 *
 * This is intended to encapsulate a tab & it's content as a single object. It was added to avoid
 * passing several parameters per-tab from the stateful composable to the composable that displays
 * the current tab.
 *
 * @param section the tab that this content is for
 * @param section content of the tab, a composable that describes the content
 */
class TabContent(val section: Sections, val content: @Composable () -> Unit)

/**
 * Remembers the content for each tab on the Updates screen
 * gathering application data from []
 */
@Composable
fun rememberTabContent(): List<TabContent> {
    // collect uiState

    // Describe the screen sections here since each section needs 1 states and 1 event.
    // Pass them to the stateless UpdateScreen using a tabContent.
    val eventSection = TabContent(Sections.Event) {
        // collect event and call the EventListScreen
        Event()
    }
    val newsSection = TabContent(Sections.News) {
        Text(text = "News")

    }
    val noticeSection = TabContent(Sections.Notice) {
        Text(text = "Notice")

    }
    return listOf(eventSection, newsSection, noticeSection)
}


/**
 * TabRowContent for the UpdatesScreen
 */
@Composable
fun UpdatesTabRowContent(
    selectedTabIndex: Int,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier = Modifier
) {
    tabContent.forEachIndexed { index, content ->
        val colorText = if (selectedTabIndex == index) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = { updateSection(content.section) },
            modifier = Modifier.heightIn(min = 48.dp),
        ) {
            Text(
                text = stringResource(id = content.section.titleResId),
                modifier = modifier.paddingFromBaseline(top = 20.dp),
                color = colorText
            )
        }
    }
}