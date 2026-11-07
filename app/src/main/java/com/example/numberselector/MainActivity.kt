
package com.example.numberselector
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity: ComponentActivity(){override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState);setContent{App()}}}
@Composable
fun App(){
var selected by remember{mutableStateOf(1)}
var delaySec by remember{mutableStateOf("3")}
var remaining by remember{mutableStateOf(3)}
LaunchedEffect(delaySec){ val s=delaySec.toIntOrNull()?:3; remaining=s; while(true){ delay(1000); remaining--; if(remaining<=0){ selected=(1..6).random(); remaining=s } } }
Column(Modifier.fillMaxSize().padding(12.dp), horizontalAlignment=Alignment.CenterHorizontally){
Text("SET 1 / 3", fontSize=24.sp)
OutlinedTextField(value=delaySec,onValueChange={delaySec=it},label={Text("Seconds")},keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Number))
Spacer(Modifier.height(8.dp))
for(r in 0..2){Row{ Cell(r+1,selected); Cell(r+4,selected)}}
Text(String.format("0:%02d", remaining), fontSize=28.sp)
}
}
@Composable fun Cell(n:Int,s:Int){Box(Modifier.size(150.dp).padding(2.dp).background(if(n==s) Color(0xFFFFC107) else Color(0xFF006B3C)), contentAlignment=Alignment.Center){Text("$n", fontSize=48.sp,color=Color.White)}}
