package com.loc.newsapp.presentation

import androidx.compose.ui.unit.dp

object Dimens {  //Dimens = nơi lưu kích thước tái sử dụng cho UI, tránh hardcode (ví dụ 24dp, 30dp).
    val MediumPadding1 = 24.dp
    val MediumPadding2 = 30.dp
    val IndicatorSize = 14.dp
    val PageIndicatorWidth = 52.dp
}

/**
✅ 1. object trong Kotlin dùng để làm gì?
Trong Kotlin, object được dùng để tạo ra một singleton — tức là:
👉 Chỉ có đúng 1 instance duy nhất trong toàn bộ chương trình:
        👉 Tự động tạo duy nhất 1 đối tượng (instance) cho object đó
        👉 Đối tượng này được dùng chung ở mọi nơi trong app
        👉 Không ai có thể tạo thêm bản thứ 2
        📌 Hiểu như kiểu: toàn bộ app chỉ có một Dimens để sử dụng, ko bao h đc tạo dimens thứ 2

👉 Dùng để chứa: hằng số, hàm tiện ích, config chung.
👉 Không thể tạo nhiều object từ nó (như với class).
    Với class, bạn có thể tạo nhiều đối tượng khác nhau.
        Ví dụ class: class User
        Bạn có thể tạo bao nhiêu cũng được:
        val user1 = User()
        val user2 = User()
        val user3 = User()

    Nhưng với object, bạn không được phép làm vậy:
        val a = Dimens() // ❌ Sai — object không có constructor
        val b = Dimens() // ❌ Sai

⭐ Tóm tắt siêu dễ hiểu
object = singleton → tạo đúng 1 lần duy nhất.
class = tạo được nhiều object mỗi lần gọi constructor.


VD:
object Dimens {
val MediumPadding1 = 24.dp
val MediumPadding2 = 30.dp
}

Đây là một singleton chứa các giá trị dùng chung, thường dùng để:
✔ Định nghĩa padding
✔ Margin
✔ Font size
✔ Kích thước UI
✔ Không phải khởi tạo → gọi trực tiếp bất cứ đâu

👉 Truy cập trực tiếp bằng tên như biến static trong Java.
👉 Bạn dùng trong Compose như sau:
Modifier.padding(Dimens.MediumPadding1)

 */