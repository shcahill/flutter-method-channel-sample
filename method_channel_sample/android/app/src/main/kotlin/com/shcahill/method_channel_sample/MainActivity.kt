package com.shcahill.method_channel_sample

import android.content.Intent
import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        // MethodChannelからのメッセージを受け取ります
        // （flutterViewはFlutterActivityのプロパティ、CHANNELはcompanion objectで定義しています）
        MethodChannel(this.flutterView, CHANNEL)
                .setMethodCallHandler { _, result: MethodChannel.Result ->
                    launchAndroidScreen()
                    result.success(null)
                }
    }

    private fun launchAndroidScreen() {
        val intent = Intent(this, AnotherActivity::class.java)
        startActivity(intent)
    }

    companion object {
        // main.dartでMethodChannelのコンストラクタで指定した文字列です
        private const val CHANNEL = "package.name/sample"
    }
}
