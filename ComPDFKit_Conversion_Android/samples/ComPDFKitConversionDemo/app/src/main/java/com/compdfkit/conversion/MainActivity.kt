package com.compdfkit.conversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.compdfkit.conversion.component.ConvertMainPage
import com.compdfkit.conversion.ui.theme.ComPDFKitConversionDemoTheme
import com.compdfkit.conversion.utils.AppContextHolder
import com.compdfkit.conversion.utils.PermissionStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                ConversionHelper.installAIModel(this@MainActivity)
            }
        }
        AppContextHolder.init(this)
        enableEdgeToEdge()
        setContent {
            ComPDFKitConversionDemoTheme {
                PermissionCheckScreen()
            }
        }
    }
}

@Composable
fun PermissionCheckScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var hasPermission by remember { mutableStateOf(false) }
    val permissionManager = remember { PermissionStateManager(context) }

    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // 每次返回应用时检查权限
                hasPermission = permissionManager.checkManageStoragePermission()
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    // 应用启动时自动检查权限
    LaunchedEffect(Unit) {
        hasPermission = permissionManager.checkManageStoragePermission()
    }

    // 根据权限状态显示不同UI
    when {
        hasPermission -> {
            ConvertMainPage()
        }
    }
}