package com.example.vehichleslist.ui.viewmodel

import androidx.lifecycle.*
import com.example.vehichleslist.data.CarsDao
import com.example.vehichleslist.model.Cars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarsViewModel(
    private val carsDao: CarsDao
): ViewModel() {

    val cars: LiveData<List<Cars>> = carsDao.getCars().asLiveData()
    fun getCars(id: Long): LiveData<Cars> {
        return carsDao.getCars(id).asLiveData()
    }


    fun addCars(
        name: String,
        model: String,
        age: String,
        type: String,
        price: String
    ) {
        val cars = Cars(
            name = name,
            model = model,
            age = age,
            type = type,
            price = price
        )

        viewModelScope.launch(Dispatchers.IO){
            carsDao.insert(cars)
        }

    }

    fun updateCars(
        id: Long,
        name: String,
        model: String,
        age: String,
        type: String,
        price: String
    ) {
        val cars = Cars(
            id = id,
            name = name,
            model = model,
            age = age,
            type = type,
            price = price
        )
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.update(cars)
        }
    }

    fun deleteCars(cars: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carsDao.delete(cars)
        }
    }

    fun isValidEntry(name: String, model: String): Boolean {
        return name.isNotBlank() && model.isNotBlank()
    }

}

class CarsViewModelFactory(private val carsDao: CarsDao): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(CarsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CarsViewModel(carsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}