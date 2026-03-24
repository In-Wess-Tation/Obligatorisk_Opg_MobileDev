package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obligatorisk_opg_2.data.Birthday

@Composable
fun EditFriendPage(
    selectedBirthday: Birthday?,
    onUpdateBirthday: (Int, Birthday) -> Unit,
    onNavigateToListPage: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var remark by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }

    LaunchedEffect(selectedBirthday) {
        selectedBirthday?.let {
            name = it.name ?: ""
            remark = it.remarks ?: ""
            birthDay = it.birthDayOfMonth.toString()
            birthMonth = it.birthMonth.toString()
            birthYear = it.birthYear.toString()
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // --- Header ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Edit Friend",
                    textDecoration = TextDecoration.Underline,
                    fontSize = 30.sp
                )
                Button(onClick = { onNavigateToListPage() }) {
                    Text("Cancel")
                }
            }
            HorizontalDivider(thickness = 2.dp)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = remark,
                    onValueChange = { remark = it },
                    label = { Text("Remark") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = birthDay,
                        onValueChange = { birthDay = it },
                        label = { Text("Day") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = birthMonth,
                        onValueChange = { birthMonth = it },
                        label = { Text("Month") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = birthYear,
                        onValueChange = { birthYear = it },
                        label = { Text("Year") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Button(
                    onClick = {
                        if (name.isNotBlank() && remark.isNotBlank() && birthDay.isNotBlank() && 
                            birthMonth.isNotBlank() && birthYear.isNotBlank() && selectedBirthday != null) {
                            
                            val updatedBirthday = selectedBirthday.copy(
                                name = name,
                                remarks = remark,
                                birthDayOfMonth = birthDay.toIntOrNull() ?: selectedBirthday.birthDayOfMonth,
                                birthMonth = birthMonth.toIntOrNull() ?: selectedBirthday.birthMonth,
                                birthYear = birthYear.toIntOrNull() ?: selectedBirthday.birthYear
                            )
                            onUpdateBirthday(selectedBirthday.id, updatedBirthday)
                            onNavigateToListPage()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Update Friend")
                }
            }
        }
    }
}