package com.example.rcciitapp.ui.updates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Refer JetNews Interests Screen for any update
 */

@Composable
fun Updates() {
    // Initialize viewModel and pass uiState
    UpdateScreen()
}

@Composable
fun UpdateScreen() {
    val tabContent = rememberTabContent()
    val (currentSelection, updateSelection) = rememberSaveable {
        mutableStateOf(tabContent.first().section)
    }
    UpdateScreenContent(
        tabContent = tabContent, currentSection = currentSelection, updateSection = updateSelection
    )

}

/**
 * Displays a tab row with [currentSection] selected and the body of the corresponding [tabContent].
 *
 * @param currentSection (state) the tab that is currently selected
 * @param updateSection (event) request a change in tab selection
 * @param tabContent (slot) tabs and their content to display, must be a non-empty list, tabs are
 * displayed in the order of this list
 */
@Composable
fun UpdateScreenContent(
    tabContent: List<TabContent>,
    currentSection: Sections,
    updateSection: (Sections) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }
    Column(modifier) {
        TabRow(selectedTabIndex = selectedTabIndex, divider = {
            androidx.compose.material.TabRowDefaults.Divider(
                color = MaterialTheme.colorScheme.secondary, thickness = 2.dp
            )
        }, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = MaterialTheme.colorScheme.secondaryContainer,
                height = 2.dp
            )
        }) {
            UpdatesTabRowContent(
                selectedTabIndex = selectedTabIndex,
                updateSection = updateSection,
                tabContent = tabContent
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            // display the current tab content which is a @Composable () -> Unit
            tabContent[selectedTabIndex].content()
        }
    }
}

