package com.example.a30days

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30days.model.Tip
import com.example.a30days.model.TipRepository
import com.example.compose._30DaysTheme

@Preview
@Composable
fun DaysApp(){
    _30DaysTheme{
        MonthlyTipGrid(TipRepository.tipList)
    }
}

@Composable
fun MonthlyTipGrid(tipList:List<Tip>, modifier:Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(
            tipList
        ){ tip->
            Image(
                painter = painterResource(tip.imageRes),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}