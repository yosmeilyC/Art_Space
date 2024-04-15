package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artspace.data.DataSource
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      ArtSpaceTheme {
        NavHost(
          navController = navController, startDestination = Screen.Home.route + "/{id}"
        ) {
          composable(
            Screen.Home.route + "/{id}", arguments = listOf(navArgument("id") {
              type = NavType.IntType
              defaultValue = 0
            })
          ) {
            HomePage(navController)
          }
          composable(
            Screen.Artist.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
          ) {
            ArtistPage(navController)
          }
        }
      }
    }
  }
}

@Composable
fun ArtistProfile(imageId: Int,nameId: Int, infoId: Int,modifier: Modifier = Modifier){

  Row (
    modifier = Modifier.padding(20.dp),
    horizontalArrangement =  Arrangement.spacedBy(10.dp,Alignment.CenterHorizontally)
  ){

    Image(
      modifier = Modifier
        .size(150.dp)
        .border(
          BorderStroke(3.dp, Color(0xFF385d7e)),
          CircleShape
        )
        .padding(7.dp)
        .clip(CircleShape),

      //.align(alignment = Alignment.End)
      contentScale = ContentScale.Crop,
      painter = painterResource(id = imageId),
      contentDescription = stringResource(id = nameId)
    )

    Column(

      verticalArrangement = Arrangement.Center,

      modifier = modifier.padding(start = 10.dp, end = 10.dp,top=40.dp,bottom=40.dp))
    {
      Text(
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        text = stringResource
          (id = nameId))
      Text(
        fontSize = 13.sp,
        text = stringResource(id = infoId))
    }
  }
}


@Composable
fun ArtistBio(artistBio: Int){

  Text(
    text = stringResource (id =artistBio),
    modifier = Modifier
      .padding(16.dp),
     // .verticalScroll(rememberScrollState()),
    textAlign = TextAlign.Justify
  )


}

@Composable
fun ArtistPage(navController: NavController) {
  val id = navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
  val art = DataSource.arts[id]

  // ARTIST PAGE section A
  // TODO: 1. Artist Profile including image, name, and info (birthplace, and years alive)
Column{
  ArtistProfile(imageId = art.artistImageId, nameId = art.artistId, infoId = art.artistInfoId)
  // ARTIST PAGE section B
  // TODO: 2  Artist bio

  LazyColumn {
    item {
      ArtistBio(artistBio = art.artistBioId)


      // DO NOT MODIFY THE FOLLOWING CODE
      // You can use the following code to navigate to the previous screen:
      // ARTIST PAGE section C
      // TODO: 3 place the code below in the proper Row or Column layout
      Row(
        horizontalArrangement = Arrangement.Start
      ) {
        Button(
          modifier = Modifier.padding(20.dp),
          onClick = {
            navController.navigate(Screen.Home.route + "/$id")
          }) {
          Text(
            text = stringResource

              (id = R.string.back)
          )
        }}
      }
    }
  }
}






@Composable
fun ArtWall(
  artistId: Int,
  artImageId: Int,
  artDescriptionId: Int,
  navController: NavController,
) {

  // TODO: 4. Add image of artwork


  // TODO: 5. Add a click listener to navigate to the artist page

  Box(modifier = Modifier.clickable {
    navController.navigate(Screen.Artist.route + "/$artistId")
  }) {

    Image(
    painter = painterResource(id = artImageId),
   contentDescription = stringResource(id = artDescriptionId)
 )
  }





}

@Composable
fun ArtDescriptor(artTitleId: Int, artistId: Int, artYearId: Int,modifier: Modifier = Modifier) {

  // HOME PAGE section B

  // TODO: 6. Add title of artwork


  Column(

    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.padding(horizontal=100.dp ),

    )
  {
    Text(

      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
//        modifier = Modifier
//          .padding(7.dp),

      text = stringResource

        (id = artTitleId)
    )

    Spacer(modifier = Modifier.height(12.dp))
    // TODO: 7. Add artist name and year of artwork
    Row(modifier = modifier,horizontalArrangement = Arrangement.spacedBy(7.dp)) {


      Text(
        fontSize = 15.sp,
        text = stringResource

          (id = artistId)

      )
      Text(
        fontSize = 15.sp,
        text = stringResource

          (id = artYearId)
      )
    }


  }
  // <--- Safely REMOVE the following code and ADD your code here --->
  //Text(text = "(B) Display Artwork Title, Artist Name and Year here as per the design")
}



@Composable
fun DisplayController(current: Int, modifier: Modifier = Modifier,move: (Int) -> Unit ) {

  // HOME PAGE section C

  // TODO: 9. Add a button to navigate to the previous artwork

  Row(
   modifier = modifier.padding(horizontal=60.dp,vertical=10.dp),
    horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
  ){

    Button(
      enabled = current != 0,
      onClick = { move(current-1)}
    ) {
      Text(
      modifier = modifier
        .padding(horizontal=12.dp),
        text = "Previous"
      )
    }

    // TODO: 10. Add a button to navigate to the next artwork

    Button(
      enabled = current != 4,
      onClick = {

        move(current+1) })
    {
      Text(
        modifier = modifier
          .padding(horizontal=20.dp),
        text = "Next"


      )}
  }//}
  // NOTE:
  // The buttons should be disabled if there is no previous or next artwork to navigate to
  // You can use the following code to disable the button:
  //  enabled = current != 0 // for the previous button

  // You can use the following code to navigate to the previous or next artwork:
  // move(current - 1) // for the previous button
  // move(current + 1) // for the next button

  // <--- Safely REMOVE the following code and ADD your code here --->


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
  var current by remember {
    mutableIntStateOf(
      navController.currentBackStackEntry?.arguments?.getInt(
        "id"
      ) ?: 0
    )
  }
  val art = DataSource.arts[current]

  Scaffold(topBar = {
    CenterAlignedTopAppBar(
      title = { Text(text = stringResource(id = R.string.app_name)) },
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
      )
    )
  }) { innerPadding ->
    /**
     *The children without weight (a) are measured first. After that, the remaining space in the column
     * is spread among the children with weights (b), proportional to their weight. If you have 2
     * children with weight 1f, each will take half the remaining space.
     */
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(innerPadding)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f) // children with weight (b)
      ) {

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_extra_large)))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          ArtWall(current, art.artworkImageId, art.descriptionId, navController)
        }
      }
      // (a) children without weight
      ArtDescriptor(art.titleId, art.artistId, art.yearId)
      DisplayController(current) {
        current = if (it !in 0..<DataSource.arts.size) 0 else it
      }
    }
  }
}



@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
  ArtSpaceTheme {
    HomePage(rememberNavController())
  }
}
