package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ListPage(
    onNavigateToEditListPage: () -> Unit,
    onNavigateToEditFriendPage: () -> Unit,
    onNavigateToHomePage: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold { innerPadding ->
        // Use ONE main Column to stack everything vertically
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
                modifier = Modifier.padding(16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { onNavigateToEditListPage() }) {
                        Text("Edit List")
                    }
                    OutlinedTextField(
                        value = searchWord,
                        onValueChange = { searchWord = it },
                        modifier = Modifier.padding(16.dp),
                        label = { Text("Filter...  ") },
                        singleLine = true
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = "Sort By: ")

                        Row() {
                            var checked by remember { mutableStateOf(false) }
                            Text(text = "Name")
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { checked = it }
                            )
                            Text(text = "Age")
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { checked = it }
                            )
                            Text(text = "Birthday")
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { checked = it }
                            )
                        }
                        HorizontalDivider(thickness = 2.dp)
                    }
                }
            }




            // --- Friend Card ---
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .size(width = 400.dp, height = 150.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "NAME",
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
                        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                            Text(text = "Birthday: INSERT BIRTHDAY")
                            Text(text = "Remark: INSERT REMARK")
                        }

                    }
                }
            }
        }
    }
}
