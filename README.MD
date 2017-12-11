[TOC]

Docker Nexus3
----

### Install
```
docker pull sonatype/nexus3
```

### Run
```
docker run -d -p 8081:8081 --name nexus sonatype/nexus3
```

### Login

`http://localhost:8081` `admin` `admin123`

### Create Repositories stat
Repository

这里的仓库分了四种类型
1. hosted(宿主仓库):用来部署自己,第三方或者公共仓库的构件
2. proxy(代理仓库):代理远程仓库
3. group(仓库组):统一管理多个仓库

简单创建 __hosted__ 类型的仓库

Deployment policy
1. Allow Redeploy
2. Disable Redeploy
3. Read-only .


### Upload Repository stat

```gradle.properties
#本地库
MAVEN_URL=http://172.16.118.134:8081/repository/stat/
MAVEN_SNAPSHOT_URL=http://172.16.118.134:8081/repository/stat-snapshot/
#对应maven的groupId值
GROUP=com.gooker.gnexus
#登录nexus oss的用户名
NEXUS_USERNAME=admin
#登录nexus oss的密码
NEXUS_PASSWORD=admin123
# groupid
GROUP_ID=com.gooker.gnexus
# type
TYPE=aar
# description
DESCRIPTION=dependences lib
```

stat model

```build.gradle
apply plugin: 'com.android.library'
apply plugin: 'maven'


def VERSION_NAME = '0.0.4'
def VERSION_CODE = 1

android {
//    compileSdkVersion 26
    compileSdkVersion rootProject.ext.compileSdkVersion

    defaultConfig {
        minSdkVersion rootProject.ext.minSdkVersion
        targetSdkVersion rootProject.ext.targetSdkVersion
        versionName VERSION_NAME
        versionCode VERSION_CODE


        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'com.android.support:appcompat-v7:26.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

uploadArchives {
    configuration = configurations.archives
    repositories {
        mavenDeployer {
            snapshotRepository(url: MAVEN_SNAPSHOT_URL) {
                authentication(userName: NEXUS_USERNAME, password: NEXUS_PASSWORD)
            }
            repository(url: MAVEN_URL) {
                authentication(userName: NEXUS_USERNAME, password: NEXUS_PASSWORD)
            }
            pom.project {
                version VERSION_NAME
                artifactId 'stat'
                groupId GROUP_ID
                packaging TYPE
                description DESCRIPTION
            }
        }
    }
}

artifacts {
    archives file('stat.aar')
}

```

Upload `gradle stat:uploadArchives`


### import
App Model
```build.gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    repositories {
        maven {
            url "http://172.16.118.134:8081/repository/stat/"
            credentials {
                username 'admin'
                password "admin123"
            }
        }
    }

    ...

    compile 'com.gooker.gnexus:stat:0.0.4'

}

```

### Accress Control
Secirity

允许 匿名访问 `Allow anonymous users to access the server`


### OK !
