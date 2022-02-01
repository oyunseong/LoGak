package com.oys.logak

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.oys.logak.base.BaseActivity
import com.oys.logak.databinding.ActivityMainBinding
import gun0912.ted.tedadmobdialog.OnBackPressListener

import gun0912.ted.tedadmobdialog.TedAdmobDialog
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    var nativeTedAdmobDialog: TedAdmobDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val android_id: String =
//            Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
//        val deviceId = md5(android_id).toUpperCase()
//        Log.v("TAG", "is Admob Test Device ? $deviceId") //to confirm it worked


        // 삼성폰 B6046CCCC3E12F59B3D241E87EE99842
        // 애뮬레이터 E8BB89F8382E0F48617270ACF53AD6F6
        val testDeviceIds = listOf("B6046CCCC3E12F59B3D241E87EE99842")
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        MobileAds.setRequestConfiguration(configuration)

        nativeTedAdmobDialog =
            TedAdmobDialog.Builder(
                this,
                TedAdmobDialog.AdType.NATIVE,
                getString(R.string.admob_native_unit_id)
//                getString(R.string.test_admob_id)
            ) //네이티브 광고 unit id
                .setOnBackPressListener(object : OnBackPressListener {
                    override fun onReviewClick() {
                        //play store 링크로 이동시키기
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        intent.data = Uri.parse("market://details?id=$packageName")
                        startActivity(intent)
                    }

                    override fun onFinish() {
                        finish()
                    }

                    override fun onAdShow() {
                        nativeTedAdmobDialog?.loadNative()
                    }
                })
                .create()

        nativeTedAdmobDialog?.loadNative()


        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        binding.navView.setupWithNavController(navController)
    }

    fun showAdmobDialog() {
        nativeTedAdmobDialog?.show();
    }

    override fun onBackPressed() {
        showAdmobDialog()
    }

//    fun md5(s: String): String {
//        try {
//            // Create MD5 Hash
//            val digest: MessageDigest = MessageDigest
//                .getInstance("MD5")
//            digest.update(s.toByteArray())
//            val messageDigest: ByteArray = digest.digest()
//
//            // Create Hex String
//            val hexString = StringBuffer()
//            for (i in messageDigest.indices) {
//                var h = Integer.toHexString(0xFF and messageDigest[i].toInt())
//                while (h.length < 2) h = "0$h"
//                hexString.append(h)
//            }
//            return hexString.toString()
//        } catch (e: NoSuchAlgorithmException) {
////            Logger.logStackTrace(TAG, e)
//        }
//        return ""
//    }
}