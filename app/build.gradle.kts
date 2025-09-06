import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "fi.project.petcare"
    compileSdk = 34

    defaultConfig {
        applicationId = "fi.project.petcare"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"

        // Load credentials from local.properties
        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val apiKey = properties.getProperty("api.key") ?: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh6cnFwZXFwcnNqZGRtc3lnY2JvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTY3MjYyNTYsImV4cCI6MjA3MjMwMjI1Nn0.Y0rQB_85w2RKU5yvu5jUybgAYFiqryNReWGJDywMI8Q"
        val apiUrl = properties.getProperty("api.url") ?: "https://xzrqpeqprsjddmsygcbo.supabase.co"
        val serverClientId = properties.getProperty("server.client.id") ?: "281776486417-93kc2ef64fosl90vq8g5pipdrjnbo5l0.apps.googleusercontent.com"

        // BuildConfig fields with proper quotes
        buildConfigField("String", "SUPABASE_KEY", "\"$apiKey\"")
        buildConfigField("String", "SUPABASE_URL", "\"$apiUrl\"")
        buildConfigField("String", "SERVER_CLIENT_ID", "\"$serverClientId\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.androidx.material.icons.extended)
        implementation(libs.androidx.lifecycle.viewmodel.compose)
        implementation(libs.androidx.navigation.compose)

        // Unit test
        testImplementation("junit:junit:4.13.2")
        testImplementation("com.google.truth:truth:1.4.4")


        // Instrumented / Compose tests
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        // Other libraries
        implementation("org.osmdroid:osmdroid-android:6.1.15")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("androidx.preference:preference-ktx:1.2.1")

        // Credentials manager and Google sign-in
        implementation(libs.androidx.credentials)
        implementation(libs.androidx.credentials.play.services.auth)
        implementation(libs.googleid)

        // Supabase libraries
        implementation(platform(libs.bom))
        implementation(libs.postgrest.kt)
        implementation(libs.compose.auth.ui)
        implementation(libs.compose.auth)
        implementation(libs.gotrue.kt)
        implementation(libs.ktor.client.android)

        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.11")
        debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.11")



    }


}
