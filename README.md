# 🍲 나만의 레시피 🍲

"나만의 레시피"는 사용자가 자신만의 레시피를 작성, 보관, 편집할 수 있는 안드로이드 앱입니다.

또한, 오늘 살 것 리스트를 작성하거나, 유튜브 레시피 영상을 검색하고 북마크하는 기능도 제공합니다.

심플한 UI로 사용하기 쉽게 직관적으로 만들려고 했습니다.

&nbsp;

&nbsp;


## ✨ 주요 기능 ✨

- **레시피 작성 및 관리**: 사용자는 새로운 레시피를 작성하고 저장, 편집, 삭제할 수 있습니다. 
- **오늘 살 것 작성 및 관리**: 쇼핑 리스트를 작성하여 필요한 재료를 관리할 수 있습니다.
- **레시피 영상 검색**: 앱 내에서 유튜브를 검색하여 다양한 요리 영상을 찾고 볼 수 있습니다.
- **영상 북마크 저장**: 좋아하는 요리 영상을 북마크하여 나중에 쉽게 찾을 수 있습니다.

&nbsp;

&nbsp;

## 📱 스크린샷 📱

<div style="display: flex; flex-direction: row;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/Splash.gif" width="30%" height="30%" style="margin: 0 10px;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/RecipeListScreen.gif" width="30%" height="30%" style="margin: 0 10px;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/WritingScreen.gif" width="30%" height="30%" style="margin: 0 10px;">
</div>

<div style="display: flex; flex-direction: row;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/ShoppingList.gif" width="30%" height="30%" style="margin: 0 10px;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/SearchScreen.gif" width="30%" height="30%" style="margin: 0 10px;">
    <img src="https://github.com/loyalflower0908/MyRecipeApp/blob/master/screenshot/FavoriteVideoScreen.png" width="30%" height="30%" style="margin: 0 10px;">
</div>

&nbsp;

&nbsp;

## 📚 기술스택 📚

- **[Jetpack Compose](https://developer.android.com/jetpack/compose)**
- **[Dagger Hilt](https://dagger.dev/hilt/)**: 의존성 주입을 관리하는 DI 프레임워크
- **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** & [Flow](https://developer.android.com/kotlin/flow): 비동기 프로그래밍
- **[Retrofit](https://square.github.io/retrofit/)**: Youtube API와의 네트워크 통신을 위해 사용됩니다.(REST API 통신)
- **[Room Database](https://developer.android.com/training/data-storage/room)**: 로컬 DB
- **[TypeSafeNavigationCompose](https://developer.android.com/guide/navigation/design/type-safety)**: 앱 내 화면 전환을 관리하고, 안전한 네비게이션을 구현
- **MVVM 패턴**: MVVM 아키텍처 패턴을 사용하여 UI 데이터를 관리합니다.

&nbsp;

&nbsp;

## 📌 느낀점 📌

이렇게 규격에 맞춰서 정리하며 프로젝트를 진행하는것은 처음이다.

게다가 혼자 진행하니까 더욱 내게 도움이 되는 경험이었고 새로운 지식도 많이 얻어간다.

확실히 이렇게 정리하니까 나중에 유지보수할 때 편한 것 같다.

이후에 계속 사용해보며 버그를 찾고 디버깅하는 과정에서 어디 폴더의 파일에 있는지 쉽게 알 수 있으니 정말 편했다.

책임이 분리되어 있는 것도 있고, 잘 정리되어 있는 것도 있고...

배운 것을 정리하고 새로운 것을 배우고 한 번 더 업그레이드 할 수 있는 시간이었다.

또한 지금까지 코딩 인생을 뒤돌아볼 수 있는 계기가 되었다...

정리하지 않고 화려한 UI와 재밌는 기능에만 심취했었던것 같다. 정리와 구조같은 기반이 중요함을 느꼈다.

<br>
