package com.example.contador

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Joven (val uniqueId: Int,
             val name: String,
             val lastName: String,
             val sex: String,
             val phone: String,
             val email: String,
             val day: Int,
             val month: Int,
             val year: Int,
             val school: String,
             val insuranceCode: Int,
             val bloodType: String,
             val passports: String,
             val destination: String,
             val fatherName: String,
             val fatherPhone: String,
             val motherName: String,
             val motherPhone: String) : Serializable{

    companion object {

        fun getJovenesFromFile(filename: String, context: Context): ArrayList<Joven> {
            val jovenesList = ArrayList<Joven>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset(filename, context)
                val json = JSONObject(jsonString)
                val recipes = json.getJSONArray("Jovenes")

                // Get Recipe objects from data
                (0 until recipes.length()).mapTo(jovenesList) {
                    Joven(recipes.getJSONObject(it).getInt("id"),
                        recipes.getJSONObject(it).getString("Nombre"),
                        recipes.getJSONObject(it).getString("Apellido"),
                        recipes.getJSONObject(it).getString("Sexo"),
                        recipes.getJSONObject(it).getString("Celular"),
                        recipes.getJSONObject(it).getString("Email"),
                        recipes.getJSONObject(it).getInt("Dia"),
                        recipes.getJSONObject(it).getInt("Mes"),
                        recipes.getJSONObject(it).getInt("Year"),
                        recipes.getJSONObject(it).getString("Colegio"),
                        recipes.getJSONObject(it).getInt("Numero Seguro"),
                        recipes.getJSONObject(it).getString("Tipo de Sangre"),
                        recipes.getJSONObject(it).getString("Pasaportes"),
                        recipes.getJSONObject(it).getString("Destino"),
                        recipes.getJSONObject(it).getString("Nombre Padre"),
                        recipes.getJSONObject(it).getString("Celular Padre"),
                        recipes.getJSONObject(it).getString("Madre"),
                        recipes.getJSONObject(it).getString("Celular Madre"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return jovenesList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }

}