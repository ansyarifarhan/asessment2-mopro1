
package com.ansyari0027.assesment2mobpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ansyari0027.assesment2mobpro1.navigation.SetupNavGraph
import com.ansyari0027.assesment2mobpro1.ui.theme.Assesment2Mobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment2Mobpro1Theme { SetupNavGraph()  }
        }
    }
}



