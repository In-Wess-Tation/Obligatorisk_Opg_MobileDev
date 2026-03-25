package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obligatorisk_opg_2.BirthdayUIState
import com.example.obligatorisk_opg_2.data.Birthday

@Composable
fun EditListPage(
    onNavigateToListPage: () -> Unit,
    onNavigateBack: () -> Unit,
    birthdayUIState: BirthdayUIState,
    onBirthdayDelete: (Birthday) -> Unit = {},
    onBirthdayAdd: (Birthday) -> Unit = {}
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Edit Friend List",
                    textDecoration = TextDecoration.Underline,
                    fontSize = 28.sp
                )
                Button(onClick = { onNavigateToListPage() }) {
                    Text("Finish Editing")
                }
            }
            HorizontalDivider(thickness = 2.dp)


            // Edit Text fields
            var name by remember { mutableStateOf("") }
            var remark by remember { mutableStateOf("") }
            var birthdayInput by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.weight(1f),
                        label = { Text("Name") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = remark,
                        onValueChange = { remark = it },
                        modifier = Modifier.weight(1f),
                        label = { Text("Remark") },
                        singleLine = true
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = birthdayInput,
                        onValueChange = { birthdayInput = it },
                        modifier = Modifier.weight(2f),
                        label = { Text("Birthday (DD/MM/YYYY)") },
                        singleLine = true
                    )
                    Button(
                        onClick = {
                            val dateParts = birthdayInput.split("/")
                            if (dateParts.size == 3 && name.isNotBlank()) {
                                val newBirthday = Birthday(
                                    id = 0,
                                    userId = "testuser4@gmail.com",
                                    name = name,
                                    birthDayOfMonth = dateParts[0].toIntOrNull() ?: 1,
                                    birthMonth = dateParts[1].toIntOrNull() ?: 1,
                                    birthYear = dateParts[2].toIntOrNull() ?: 2000,
                                    remarks = remark,
                                    age = 0
                                )
                                onBirthdayAdd(newBirthday)
                                name = ""
                                remark = ""
                                birthdayInput = ""
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "ADD")
                    }
                }

            }
            HorizontalDivider(thickness = 2.dp)

            // --- Friend Card ---
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(birthdayUIState.birthdays) { birthdayItem ->
                    FriendCard(
                        birthday = birthdayItem,
                        onDeleteClick = { onBirthdayDelete(birthdayItem) }
                    )
                }
            }
        }
    }
}

@Composable
fun FriendCard(
    birthday: Birthday,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Name Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = birthday.name ?: "",
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            VerticalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Details Column
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Birthday: ${birthday.birthDayOfMonth}/${birthday.birthMonth}/${birthday.birthYear}",
                    fontSize = 14.sp,
                    maxLines = 1
                )
                Text(
                    text = "Remark: ${birthday.remarks ?: ""}",
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            VerticalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Action Column
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "DELETE",
                        color = Color.White,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}
