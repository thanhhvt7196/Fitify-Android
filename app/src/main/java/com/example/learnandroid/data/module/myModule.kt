import com.example.learnandroid.presentation.screens.dashboard.DashboardViewModel
import com.example.learnandroid.presentation.screens.home.HomeViewModel
import com.example.learnandroid.presentation.screens.notifications.NotificationsViewModel
import com.example.learnandroid.presentation.screens.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { SplashViewModel() }
    single { HomeViewModel() }
    single { DashboardViewModel() }
    single { NotificationsViewModel() }
}