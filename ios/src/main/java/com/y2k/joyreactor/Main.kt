package com.y2k.joyreactor

import com.intel.moe.natj.general.Pointer
import com.intel.moe.natj.general.ann.RegisterOnStartup
import com.intel.moe.natj.objc.ann.Selector
import ios.NSObject
import ios.foundation.NSDictionary
import ios.uikit.UIApplication
import ios.uikit.UIWindow
import ios.uikit.c.UIKit
import ios.uikit.protocol.UIApplicationDelegate
import y2k.joyreactor.common.ServiceLocator
import y2k.joyreactor.common.platform.IosPlatform
import y2k.joyreactor.common.platform.Platform

@RegisterOnStartup
class Main(peer: Pointer) : NSObject(peer), UIApplicationDelegate {

    override fun applicationDidFinishLaunchingWithOptions(application: UIApplication?, launchOptions: NSDictionary<*, *>?): Boolean {
        ServiceLocator.registerSingleton<Platform> { IosPlatform() }
        return true
    }

    private var window: UIWindow? = null

    override fun setWindow(value: UIWindow?) {
        window = value
    }

    override fun window(): UIWindow? {
        return window
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            UIKit.UIApplicationMain(0, null, null, Main::class.java.name)
        }

        @Selector("alloc")
        external fun alloc(): Main
    }
}