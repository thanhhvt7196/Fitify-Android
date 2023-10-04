import com.example.learnandroid.ui.screens.dashboard.DashboardViewModel
import com.example.learnandroid.ui.screens.home.HomeViewModel
import com.example.learnandroid.ui.screens.notifications.NotificationsViewModel
import com.example.learnandroid.ui.screens.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { SplashViewModel() }
    single { HomeViewModel() }
    single { DashboardViewModel() }
    single { NotificationsViewModel() }
}