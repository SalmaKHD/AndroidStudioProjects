package com.salmakhd.android.courselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salmakhd.android.courselist.datasource.DataSource
import com.salmakhd.android.courselist.model.CourseTopic
import com.salmakhd.android.courselist.ui.theme.CourseListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CourseListTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    TopicsGrid(courses = DataSource.topics)
                }

            }
        }
    }
}

@Composable
fun TopicsGrid(
    courses: List<CourseTopic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        // padding for the entire grid itself
        contentPadding = PaddingValues(8.dp),
        // padding for items in the grid
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courses.size) {index ->
            TopicGridItem(
                courseImageId = courses[index].imageResourceId,
                courseItemNumber = courses[index].numberOfItems,
                courseTopicId = courses[index].nameStringId
            )
        }
    }
}

@Preview
@Composable
fun TopicGridItemPreview() {
    val sampleCourseTopic = CourseTopic(
        nameStringId = R.string.architecture,
        numberOfItems = 58,
        imageResourceId = R.drawable.architecture)

    TopicGridItem(
        courseImageId = sampleCourseTopic.imageResourceId,
        courseItemNumber = sampleCourseTopic.numberOfItems,
        courseTopicId = sampleCourseTopic.nameStringId)
}

@Composable
fun TopicGridItem(
    courseImageId: Int,
    courseItemNumber: Int,
    courseTopicId: Int,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(68.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = courseImageId),
                contentDescription = stringResource(id = courseTopicId)
            )

            Column {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end= 16.dp, top= 16.dp),
                    text = stringResource(id = courseTopicId),
                    style = MaterialTheme.typography.body2
                )

                Row (
                    modifier = Modifier
                        .padding(start=16.dp, end=16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = courseItemNumber.toString(),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}
