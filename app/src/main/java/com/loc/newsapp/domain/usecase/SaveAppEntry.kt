package com.loc.newsapp.domain.usecase

import com.loc.newsapp.domain.manager.LocalUserManager
//Lớp SaveAppEntry này là một Use Case (Trường hợp sử dụng). Trong kiến trúc Clean Architecture,
// Use Case đóng vai trò là "người truyền tin" giữa tầng Giao diện (UI) và tầng Dữ liệu (Data).
class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}

/*
1. Ý nghĩa của lớp SaveAppEntry
Lớp này chỉ làm đúng một việc duy nhất: Ra lệnh cho localUserManager lưu trạng thái người dùng đã vào app.
Thay vì để ViewModel gọi trực tiếp vào tầng Data (Manager), chúng ta đi qua Use Case để code sạch hơn, dễ kiểm tra (test) và dễ bảo trì hơn.

2. Giải thích các từ khóa
suspend (Tạm dừng)
Tác dụng: Đánh dấu đây là một hàm có thể gây tốn thời gian (như ghi file vào bộ nhớ).
Cơ chế: Khi hàm này chạy, nó sẽ "tạm dừng" Coroutine đang chứa nó mà không làm "treo" (block) luồng chính (Main Thread) của ứng dụng.
 Khi việc ghi dữ liệu xong, nó sẽ tự động tiếp tục công việc.
Quy tắc: Bạn chỉ có thể gọi một hàm suspend từ một hàm suspend khác hoặc từ bên trong một Coroutine.

operator (Toán tử)
Tác dụng: Cho phép bạn sử dụng một đối tượng (object) như thể nó là một hàm.
Cơ chế: Trong Kotlin, khi bạn thêm từ khóa operator trước hàm invoke, bạn đang thực hiện "nạp chồng toán tử" (operator overloading).

invoke (Gọi)
Tác dụng: Đây là một hàm đặc biệt trong Kotlin. Khi kết hợp với operator, nó cho phép bạn gọi lớp đó bằng cặp dấu ngoặc đơn ().
Ví dụ thực tế: Thay vì phải viết dài dòng: saveAppEntry.execute() hoặc saveAppEntry.save()
Bạn có thể gọi cực kỳ ngắn gọn: saveAppEntry()

3. Tại sao lại dùng cách này?
Hãy xem sự khác biệt khi sử dụng trong ViewModel:
Nếu không dùng invoke: saveAppEntry.save() (Trông giống như đang gọi một phương thức bình thường).
Khi dùng invoke: saveAppEntry() (Trông giống như bạn đang gọi một "hành động" cụ thể.
Điều này giúp code đọc giống ngôn ngữ tự nhiên hơn: "Thực hiện việc Lưu App Entry").

Tóm tắt luồng đi:
ViewModel gọi saveAppEntry().
Hàm invoke() trong Use Case được kích hoạt.
Use Case gọi localUserManager.saveAppEntry().
LocalUserManagerImpl dùng DataStore để ghi giá trị true vào bộ nhớ máy.
*/