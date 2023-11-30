package me.szydelko.myapplication

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.runBlocking
import me.szydelko.myapplication.dto.Info
import me.szydelko.myapplication.dto.JsonPle
import me.szydelko.myapplication.dto.Le
import me.szydelko.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(InternalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyApplicationTheme {
                var t by remember {
                    mutableStateOf<Le>(Le(
                        mapOf("1" to "x", "2" to "y", "-1" to "zz"),
                        mapOf("1" to "x", "2" to "y", "-1" to "zz"),
                        Info("","","","","")
                    ))
                }

//                var r by remember {
//                    mutableStateOf<JsonPle>(
//                        JsonPle(
//                        true,1,"tak",2
//                    )
//                    )
//                }
                // A surface container using the 'background' color from the theme
                 runBlocking {
                            val client = HttpClient(Android) {
                                install(ContentNegotiation) {
                                    json()
                                }
                            }

                            t = client.get("http://zasob.itmargen.com/4TR/").body<Le>()
//                     println("jooooooooooo");
//                     val o:String = client.get("http://zasob.itmargen.com/4TR/"){
//                                headers {
//                                    append(HttpHeaders.Accept, "text/html")
//                                }
//                            }.body()
//
//                     println(o);
//                             r = client.get("https://jsonplaceholder.typicode.com/todos/1").body<JsonPle>();
                            client.close();
                        }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    InfoCard(info = t.info)
                    Spacer(modifier = Modifier.height(16.dp))
                    GroupCard(title = "G1", items = t.grupa1)
                    Spacer(modifier = Modifier.height(16.dp))
                    GroupCard(title = "G2", items = t.grupa2)
                }
                }



            }
        }
    }

    @Composable
    fun InfoCard(info: Info) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                SectionHeader("INFO:")
                InfoItem("Miasto", info.miasto)
                InfoItem("Szkola", info.szkola)
                InfoItem("Data i czas", info.dataczas)
                InfoItem("Przedmiot", info.przedmiot)
                InfoItem("Prowadzacy", info.prowadzacy)
            }
        }
    }

    @Composable
    fun GroupCard(title: String, items: Map<String, String>) {
        Card (
            modifier = Modifier.fillMaxWidth(),
        ){
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                SectionHeader(title)
                items.forEach { (key, value) ->
                    InfoItem(label = key, value = value)
                }
            }
        }
    }

    @Composable
    fun SectionHeader(title: String) {
        Text(text = title, fontWeight = FontWeight.Bold)
    }

    @Composable
    fun InfoItem(label: String, value: String) {
        Row {
            Text(text = "$label: ",fontWeight = FontWeight.Bold)
            Text(value)
        }

    }

}



