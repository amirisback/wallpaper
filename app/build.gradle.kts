plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = "com.frogobox.wallpaper"
    flavorDimensions += "version"

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Naming APK // AAB
        setProperty("archivesBaseName", "${ProjectSetting.NAME_APK}-${versionCode}")

        // Search for event by event name
        buildConfigField("String", "TOPIC_WALLPAPER", "\"${ProjectSetting.TOPIC}\"")

        // Inject app name for debug
        resValue("string", "app_name", ProjectSetting.NAME_APP_DEBUG)

        // Inject admob id for debug
        resValue("string", "admob_app_id", ProjectAds.Admob.Debug.APP_ID)

        // Inject admob interstitial id for debug
        resValue("string", "admob_interstitial_id", ProjectAds.Admob.Debug.INTERSTITIAL_ID)

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }

    }

    sourceSets {
        getByName("androidTest"){
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }

    productFlavors {

        ProjectVariant.listPlayStore.forEach {
            create(it.name) {
                // Application ID
                applicationId = it.appId

                // Dimension
                dimension = "version"

            }
        }

    }

    signingConfigs {

        // You need to specify either an absolute path or include the
        // keystore file in the same directory as the build.gradle file.
        // [PROJECT FOLDER NAME/app/[COPY YOUT KEY STORE] .jks in here

        ProjectVariant.listPlayStore.forEach {
            create(it.name) {
                storeFile = file(it.playStoreStoreFile)
                storePassword = it.playStoreStorePassword
                keyAlias = it.playStoreKeyAlias
                keyPassword = it.playStoreKeyPassword
            }
        }

    }

    buildTypes {

        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false

            ProjectVariant.listPlayStore.forEach {
                productFlavors.getByName(it.name) {
                    signingConfig = signingConfigs.getByName(it.name)
                }
            }

            // Inject app name for release
            resValue("string", "app_name", ProjectSetting.NAME_APP)

            // Inject admob id for release
            resValue("string", "admob_app_id", ProjectAds.Admob.APP_ID)

            // Inject admob interstitial id for release
            resValue("string", "admob_interstitial_id", ProjectAds.Admob.INTERSTITIAL_ID)

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    packagingOptions {
        resources {
            excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
        }
    }

}

dependencies {

    implementation(Androidx.Core.ktx)
    implementation(Androidx.Work.runtimeKtx)

    implementation(Androidx.constraintLayout)
    implementation(Androidx.swipeRefreshLayout)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompat)

    implementation(Frogo.ui)
    implementation(Frogo.sdk)
    implementation(Frogo.admob)
    implementation(Frogo.recyclerView)
    implementation(Frogo.consumeApi)

    implementation(GitHub.balloon)

    implementation(Util.hdodenhofCircleImageView)

    kapt(Androidx.Lifecycle.compiler)
    kapt(Androidx.Room.compiler)
    kapt(GitHub.glideCompiler)

}