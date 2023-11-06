package com.deliter.cookingapp.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.deliter.cookingapp.R
import com.deliter.cookingapp.data.Dish
import com.deliter.cookingapp.data.DishRepository
import com.deliter.cookingapp.data.MenuItems
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookingApp(
    modifier: Modifier = Modifier
) {
    val items = MenuItems.menuList
    val dishItems = DishRepository.dishList

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(items[0]) }
    val title = remember { mutableStateOf("CookingApp") }
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(R.drawable.drawer_header),
                    contentDescription = "Header",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                items.forEach { item ->
                    NavigationDrawerItem(

                        selected = selectedItem == item,
                        icon = {
                            Icon(imageVector = item.icon,
                                contentDescription = item.contentDescription
                            ) },
                        label = { Text(text = item.title)  },
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
                            title.value = item.title
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title.value) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                )
            }
        ) {
            CookingList(currentSelectedRecipe = dishItems, modifier = modifier.padding(it))
        }
    }
}

@Composable
private fun CookingList(
    currentSelectedRecipe: List<Dish>,
    modifier: Modifier = Modifier
) {



    LazyColumn(modifier = modifier ) {
        itemsIndexed(currentSelectedRecipe) { index, dish ->

            var expanded by remember { mutableStateOf(false) }
            var favorite by remember { mutableStateOf(false) }

            Card(modifier = Modifier.padding(3.dp)) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(dish.dishName),
                            modifier = Modifier.padding(5.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        if (!expanded) {
                            Image(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp)
                                    .clip(shape = RoundedCornerShape(size = 20.dp))
                                    .padding(vertical = 5.dp),
                                painter = painterResource(dish.dishImage),
                                contentDescription = "Dish image",
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            IconToggleButton(
                                checked = favorite,
                                onCheckedChange = { favorite = !favorite }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite Item",
                                    tint = if (favorite) Color.Magenta else Color.LightGray
                                )
                            }
                        }

                        IconButton(
                            onClick = { expanded = !expanded },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            Icon(
                                imageVector = if (expanded) Icons.Filled.ExpandLess
                                else Icons.Filled.ExpandMore,
                                contentDescription = stringResource(R.string.share),
                            )
                        }
                    }

                    if (expanded) {
                        Image(
                            painter = painterResource(dish.dishImage),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
            }
        }
    }
}




