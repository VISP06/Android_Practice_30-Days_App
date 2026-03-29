package com.example.a30days

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthlyTipGrid(tipList:List<Tip>, modifier:Modifier = Modifier){
    var selectedTip by remember {mutableStateOf<Tip?>(null)}
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(
            tipList
        ) { tip ->
            Image(
                painter = painterResource(tip.imageRes),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {
                        selectedTip = tip
                    }
            )
        }
    }
    if(selectedTip != null) {
        ModalBottomSheet(
            onDismissRequest = {
                selectedTip = null //this used to handle closing the bottom sheet when the user
                // does literally anything else other than clicking the close button to try and close the bottom sheet
            }
        ) {
            Column() {
                Row(){
                    Text(text = stringResource(selectedTip!!.title))
                    Spacer(Modifier.weight(1f))
                    Button(onClick = {
                        selectedTip = null
                    }
                    ) {
                        Text("Close")
                    }
                }
                Text(text = stringResource(selectedTip!!.desc))
            }

        }
    }
}