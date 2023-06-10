package com.example.localretrotest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.localretrotest.R
import com.example.localretrotest.model.User
import com.example.localretrotest.ui.utils.UserUiState

@Composable
fun HomeScreen(
    userUiState: UserUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {

    when(userUiState){
        is UserUiState.Loading -> LoadingScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(200.dp)
        )
        is UserUiState.Error -> ErrorScreen(retryAction = retryAction)
        is UserUiState.Success -> UserList(users = userUiState.users, modifier.fillMaxSize())
    }

}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier
    )
}


@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.loading_failed))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}


@Composable
fun UserList(users: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(
            users,
            key = {user ->
                user.name
            }
        ){user ->
            UserCard(user = user)
        }
    }
}


@Composable
fun UserCard(user: User, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = stringResource(id = R.string.name, user.name))
            Text(text = stringResource(id = R.string.age, user.age))
            Text(text = stringResource(id = R.string.phone, user.phone))
        }

    }
}