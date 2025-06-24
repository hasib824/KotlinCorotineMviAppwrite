package com.example.kotlincorotinemviappwrite.Presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlincorotinemviappwrite.ui.theme.KotlinCorotineMviAppwriteTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /*CoroutineScope(Dispatchers.Main).launch {

            flowTester()
        }*/

        setContent {
            KotlinCorotineMviAppwriteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

suspend fun flowTester()
{
    val myFlow = flowOf(10,15,20) // Flow is created but not yet active

    println("\nStarting to collect from the Flow...")
    // 2. Consumer: Collect values from the Flow
    myFlow.collect { value -> // 'collect' is a terminal operator that starts the flow
        println("Collected $value")
        Log.e("Flow : ", "flowTester: $value")
    }

    fun myFlower(x:Int) : Unit
    {
        Log.e("Listy F", x.toString())

    }

    myFlow.collect(::myFlower)

    suspend fun myMapper(x:Int) : Int
    {
        Log.e("Listy", x.toString())
        return x*2
    }

    val newOne = listOf<Int>(1, 2, 3, 4, 5).map( fun(x:Int) : Int { return x*2})
    Log.e("Listy", newOne.toString())

    val suspendingDouble: suspend (Int) -> Int = { it * 2 }

    flowOf(4, 2, 5, 1, 7).map(suspendingDouble)
        .collect {
            Log.d("Listy l", it.toString())
        }

    /*myFlow.map(myMapper).collect { result ->
        Log.d("FlowCollect", "Collected (anonymous suspend): $result")
    }
*/
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {


    val myViewModel : MyViewModel = viewModel()

    var mutableStateOf by remember { mutableStateOf("bangladesh") }

    val collectAsState by myViewModel._state.collectAsState()

    // val listOf = MutableFloatList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val context = LocalContext.current // ✅ Safe here

    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.Gray)
        .padding(16.dp)) {

        Text(text = "Type country name", color = Color.White, modifier = modifier.fillMaxWidth().background(Color.DarkGray).padding(16.dp), fontSize = 20.sp)
        TextField(value = mutableStateOf, onValueChange = { x->  mutableStateOf = x  },  modifier=modifier
            .fillMaxWidth()
            .background(Color.White) )
        Button( onClick = {

            myViewModel.dispatch(CountryIntent.getCountryByName(mutableStateOf))
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()

        }) {
            Text(text = "Submit")
        }

        if(collectAsState.isLoading)
        {
            CircularProgressIndicator()
        }
        else if(collectAsState.error != null)
        {
            Text(text = collectAsState.error.toString(), Modifier.fillMaxWidth().height(40.dp).background(Color.LightGray), color = Color.Red)
        }
        else{
            Text(text = collectAsState.country?.name?.common.toString(), Modifier.fillMaxWidth().height(64.dp).clip(
                RoundedCornerShape(16.dp)
            ).background(Color.LightGray).padding(16.dp), color = Color.Red)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = collectAsState.country?.capital.toString(), Modifier.fillMaxWidth().height(64.dp).background(Color.LightGray).padding(16.dp), color = Color.Red)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = collectAsState.country?.region.toString(), Modifier.fillMaxWidth().height(64.dp).background(Color.LightGray).padding(16.dp), color = Color.Red)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinCorotineMviAppwriteTheme {
        Greeting("Android")
    }
}