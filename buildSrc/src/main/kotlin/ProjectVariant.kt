/**
 * Created by faisalamir on 19/09/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectVariant {

    const val SUFFIX_SERVER = "remote-data-source"

    val listPlayStore = listOf(
        Frogobox.playConsoleVariant,
        MonaPrimaveras.playConsoleVariant,
        Xeonidas.playConsoleVariant,
        Mnia.playConsoleVariant,
        CaravanCodes.playConsoleVariant,
        SuncodeOri.playConsoleVariant,
        SuncodeAmir.playConsoleVariant
    )

    object Frogobox {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "frogobox"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = "frogo"
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/frogoboxmedia.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "frogoisback"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object MonaPrimaveras {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "monaprimaveras"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/mona-primaveras.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "monaprimaveras"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object Xeonidas {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "xeonidas"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/xeonidas.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "xeonidasisback"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object Mnia {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "mnia"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/mnia.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "mniaisback"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object CaravanCodes {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "caravancodes"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/caravan-key.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "caravanisback"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            PLAY_STORE_NAME,
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object SuncodeOri {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "suncode"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/suncode-ori.jks"
        const val PLAYSTORE_STORE_PASSWORD = "suncode2020"
        const val PLAYSTORE_KEY_ALIAS = "Suncode"
        const val PLAYSTORE_KEY_PASSWORD = "suncode2020"

        val playConsoleVariant = PlayConsoleVariant(
            "${PLAY_STORE_NAME}Ori",
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

    object SuncodeAmir {

        // Play Console / Godev Name
        const val PLAY_STORE_NAME = "suncode"
        val APP_ID = "${ProjectSetting.APP_DOMAIN}.$PLAY_STORE_NAME.${ProjectSetting.APP_NAME}"
        val DB = "db_${PLAY_STORE_NAME}_${ProjectSetting.NAME_SETTING_LOWERCASE}.db"
        val PREF_NAME = "PREF_GAME_${PLAY_STORE_NAME.toUpperCase()}_${ProjectSetting.NAME_SETTING_UPERCASE}"

        // -----------------------------------------------------------------------------------------

        // Server
        private const val PREFIX_REPO_SERVER = PLAY_STORE_NAME
        private const val REPOSITORY_SERVER_NAME = "$PREFIX_REPO_SERVER-$SUFFIX_SERVER"
        private const val BASE_URL_ADMOB_SERVER = "api/v1"
        val URL_ADMOB_SERVER = "$BASE_URL_ADMOB_SERVER/$PLAY_STORE_NAME/${ProjectSetting.NAME_APK}/"

        // -----------------------------------------------------------------------------------------

        // Key Store
        const val PLAYSTORE_STORE_FILE = "keystore/suncode-amir.jks"
        const val PLAYSTORE_STORE_PASSWORD = "amirisback"
        const val PLAYSTORE_KEY_ALIAS = "suncode"
        const val PLAYSTORE_KEY_PASSWORD = "amirisback"

        val playConsoleVariant = PlayConsoleVariant(
            "${PLAY_STORE_NAME}Amir",
            APP_ID,
            DB,
            PREF_NAME,
            URL_ADMOB_SERVER,
            PLAYSTORE_STORE_FILE,
            PLAYSTORE_STORE_PASSWORD,
            PLAYSTORE_KEY_ALIAS,
            PLAYSTORE_KEY_PASSWORD
        )

    }

}

data class PlayConsoleVariant(
    val name: String,
    val appId: String,
    val dbName: String,
    val prefName: String,
    val urlAdmobServer: String,
    val playStoreStoreFile: String,
    val playStoreStorePassword: String,
    val playStoreKeyAlias: String,
    val playStoreKeyPassword: String,
)