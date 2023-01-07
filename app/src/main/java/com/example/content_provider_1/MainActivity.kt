package com.example.content_provider_1

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {

    lateinit var btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
    }

    @SuppressLint("Range")
    override fun onStart() {
        super.onStart()

        btn.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this, arrayOf<String>(android.Manifest.permission.READ_CONTACTS),0)

        }
            var contentResolver = contentResolver
            var uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            var cursor = contentResolver.query(uri,null,null,null,null)

            if(cursor!!.count > 0 ){
                while (cursor.moveToNext()){
                    var contactName = cursor.getString(cursor.getColumnIndex
                        (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    var contactNumber = cursor.getString(cursor.getColumnIndex
                        (ContactsContract.CommonDataKinds.Phone.NUMBER));

                    Log.i("CONTACT_PROVIDER_DEMO","Contact Name" +contactName + "Ph #"+contactNumber);
                }
            }
        }
    }

}