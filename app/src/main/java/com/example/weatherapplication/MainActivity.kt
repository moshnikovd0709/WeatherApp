package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var call: Call<DailyForecasts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            temp.text = savedInstanceState.getString(getString(R.string.temp))
        }
        run()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(getString(R.string.temp), temp.text.toString())
        outState.putString(getString(R.string.rft), rft.text.toString())
        outState.putString(getString(R.string.prec), prec.text.toString())
        outState.putString(getString(R.string.wind), wind.text.toString())
        call.cancel()
    }

    private fun run() {
        call = MyApp.app.weatherApi.getRepos("294021", BuildConfig.ApiKey)
        call?.enqueue(object : Callback<DailyForecasts> {
            override fun onFailure(call: Call<DailyForecasts>, t: Throwable) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Connection troubles", Toast.LENGTH_SHORT
                )
                toast.show()
                call.cancel()
            }

            override fun onResponse(
                call: Call<DailyForecasts>,
                response: Response<DailyForecasts>
            ) {
                val body = response.body()
                if (body != null) {
                    temp.text =
                        getString(R.string.format, body.dailyForecasts[0].temperature.min.value)
                    rft.text =
                        getString(R.string.format, body.dailyForecasts[0].rftemperature.min.value)
                    prec.text = getString(R.string.format, body.dailyForecasts[0].day.precipitation)
                    wind.text =
                        getString(R.string.format, body.dailyForecasts[0].day.wind.speed.value)
                }
            }
        })
    }
}
