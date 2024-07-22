package com.example.bookshelf

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookshelf.domain.Book
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookShelfScreen(
    bookShelfScreenStateFlow: Flow<List<Book>>
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val bookShelf by bookShelfScreenStateFlow.collectAsState(emptyList())
                LazyColumn(
                    contentPadding = PaddingValues(4.dp)
                ) {
                    itemsIndexed(bookShelf) {_, book ->
                        BookItem(book = book)
                    }
            }

                }

        }

}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookItem(
   book: Book
   ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {

        Row {
            GlideImage(
                model = book.imgBook,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .aspectRatio(0.7f)
            )
            Text(text = book.title)
        }
    }
}



