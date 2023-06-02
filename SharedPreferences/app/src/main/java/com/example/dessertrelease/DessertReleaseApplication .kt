
private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.datastore: DataStore<Preferences> by preferencesStore(
    name = LAYOUT_PREFERENCE_NAME
        )
class DessertReleaseApplication: Application() {
    private lateinit var userPreferencesRepository: UserPrefenrecesRepository

    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}