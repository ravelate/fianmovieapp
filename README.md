# fianmovieapp

## DESCRIPTION
It is a Movie application that consists of show list of movie from TMDB Api, where I applied the MVVM design pattern I wrote with Kotlin, and apply clean code rules.

## LIBRARY
- ViewModel
- LiveData
- flow
- Retrofit
- OKHttp
- Koin
- Service
- Coroutine
- Room
- Glide

Catatan untuk HR, saya tidak mengikuti perintah " The app should implement Local Broadcast Manager, once the updates are
completed by the Service, the Activity should display a notification stating that the
old data has been deleted and new data is available" dikarenakan library nya sendiri sudah deprecated (source: https://developer.android.com/reference/androidx/localbroadcastmanager/content/LocalBroadcastManager). jadi saya menggunakan yang lebih terbaru untuk reactive streams seperti menggunakan live data dan flow
