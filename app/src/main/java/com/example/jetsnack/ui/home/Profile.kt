/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.home

import ai.securiti.cmpsdkcore.main.SecuritiMobileCmp
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.Settings
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetsnack.R
import com.example.jetsnack.ui.theme.JetsnackTheme
import kotlinx.coroutines.launch

@Composable
fun Profile(
    modifier: Modifier = Modifier
) {
    fun Context.getActivity(): ComponentActivity? = when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.getActivity()
        else -> null
    }

    var purposeStateText by rememberSaveable { mutableStateOf("") }
    var purposeStateVisible by remember { mutableStateOf(false) }
    var purposeStateLines by remember { mutableIntStateOf(3) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.work_in_progress),
            style = MaterialTheme.typography.titleLarge.copy(textDecoration = TextDecoration.Underline),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.grab_beverage) + ": " + SecuritiMobileCmp.VERSION,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                purposeStateVisible = false
                context.getActivity()?.let { SecuritiMobileCmp.presentPreferenceCenter(it) }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White

            )
        ) {
            Text("My Preferences")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                context.getActivity()?.let {
                    purposeStateVisible = false
                    SecuritiMobileCmp.resetConsent()
                    coroutineScope.launch {
                        val toast = Toast.makeText(context, "Consents Cleared!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                        toast.show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White

            )
        ) {
            Text("Reset Consent")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                purposeStateVisible = false
                context.getActivity()?.let {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", "com.example.jetsnack", null)
                    intent.data = uri
                    it.startActivity(intent)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White

            )
        ) {
            Text("Update App Permissions")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                context.getActivity()?.let {
                    purposeStateVisible = true
                    val purposes = SecuritiMobileCmp.getPurposes()
                    var pText = ""
                    if (purposes != null) {
                        for (purpose in purposes) {
                            pText += purpose.purposeName?.get("_").toString().trim('"') + ": " + purpose.purposeId?.let { it1 ->
                                 SecuritiMobileCmp.getConsent(
                                    it1
                                ).consentStatus.toString()
                            } + "\n"
                        }
                        purposeStateLines = 1 + purposes.size
                    }
                    purposeStateText = pText
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White

            )
        ) {
            Text("getPurposeConsents()")
        }
        Spacer(Modifier.height(16.dp))
        if (purposeStateVisible) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
                text = purposeStateText,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleMedium,
                maxLines = purposeStateLines
            )
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProfilePreview() {
    JetsnackTheme {
        Profile()
    }
}
