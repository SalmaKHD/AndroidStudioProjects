package com.bignerdranch.android.codapizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.bignerdranch.android.codapizza.model.Topping
import com.bignerdranch.android.codapizza.model.ToppingPlacement
import com.bignerdranch.android.codapizza.ui.ToppingCell

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToppingCell(
                topping = Topping.Pepperoni,
                placement = ToppingPlacement.Left,
                onClickTopping = {}
            )
        }
    }
}