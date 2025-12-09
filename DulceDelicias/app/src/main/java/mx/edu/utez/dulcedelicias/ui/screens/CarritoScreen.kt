package mx.edu.utez.dulcedelicias.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.dulcedelicias.ui.screens.components.CarritoList

@Composable
fun CarritoScreen() {
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text="Total",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick={}) {
                    Text(text="Confirmar pedido")
                }
            }
        }
    ) { innerpadding ->
        CarritoList()
    }
}

@Preview (showBackground = true)
@Composable
fun CarritoScreenPreview(){
    CarritoScreen()
}