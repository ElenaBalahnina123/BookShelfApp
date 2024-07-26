package com.example.bookshelf

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookshelf.component.ExpandableText
import com.example.bookshelf.component.GradientButton
import com.example.bookshelf.component.Search
import com.example.bookshelf.domain.Book
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookShelfScreen(
    bookShelfScreenStateFlow: Flow<List<Book>>,
    onQueryChanged: (TextFieldValue) -> Unit,
    queryFlow: Flow<TextFieldValue>,
    onClickSearch: () -> Unit = {},
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Search(
                flow = queryFlow,
                onSearchChanged = onQueryChanged
            )
            GradientButton(
                gradientColors = listOf(
                    colorResource(id = R.color.light_yellow),
                    colorResource(id = R.color.red)
                ),
                text = "Искать",
                onClick = onClickSearch,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.CenterHorizontally)
            )
            val bookShelf by bookShelfScreenStateFlow.collectAsState(emptyList())
            LazyColumn {
                items(bookShelf) { book ->
                    BookItem(book = book)
                }
            }
        }

    }

}

class BookShelfScreenPreviewParameter : PreviewParameterProvider<Book> {
    override val values: Sequence<Book>
        get() = sequenceOf(
            Book(
                imgBook = null,
                title = "Королевство",
                previewLink = "http://books.google.ru/books?id=KjJREAAAQBAJ&printsec=frontcover&dq=%D1%81%D0%B0%D1%80%D0%B0%D0%BC%D0%B0%D0%B0%D1%81&hl=&cd=1&source=gbs_api",
                description = "Неста, сестра Фейры, Верховной правительницы Двора ночи, после погружения в древний источник, содержащий первооснову жизни, обретает силу, непонятную и неподвластную ей самой. Она не знает, как справиться с этим даром, но мудрые родственники наставляют ее на путь истинный и отправляют в Дом ветра осваивать древнее воинское искусство валькирий, истребленных полностью в сражениях прошлого. Тем временем Бриаллина, королева людей, вступает в союз с Косфеем, существом, неподвластным смерти. Она завладевает Короной — одним из трех магических артефактов из Сокровищницы ужасов, — и способна теперь подчинить своей воле любого смертного. Чтобы ей противостоять, нужно найти два других артефакта древности – Арфу, способную преодолевать пространство и останавливать время, и Маску, способную воскрешать мертвых. Неста, наделенная силой и обученная искусству воина, готова выполнить эту миссию… На русском языке роман публикуется впервые!",
                author = listOf("Сара Маас", "Кто-то еще")
            )
        )

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
@Preview
fun BookItem(
    @PreviewParameter(BookShelfScreenPreviewParameter::class)
    book: Book
) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row {
            if (book.imgBook != null) {
                GlideImage(
                    model = book.imgBook,
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .aspectRatio(0.7f)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.book), contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .aspectRatio(0.7f)
                )
            }
            Column {
                Text(
                    text = book.title,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp),
                    maxLines = 2
                )
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    if (book.author != null) {
                        for (author in book.author) {
                            Text(
                                text = author,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 8.dp),
                                maxLines = 2,
                                color = colorResource(id = R.color.red)
                            )
                        }
                    }
                }


                if (book.description != null) {
                    ExpandableText(
                        text = book.description,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                if (book.previewLink != null) {
                    Button(
                        onClick =
                        { uriHandler.openUri(book.previewLink) },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red)),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(20)
                    ) {
                        Text(text = "Прочитать фрагмент", fontSize = 10.sp, fontWeight = FontWeight.W400, textAlign = TextAlign.Center)
                    }

                }

            }
        }
    }
}




