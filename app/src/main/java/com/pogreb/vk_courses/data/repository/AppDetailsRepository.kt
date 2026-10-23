package com.pogreb.vk_courses.data.repository

import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails
import com.pogreb.vk_courses.domain.entity.Category

class AppDetailsRepository {
    fun getAppDetails() : AppDetails = AppDetails(
        id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
        name = "Гильдия Героев: Экшен ММО РПГ",
        developer = "VK Play",
        category = Category.GAME,
        ageRating = 12,
        size = 223.7f,
        screenshotUrlList = listOf(
            "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
            "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
            "https://static.rustore.ru/imgproxy/g5whSI1uNqaL2TUO7TFfM8M63vXpWXNCm2vlX4Ahvc4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/c2dde8bc-c4ab-482a-80a5-2789149f598d.jpg@webp",
            "https://static.rustore.ru/imgproxy/TjeurtC7BczOVJt74XhjGYuQnG1l4rx6zpDqyMb00GY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/08318f76-7a9c-43aa-b4a7-1aa878d00861.jpg@webp",
        ),
        iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!"
    )

    fun getSimilarApps(): List<App> = listOf(
        App(
            id = 1,
            name = "QuickNote",
            iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 2,
            name = "Calmalist",
            iconUrl = "https://fastly.picsum.photos/id/238/200/200.jpg?hmac=O4Jc6lqHVfaKVzLf8bWssNTbWzQoaRUC0TDXod9xDdM",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 3,
            name = "FitRiver",
            iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 4,
            name = "TaskLoop",
            iconUrl = "https://fastly.picsum.photos/id/240/200/200.jpg?hmac=PZiJibMUuv5Zpv9MAcqLaCYJOPt-XNuDh3RPBZiGcoY",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 5,
            name = "Pixtrix",
            iconUrl = "https://fastly.picsum.photos/id/241/200/200.jpg?hmac=F-mDYyK1xUiXjlLd5PvE8EQpTwGfFndV7mMaANDtl28",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 6,
            name = "GymMate",
            iconUrl = "https://fastly.picsum.photos/id/243/200/200.jpg?hmac=fW5ZwzzyTBy2t2MROp988oq12mZnKwN0coFLhZEE87s",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 7,
            name = "FlowCard",
            iconUrl = "https://fastly.picsum.photos/id/244/200/200.jpg?hmac=Q1gdvE6ZPZUX3nXkxvmzuc12eKVZ9XVEmSH3nCJ2OOo",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 8,
            name = "DreamSafe",
            iconUrl = "https://fastly.picsum.photos/id/2/200/200.jpg?hmac=isSWZUpv7D1D156XcADPOCZlfCG9mmvb8OlXFdvLdK0",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 9,
            name = "MoodPin",
            iconUrl = "https://fastly.picsum.photos/id/3/200/200.jpg?hmac=N5yYUNYl5gOUcaMmTtnNNtx839TN2qaNM4SaXhQl65U",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 10,
            name = "Shoply",
            iconUrl = "https://fastly.picsum.photos/id/247/200/200.jpg?hmac=oKt3N5MCdI8hCrzIbokjpVNzUuywbK64CJn1bfRAxbA",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 11,
            name = "FeedJoy",
            iconUrl = "https://fastly.picsum.photos/id/248/200/200.jpg?hmac=36BllTJxy_tU762d2RYKfYaSQ3-RmP74hVxabGP_u3o",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 12,
            name = "Scanwise",
            iconUrl = "https://fastly.picsum.photos/id/249/200/200.jpg?hmac=75zqoHvrxGGVnJnS8h0gUzZ3zniIk6PggG38GjmyOto",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 13,
            name = "MemoSpark",
            iconUrl = "https://fastly.picsum.photos/id/250/200/200.jpg?hmac=23TaEG1txY5qYZ70amm2sUf0GYKo4v7yIbN9ooyqWzs",
            description = "Описание",
            category = "Категория",
        ),
        App(
            id = 14,
            name = "ZenRadio",
            iconUrl = "https://fastly.picsum.photos/id/251/200/200.jpg?hmac=_yKttpPQLBisFkKPMRolKUyfZ89QQpENncPdrg8a1J0",
            description = "Описание",
            category = "Категория",
        ),
    )
}