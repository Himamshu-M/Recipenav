package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel: ViewModel(){
    private val _categoriesState= mutableStateOf(RecipeState())//abstraction
    val categoriesState: State<RecipeState> = _categoriesState

    init{
        fetchCat()
    }
    private fun fetchCat(){
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()
                _categoriesState.value=_categoriesState.value.copy(
                    listCat = response.categories,
                    loading= false,
                    errorCheck = null
                )
            }catch (e: Exception){
                _categoriesState.value=_categoriesState.value.copy(
                    loading=false,
                    errorCheck = "Error fetching Categories${e.message}"
                )
            }
        }
    }
    data class RecipeState(
        val loading: Boolean=true,
        val listCat : List<Category> = emptyList(),
        val errorCheck :String?=null
    )
}
