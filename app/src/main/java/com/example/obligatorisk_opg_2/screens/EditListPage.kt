package com.example.obligatorisk_opg_2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obligatorisk_opg_2.NavRoutes

@Composable
fun EditListPage(
    onNavigateToListPage: () -> Unit,
    onNavigateBack: () -> Unit
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
            var remark by remember { mutableStateOf("") }
            var birthday by remember { mutableStateOf("") }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 200.dp, height = 60.dp),
                        label = { Text("Name ") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = remark,
                        onValueChange = { remark = it },
                        modifier = Modifier
                            .padding(5.dp)
                            .size(width = 200.dp, height = 60.dp),
                        label = { Text("Remark ") },
                        singleLine = true
                    )
                }
                Row(
                    modifier = Modifier
                        .size(width = 400.dp, height = 100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = birthday,
                        onValueChange = { birthday = it },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 250.dp, height = 60.dp),
                        label = { Text("Birthday ") },
                        singleLine = true
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(top = 25.dp, end = 25.dp)
                    ) {
                        Text(text = "ADD")
                    }
                }

            }
            HorizontalDivider(thickness = 2.dp)

            // --- Friend Card ---
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .size(width = 400.dp, height = 150.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = "NAME",
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                            }
                            VerticalDivider(modifier = Modifier.padding(all = 5.dp))
                            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                                Text(text = "Birthday: INSERT BIRTHDAY")
                                Text(text = "Remark: INSERT REMARK")
                            }
                            VerticalDivider(modifier = Modifier.padding(all = 5.dp))
                            Button(
                                onClick = {/*TO DO*/ },
                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                    containerColor = androidx.compose.ui.graphics.Color.Red
                                )
                            ) {
                                Text(
                                    "DELETE",
                                    color = androidx.compose.ui.graphics.Color.White,

                                    )
                            }

                        }
                    }
                }
            }


        }


    }
}