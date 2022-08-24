package com.example.news.presentation.ui.screens

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.example.news.presentation.ui.main.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                viewModel.newsQuery = ""
            },
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = null
            )
        }
    }
    OutlinedTextField(
        modifier = modifier,
        value = viewModel.newsQuery,
        placeholder = {
            Text("Search for any news...")
        },
        trailingIcon = if (viewModel.newsQuery.isNotBlank()) trailingIconView else null,
        singleLine = true,
        onValueChange = {
            viewModel.newsQuery = it
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            viewModel.querySearch()
        })
    )
}
