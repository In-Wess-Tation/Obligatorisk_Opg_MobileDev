package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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


@Composable
fun EditFriendPage(
    onNavigateToListPage: () -> Unit,
    onNavigateBack: () -> Unit,
    birthdayUIState: BirthdayUIState
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                    fontSize = 30.sp
                )
                Button(onClick = { onNavigateToListPage() }) {
                    Text("Finish Editing")
                }
            }
            HorizontalDivider(thickness = 2.dp)

            // Edit Text fields
            var name by remember { mutableStateOf("") }
            var birthday by remember { mutableStateOf("") }
            var remark by remember { mutableStateOf("") }
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    modifier = Modifier,
                    text = "Name",
                    fontSize = 25.sp
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .padding(5.dp, bottom = 40.dp)
                        .size(width = 350.dp, height = 60.dp),
                    label = { Text("Insert Name... ") },
                    singleLine = true
                )
                Text(
                    text = "Birthday",
                    fontSize = 25.sp
                )
                OutlinedTextField(
                    value = birthday,
                    onValueChange = { birthday = it },
                    modifier = Modifier
                        .padding(5.dp, bottom = 40.dp)
                        .size(width = 350.dp, height = 60.dp),
                    label = { Text("Insert Birthday... ") },
                    singleLine = true
                )
                Text(
                    text = "Remark",
                    fontSize = 25.sp
                )
                OutlinedTextField(
                    value = remark,
                    onValueChange = { remark = it },
                    modifier = Modifier
                        .padding(5.dp)
                        .size(width = 350.dp, height = 60.dp),
                    label = { Text("Insert Remark... ") },
                    singleLine = true
                )
            }










        }
    }
}






