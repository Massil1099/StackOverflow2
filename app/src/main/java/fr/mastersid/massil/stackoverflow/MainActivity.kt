package fr.mastersid.massil.stackoverflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.massil.stackoverflow.db.Question
import fr.mastersid.massil.stackoverflow.ui.theme.StackOverflowTheme
import fr.mastersid.massil.stackoverflow.viewmodel.QuestionsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowTheme {
                QuestionsScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun QuestionsScreen(modifier: Modifier = Modifier, questionsViewModel: QuestionsViewModel = viewModel()) {
    val questionsList by questionsViewModel.questionsList.observeAsState(emptyList())
    val isUpdating by questionsViewModel.isUpdating.observeAsState(false)
    val errorMessage by questionsViewModel.errorMessage.observeAsState(null)
    var sortByQuestionWithNoResponse by rememberSaveable { mutableStateOf(false) }

    val displayedQuestions = if (sortByQuestionWithNoResponse) {
        questionsList.filter { it.answerCount == 0 }
    } else {
        questionsList
    }


    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage){
        errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            questionsViewModel.clearError()
        }
    }


    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { questionsViewModel.updateQuestions() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_refresh_24),
                    contentDescription = stringResource(id = R.string.refresh_button_content_description)
                )
            }
        },
        bottomBar = {
            SortByQuestionWithNoResponseSwitch(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                sortByQuestionWithNoResponse = sortByQuestionWithNoResponse,
                onChange = { sortByQuestionWithNoResponse = it }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (isUpdating) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(displayedQuestions) { question ->
                    QuestionRow(question)
                }
            }
        }
    }
}

@Composable
fun QuestionRow(question: Question) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = question.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = question.body ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = stringResource(id = R.string.answerCount, question.answerCount),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun SortByQuestionWithNoResponseSwitch(modifier: Modifier, sortByQuestionWithNoResponse: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Switch(
            checked = sortByQuestionWithNoResponse,
            onCheckedChange = onChange
        )
        Text(stringResource(id = R.string.sort_by_question_with_no_response))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StackOverflowTheme {
        Greeting("Android")
    }
}

@Preview(widthDp = 400)
@Composable
fun QuestionRowPreview() {
    StackOverflowTheme {
        QuestionRow(Question(1, "StackOverflow first question ", 10, body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"))
    }
}

@Preview(widthDp = 400)
@Composable
fun SortByQuestionWithNoResponseSwitchPreview() {
    StackOverflowTheme {
        SortByQuestionWithNoResponseSwitch(modifier = Modifier.padding(horizontal = 16.dp), sortByQuestionWithNoResponse = true) {}
    }
}
