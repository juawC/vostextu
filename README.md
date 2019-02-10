# vostextu

The project's architecture was based on the Clean Architecture principles but does not strictly follows it. There are five main packages:
* Data and Model that have model and data classes.
* Domain that have UseCases classes.
* Ui for the presentation classes (Fragments, ViewModels, etc..)
* DI for most of the Dagger classes.

Regarding the presentation logic I opted for using MVVM backed up with a ViewState class to store the ViewModel Model and a ViewAction interface for the Ui callbacks.

Regarding testing, I choose to test the following classes: GetUserUseCase, PostsViewModel, PostsFragment. Testing these classes allowed me to have three different kind of tests.
Additionally the PostFragmentTests can be run both using instrumentation or robolectric:

  ./gradlew -Pandroid.testInstrumentationRunnerArguments.class=com.app.juawcevada.vostextu.ui.posts.PostsFragmentTest connectedCheck

./gradlew testDebugUnitTest --tests "com.app.juawcevada.vostextu.ui.posts.PostsFragmentTest"
