package com.loc.newsapp.presentation.onboarding


import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(  // data class giống với 1 model trong java để lưu trữ dữ liệu
    val title: String,
    val description: String,
    @DrawableRes val image: Int  //biến image kiểu int vì int là truyền vào vị trí bức ảnh. ta để @DrawableRes để android biết rằng nó là 1 tài nguyên trong folder drawable để android load được
)

//tao 1 list 3 đối tượng page = 3 trang dùng trong onboarding
val pages = listOf(
    Page(
        title = "Explore",
        description = "Find the latest news and articles from all over the world",
        image = R.drawable.onboarding1

    ),
    Page(
        title = "Save",
        description = "Save and share your favorite articles and news later",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Earn",
        description = "Earn points by reading, sharing and comment on articles and news",
        image = R.drawable.onboarding3
    )
)


/**
🟦 1. Data class trong Kotlin dùng để làm gì?
Mục đích chính:

✔️ Lưu trữ dữ liệu
✔️ Đại diện cho 1 model / entity / object
✔️ Dùng để truyền dữ liệu giữa các hàm, màn hình, API, database
✔️ Tự động tạo:

equals()
hashCode()
toString()
copy()
componentN() (hỗ trợ destructuring)
👉 Đây là những thứ trong Java bạn phải viết bằng tay.

🟦 2. Khi nào nên dùng data class?

Khi class của bạn chỉ dùng để chứa dữ liệu, ví dụ:
User
Product
LoginResponse
Book
TodoItem
API response object

Ví dụ:
data class User(
val id: Int,
val name: String,
val email: String
)

🟦 3. Data class có giống class trong Java không?
✔️ Giống ở chỗ
Đều là class
Đều có constructor
Đều có property/variable

❌ Không giống ở chỗ – Kotlin data class quá mạnh
Trong Java bạn cần viết như sau:
    public class User {
        private int id;
        private String name;

        public User(int id, String name) { ... }

        public int getId() { ... }
        public String getName() { ... }

        @Override
        public boolean equals(Object o) { ... }

        @Override
        public int hashCode() { ... }

        @Override
        public String toString() { ... }
    }

✔️ Trong Kotlin chỉ cần:
data class User(val id: Int, val name: String)
Và Kotlin tạo tự động toàn bộ 7 hàm quan trọng:

1️⃣ toString()
→ "User(id=1, name=Dang)"

2️⃣ equals()
→ So sánh giá trị, không phải so sánh địa chỉ

3️⃣ hashCode()
→ Hỗ trợ dùng trong Set, Map

4️⃣ copy()
→ Clone object chỉ với vài property mới
val user1 = User(1, "Dang")
val user2 = user1.copy(name = "Hieu")

5️⃣ component1(), component2(), ...
→ Cho phép destructuring:
val (id, name) = user1

 */