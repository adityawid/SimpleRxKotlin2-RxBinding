package com.devjurnal.simplerxkotlin2xml

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ada 3 pilihan ".just" , ".fromarray" , ".create"

        /**
         * Cara 1
         */
        /*Observable.create<String> { e ->

            // Todo on object -> alt Enter jika object merah untuk implemen method
            edtInputan.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    e.onNext(s.toString())
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun afterTextChanged(s: Editable?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        } .debounce(1,TimeUnit.SECONDS)         *//** Timmer delay *//*
                .subscribeOn(Schedulers.newThread())    // Management thread , create new thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: String? -> tvOutput.text = t }*/


        /**
         * Cara 2 dengan RxBinding
         */
        // TODO add RXbinding to gradle
        RxTextView.textChanges(edtInputan)
                .map { t ->  t.toString()}
                .debounce(1,TimeUnit.SECONDS)         /** Timmer delay */
                .subscribeOn(Schedulers.newThread())    // Management thread , create new thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: String? ->
                    tvOutput.text = t }

    }
}
