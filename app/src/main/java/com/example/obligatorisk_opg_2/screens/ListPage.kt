package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obligatorisk_opg_2.BirthdayUIState
import com.example.obligatorisk_opg_2.data.Birthday


@Composable
fun ListPage(
    onNavigateToEditListPage: () -> Unit,
    onNavigateToEditFriendPage: () -> Unit,
    onNavigateToHomePage: () -> Unit,
    onNavigateBack: () -> Unit,
    birthdayUIState: BirthdayUIState
) {
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
                    text = "UserName",
                    textDecoration = TextDecoration.Underline,
                    fontSize = 30.sp
                )
                Button(onClick = { onNavigateToHomePage() }) {
                    Text("Log Out")
                }
            }
            HorizontalDivider(thickness = 2.dp)


            // --- Action Buttons ---
            var searchWord by remember { mutableStateOf("") }
            Column(
                modifier = Modifier.padding(5.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onNavigateToEditListPage() },
                        modifier = Modifier
                            .padding(5.dp, end = 30.dp)
                            .size(width = 100.dp, height = 40.dp),
                    ) {
                        Text("Edit List")
                    }
                    OutlinedTextField(
                        value = searchWord,
                        onValueChange = { searchWord = it },
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 250.dp, height = 60.dp),
                        label = { Text("Filter...  ") },
                        singleLine = true
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 100.dp, height = 40.dp),
                    ) {
                        Text("Search")
                    }
                }


                // Check Boxes
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Sort By: ")

                        Row {
                            var checkedName by remember { mutableStateOf(false) }
                            var checkedAge by remember { mutableStateOf(false) }
                            var checkedBirthday by remember { mutableStateOf(false) }

                            Text(text = "Name")
                            Checkbox(
                                checked = checkedName,
                                onCheckedChange = { checkedName = it }
                            )
                            Text(text = "Age")
                            Checkbox(
                                checked = checkedAge,
                                onCheckedChange = { checkedAge = it }
                            )
                            Text(text = "Birthday")
                            Checkbox(
                                checked = checkedBirthday,
                                onCheckedChange = { checkedBirthday = it }
                            )
                        }
                        HorizontalDivider(thickness = 2.dp)
                    }
                }
            }


            // --- Friend Card ---
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(birthdayUIState.birthdays) { birthday ->
                    FriendCard(
                        onNavigateToEditFriendPage = onNavigateToEditFriendPage,
                        birthday = birthday
                    )
                }
            }
        }
    }
}

@Composable
private fun FriendCard(
    onNavigateToEditFriendPage: () -> Unit,
    birthday: Birthday
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 150.dp, width = 400.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = birthday.name,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Button(
                    onClick = { onNavigateToEditFriendPage() },
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 15.dp)
                ) {
                    Text("Edit Friend")
                }
            }
            VerticalDivider(modifier = Modifier.padding(all = 10.dp))
            Column(
                modifier = Modifier.weight(1.5f).padding(horizontal = 5.dp)
            ) {
                Text(text = "Birthday: ${birthday.birthDayOfMonth}/${birthday.birthMonth}/${birthday.birthYear}")
                Text(text = "Remark: ${birthday.remark}")
            }
        }
    }
}
