package com.example.weatherapp

import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherScreen()
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#59469d")),
                        Color(android.graphics.Color.parseColor("#643d67"))
                    )
                )
            )
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Parcialmente Nublado",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 48.dp),
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(id = R.drawable.cloudy_sunny),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(top = 8.dp)
                    )
                    // mostrando data e hora
                    Text(
                        text = "Seg Outubro 07 | 8:00 AM",
                        fontSize = 19.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    // mostrando temperatura
                    Text(
                        text = "20°",
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "H:27 L:18",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .background(
                                color = colorResource(id = R.color.purple),
                                shape = RoundedCornerShape(25.dp)
                            )

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            WeatherDetailItem(
                                icon = R.drawable.rain,
                                value = "30%",
                                label = "Chuva"
                            )
                            WeatherDetailItem(
                                icon = R.drawable.wind,
                                value = "15km/h",
                                label = "Velocidade do vento"
                            )
                            WeatherDetailItem(
                                icon = R.drawable.humidity,
                                value = "20%",
                                label = "Humidade"
                            )
                        }
                    }
                    Text(
                        text = "Hoje",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp)
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(items) { item ->
                            FutureModelViewHolder(item)
                        }
                    }
                }
                // mostrando os próximos 7 dias de previsão
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Futuro",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Próximos 7 dias>",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )


                    }
                }
                items(dailyItems) {
                    FutureItem(it)
                }
            }
        }
    }
}

@Composable
fun FutureItem(item: FutureModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.day,
            color = Color.White,
            fontSize = 14.sp
        )
        Image(
            painter = painterResource(id = getDrawableResourceId(picPath = item.picPath)),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 32.dp)
                .size(45.dp)
        )
        Text(
            text = item.status, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "${item.highTemp}°",
            modifier = Modifier.padding(end = 16.dp),
            color = Color.White,
            fontSize = 14.sp

        )
        Text(
            text = "${item.lowTemp}°",
            color = Color.White,
            fontSize = 14.sp

        )


    }
}

@Composable
fun getDrawableResourceId(picPath: String): Int {
    return when (picPath) {
        "nublado" -> R.drawable.cloudy
        "sol" -> R.drawable.sunny
        "ventania" -> R.drawable.wind
        "parcialmente nublado" -> R.drawable.cloudy_sunny
        "chuva" -> R.drawable.rain
        "tempestade" -> R.drawable.storm
        else -> R.drawable.sunny
    }
}

val dailyItems = listOf(
    FutureModel("Seg", "nublado", "", 20, 5),
    FutureModel("Ter", "sol", "", 25, 10),
    FutureModel("Qua", "Chuva", "", 17, 10),
    FutureModel("Qui", "ventania", "", 20, 10),
    FutureModel("Sex", "Sol", "", 25, 15),
    FutureModel("Sab", "Sol", "", 31, 18),
    FutureModel("Dom", "Sol", "", 30, 18)
)

@Composable
fun FutureModelViewHolder(model: HourlyModel) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight()
            .padding(4.dp)
            .background(
                color = colorResource(id = R.color.purple),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = model.hour,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(
                id = when (model.picPath) {
                    "nublado" -> R.drawable.cloudy
                    "Sol" -> R.drawable.sunny
                    "ventania" -> R.drawable.wind
                    "chuva" -> R.drawable.rain
                    "tempestade" -> R.drawable.storm
                    else -> R.drawable.sunny
                }
            ), contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "${model.temp}°",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

    }
}


val items = listOf(
    HourlyModel("5pm", 10, "nublado"),
    HourlyModel("12pm", 30, "sol"),
    HourlyModel("13pm", 31, "ventania"),
    HourlyModel("16pm", 31, "chuva"),
    HourlyModel("17pm", 17, "Tempestade"),
)

@Composable
fun WeatherDetailItem(icon: Int, value: String, label: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon), contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center

        )
        Text(
            text = label,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center
        )
    }
}


