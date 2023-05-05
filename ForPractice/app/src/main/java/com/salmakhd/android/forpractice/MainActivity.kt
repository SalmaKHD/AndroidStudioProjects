package com.salmakhd.android.forpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salmakhd.android.forpractice.ui.theme.ForPracticeTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // launch a coroutine and run two operations in parallel
        // not efficient due to sequential execution
        CoroutineScope(Dispatchers.IO).launch {
            val totalStockValue = getStock1() + getStock2()
            Log.i("MAIN_ACTIVITY", "The total value of stocks is: $totalStockValue")
        }

        // more efficient solution: run coroutines using async{}
        val job = CoroutineScope(Dispatchers.IO).launch {
            val totalStockValue = async{getStock1()}.await() + async{getStock2()}.await()
            // execution time down by 8 seconds
            Log.i("MAIN_ACTIVITY", "The total value of stocks is: $totalStockValue, async used.")
        }
        // show the status of the top coroutine every one second
            for (i in 1 .. 10) {
                Log.i("MAIN_ACTIVITY", "The status of the stock coroutines is: ${job.isActive}")

            }

        setContent {
            ForPracticeTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // will be incremented with each second button click
                    var count by remember {
                        mutableStateOf(0)
                    }

                    // simulates user data being retrieved from the network
                    var userData by remember { mutableStateOf(0) }

                    Text(text= userData.toString(), modifier= Modifier.padding(top=100.dp))

                    Button(modifier = Modifier.padding(top=100.dp), onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            for (i in 1 .. 200000) {
                                    userData++
                            }
                            // add a delay of 2 seconds
                            delay(2000)
                        }
                    }
                    ) {
                        Text("Download User Data")
                    }

                    Spacer(Modifier.height(200.dp))

                    Text(text=count.toString())

                    Button(onClick = {count++}) {
                        Text(text="Increase Count")
                    }
                }
            }
        }
    }

    // define a suspending function
    private suspend fun getStock1(): Int {
        delay(8000)
        Log.i("MAIN_ACTIVITY", "stock 1 returned.")
        return 5000
    }


    // define another suspending function
    private suspend fun getStock2(): Int {
        delay(10000)
        Log.i("MAIN_ACTIVITY", "stock 2 returned.")
        return 3500
    }

    // create a suspending function that creates a child CoroutineScope
    private suspend fun getTotalUserCount(): Int {
        // the function will not return before all child coroutines have completed their execution
        coroutineScope {
            launch {

            }
        }
    }
}