/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.wear.compose.integration.demos

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.compose.material.rememberScalingLazyListState

@Composable
fun ToggleChips(
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    description: String = "Toggle Chips"
) {
    val applicationContext = LocalContext.current
    val scrollState: ScalingLazyListState = rememberScalingLazyListState()
    var enabled by remember { mutableStateOf(true) }

    var checkBoxIconChecked by remember { mutableStateOf(true) }
    var switchIconChecked by remember { mutableStateOf(true) }
    var radioIconChecked by remember { mutableStateOf(true) }
    var radioIconWithSecondaryChecked by remember { mutableStateOf(true) }
    var splitWithCheckboxIconChecked by remember { mutableStateOf(true) }
    var splitWithSwitchIconChecked by remember { mutableStateOf(true) }
    var splitWithRadioIconChecked by remember { mutableStateOf(true) }

    var switchIconWithSecondaryChecked by remember { mutableStateOf(true) }
    var switchIconWithIconChecked by remember { mutableStateOf(true) }
    var splitWithCustomColorChecked by remember { mutableStateOf(true) }

    ScalingLazyColumnWithRSB(
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
    ) {
        item {
            ListHeader {
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption1,
                    color = Color.White
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = { Text("CheckboxIcon") },
                    checked = checkBoxIconChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.checkboxIcon(
                                checked = checkBoxIconChecked
                            ),
                            contentDescription = if (checkBoxIconChecked) "Checked" else "Unchecked"
                        )
                    },
                    onCheckedChange = { checkBoxIconChecked = it },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = { Text("SwitchIcon") },
                    checked = switchIconChecked,
                    // For Switch  toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.toggleChipColors(
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = switchIconChecked
                            ),
                            contentDescription = if (switchIconChecked) "On" else "Off"
                        )
                    },
                    onCheckedChange = { switchIconChecked = it },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = {
                        Text("RadioIcon", maxLines = 2, overflow = TextOverflow.Ellipsis)
                    },
                    checked = radioIconChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.radioIcon(checked = radioIconChecked),
                            contentDescription = if (radioIconChecked) "Selected" else "Unselected"
                        )
                    },
                    onCheckedChange = { radioIconChecked = it },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = {
                        Text(
                            "RadioIcon",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    secondaryLabel = {
                        Text("Custom Color", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    checked = radioIconWithSecondaryChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.radioIcon(
                                checked = radioIconWithSecondaryChecked
                            ),
                            contentDescription = if (radioIconWithSecondaryChecked) "Selected"
                            else "Unselected"
                        )
                    },
                    onCheckedChange = { radioIconWithSecondaryChecked = it },
                    enabled = enabled,
                    colors = ToggleChipDefaults.toggleChipColors(
                        checkedToggleControlColor = AlternatePrimaryColor3,
                        checkedEndBackgroundColor = AlternatePrimaryColor3.copy(alpha = 0.325f)
                    )
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = {
                        Text("SwitchIcon", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    secondaryLabel = {
                        Text("With secondary label", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    checked = switchIconWithSecondaryChecked,
                    // For Switch toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.toggleChipColors(
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = switchIconWithSecondaryChecked
                            ),
                            contentDescription = if (switchIconWithSecondaryChecked) "On" else "Off"
                        )
                     },
                    onCheckedChange = { switchIconWithSecondaryChecked = it },
                    appIcon = { DemoIcon(R.drawable.ic_airplanemode_active_24px) },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    label = { Text("SwitchIcon", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                    secondaryLabel = {
                        Text("With switchable icon", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    checked = switchIconWithIconChecked,
                    // For Switch  toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.toggleChipColors(
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = switchIconWithIconChecked
                            ),
                            contentDescription = if (switchIconWithIconChecked) "On" else "Off"
                        )
                    },
                    onCheckedChange = { switchIconWithIconChecked = it },
                    appIcon = {
                        if (switchIconWithIconChecked) DemoIcon(R.drawable.ic_volume_up_24px) else
                            DemoIcon(R.drawable.ic_volume_off_24px)
                    },
                    enabled = enabled,
                )
            }
        }
        item {
            ListHeader {
                Text(
                    text = "Split Toggle Chips",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption1,
                    color = Color.White
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                SplitToggleChip(
                    label = { Text("Split with CheckboxIcon") },
                    checked = splitWithCheckboxIconChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.checkboxIcon(
                                checked = splitWithCheckboxIconChecked
                            ),
                            contentDescription = if (splitWithCheckboxIconChecked) "Checked"
                            else "Unchecked"
                        )
                    },
                    onCheckedChange = { splitWithCheckboxIconChecked = it },
                    onClick = {
                        Toast.makeText(
                            applicationContext, "Text was clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                SplitToggleChip(
                    label = { Text("Split with SwitchIcon") },
                    checked = splitWithSwitchIconChecked,
                    // For Switch  toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.splitToggleChipColors(
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = splitWithSwitchIconChecked
                            ),
                            contentDescription = if (splitWithSwitchIconChecked) "On" else "Off"
                        )
                    },
                    onCheckedChange = { splitWithSwitchIconChecked = it },
                    onClick = {
                        Toast.makeText(
                            applicationContext, "Text was clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                SplitToggleChip(
                    label = { Text("Split with RadioIcon") },
                    checked = splitWithRadioIconChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.radioIcon(
                                checked = splitWithRadioIconChecked
                            ),
                            contentDescription = if (splitWithRadioIconChecked) "Selected"
                            else "Unselected"
                        )
                    },
                    onCheckedChange = { splitWithRadioIconChecked = it },
                    onClick = {
                        Toast.makeText(
                            applicationContext, "Text was clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                SplitToggleChip(
                    label = {
                        Text(
                            "Split with SwitchIcon", maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    secondaryLabel = {
                        Text(
                            "and custom color", maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    checked = splitWithCustomColorChecked,
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = splitWithCustomColorChecked
                            ),
                            contentDescription = if (splitWithCustomColorChecked) "On" else "Off"
                        )
                    },
                    onCheckedChange = { splitWithCustomColorChecked = it },
                    onClick = {
                        Toast.makeText(
                            applicationContext,
                            "Text was clicked", Toast.LENGTH_SHORT
                        ).show()
                    },
                    // For Switch  toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.splitToggleChipColors(
                        checkedToggleControlColor = AlternatePrimaryColor1,
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    enabled = enabled,
                )
            }
        }
        item {
            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                ToggleChip(
                    checked = enabled,
                    onCheckedChange = { enabled = it },
                    label = {
                        Text("Chips enabled")
                    },
                    // For Switch  toggle controls the Wear Material UX guidance is to set the
                    // unselected toggle control color to
                    // ToggleChipDefaults.switchUncheckedIconColor() rather than the default.
                    colors = ToggleChipDefaults.toggleChipColors(
                        uncheckedToggleControlColor = ToggleChipDefaults
                            .SwitchUncheckedIconColor
                    ),
                    toggleControl = {
                        Icon(
                            imageVector = ToggleChipDefaults.switchIcon(
                                checked = enabled
                            ),
                            contentDescription = if (enabled) "On" else "Off"
                        )
                    },
                )
            }
        }
    }
}
