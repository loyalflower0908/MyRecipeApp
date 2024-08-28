# 나만의 레시피 🍲

"나만의 레시피"는 사용자가 자신만의 레시피를 작성, 보관, 편집할 수 있는 안드로이드 앱입니다.

또한, 오늘 살 것 리스트를 작성하거나, 유튜브 레시피 영상을 검색하고 북마크하는 기능도 제공합니다.

심플한 UI로 사용하기 쉽게 직관적으로 만들려고 했습니다.

&nbsp;

&nbsp;

<!-- 여기에 앱의 동작을 보여주는 GIF 이미지 링크를 추가하세요 -->

## 주요 기능 ✨

- **레시피 작성 및 관리**: 사용자는 새로운 레시피를 작성하고 저장, 편집, 삭제할 수 있습니다. 
- **오늘 살 것 작성 및 관리**: 쇼핑 리스트를 작성하여 필요한 재료를 관리할 수 있습니다.
- **레시피 영상 검색**: 앱 내에서 유튜브를 검색하여 다양한 요리 영상을 찾고 볼 수 있습니다.
- **영상 북마크 저장**: 좋아하는 요리 영상을 북마크하여 나중에 쉽게 찾을 수 있습니다.

&nbsp;

&nbsp;

## 스크린샷 📱

### 홈 화면
![Home Screen](path_to_home_screen_image)

### 오늘 살 것 화면
![Shopping List Screen](path_to_home_screen_image)

### 레시피 작성 화면
![Recipe Writing Screen](path_to_recipe_writing_image)

### 영상 검색 화면
![YouTube Search Screen](path_to_youtube_search_image)

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
